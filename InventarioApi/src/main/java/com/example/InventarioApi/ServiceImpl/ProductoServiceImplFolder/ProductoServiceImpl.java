package com.example.InventarioApi.ServiceImpl.ProductoServiceImplFolder;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.springframework.web.multipart.MultipartFile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.InventarioApi.DTO.ProductoDTOs.CreateProductoDTO;
import com.example.InventarioApi.DTO.ProductoDTOs.MapperProducto;
import com.example.InventarioApi.DTO.ProductoDTOs.ProductoDTO;
import com.example.InventarioApi.DTO.ProductoDTOs.UpdateProductoDTO;
import com.example.InventarioApi.Model.Marca;
import com.example.InventarioApi.Model.ProductoModelsFolder.CategoriaProducto;
import com.example.InventarioApi.Model.ProductoModelsFolder.Producto;
import com.example.InventarioApi.Repository.MarcaRepository;
import com.example.InventarioApi.Repository.ProductoRepositoriesFolder.CategoriaProductoRepository;
import com.example.InventarioApi.Repository.ProductoRepositoriesFolder.ProductoRepository;
import com.example.InventarioApi.Service.ProductoServicesFolder.ProductoService;

@Service
public class ProductoServiceImpl implements ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private MarcaRepository marcaRepositorio;

    @Autowired
    private CategoriaProductoRepository categoriaProductoRepositorio;


    
    public List<ProductoDTO>  obtenerTodosLosProductos() {
        List<Producto> productos = productoRepository.findAll();
        return productos.stream()
                .map(MapperProducto::toDTO) // usamos el mapper que me pasaste
                .collect(Collectors.toList());
    }

    public Producto guardarProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    public ProductoDTO obtenerProductoPorId(Integer id) {
        Optional<Producto> producto = productoRepository.findById(id);
        return producto.map(MapperProducto::toDTO).orElse(null);
    }

    public void eliminarProducto(Integer id) {
        // Intentar eliminar la imagen asociada antes de eliminar el producto
        Optional<Producto> productoOpt = productoRepository.findById(id);
        productoOpt.ifPresent(p -> {
            String ruta = p.getFoto_url();
            if (ruta != null && !ruta.isBlank()) {
                try {
                    Path path = Paths.get(ruta);
                    if (Files.exists(path)) {
                        Files.delete(path);
                    }
                } catch (IOException ignored) {}
            }
        });
        productoRepository.deleteById(id);
    }


    public ProductoDTO crearProducto(CreateProductoDTO createDTO) {
        // Buscar Marca
        Marca marca = marcaRepositorio.findById(createDTO.getId_marca())
                .orElseThrow(() -> new RuntimeException("Marca no encontrada con id: " + createDTO.getId_marca()));

        // Buscar Categoría
        CategoriaProducto categoria = categoriaProductoRepositorio.findById(createDTO.getId_categoria())
                .orElseThrow(() -> new RuntimeException("Categoria no encontrada con id: " + createDTO.getId_categoria()));

        // Crear el Producto desde el DTO
        Producto producto = new Producto();
        producto.setNombre_producto(createDTO.getNombre_producto());
        producto.setDescripcion(createDTO.getDescripcion());
        producto.setPrecio(createDTO.getPrecio());
        producto.setStock(createDTO.getStock());
        producto.setMarca(marca);
        producto.setCategoria(categoria);
        Producto productoGuardado = productoRepository.save(producto);
        return MapperProducto.toDTO(productoGuardado);
    }


    public ProductoDTO actualizarProducto(Integer id, UpdateProductoDTO updateDTO) {
        // Buscar producto en BD
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con id: " + id));

        // Settear los datos actualizables
        producto.setNombre_producto(updateDTO.getNombre_producto());
        producto.setDescripcion(updateDTO.getDescripcion());
        producto.setPrecio(updateDTO.getPrecio());
        producto.setStock(updateDTO.getStock());
        // Marca y Categoria se mantienen iguales

        // Guardar cambios
        Producto actualizado = productoRepository.save(producto);

        // Retornar DTO
        return MapperProducto.toDTO(actualizado);
    }

    // Actualizar producto vía multipart/form-data con posible nueva imagen
    public ProductoDTO actualizarProductoConFoto(Integer id, String nombre, String descripcion, Integer precio, Integer stock,
                                                 Integer idMarca, Integer idCategoria, MultipartFile foto) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con id: " + id));

        // Actualizar campos básicos
        producto.setNombre_producto(nombre);
        producto.setDescripcion(descripcion);
        producto.setPrecio(precio);
        producto.setStock(stock);

        // Actualizar asociaciones
        Marca marca = marcaRepositorio.findById(idMarca)
                .orElseThrow(() -> new RuntimeException("Marca no encontrada con id: " + idMarca));
        CategoriaProducto categoria = categoriaProductoRepositorio.findById(idCategoria)
                .orElseThrow(() -> new RuntimeException("Categoria no encontrada con id: " + idCategoria));
        producto.setMarca(marca);
        producto.setCategoria(categoria);

        producto = productoRepository.save(producto);

        // Si viene una nueva imagen, guardarla y actualizar ruta
        if (foto != null && !foto.isEmpty()) {
            try {
                String original = foto.getOriginalFilename();
                String extension = "";
                if (original != null && original.contains(".")) {
                    extension = original.substring(original.lastIndexOf('.') + 1).toLowerCase();
                }
                if (extension.isBlank()) extension = "png";

                Path dir = Paths.get(System.getProperty("user.dir"), "uploads", "productos");
                Files.createDirectories(dir);

                String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
                String filename = "producto-" + producto.getId_producto() + "-" + timestamp + "." + extension;
                Path filePath = dir.resolve(filename);
                Files.write(filePath, foto.getBytes());

                producto.setFoto_url(filePath.toString());
                producto = productoRepository.save(producto);
            } catch (IOException e) {
                // Si falla la escritura, dejamos la ruta como estaba
            }
        }

        return MapperProducto.toDTO(producto);
    }


    public Producto sumarStock(Integer id, Integer cantidad) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
        producto.setStock(producto.getStock() + cantidad);
        return productoRepository.save(producto);
    }

    public Producto restarStock(Integer id, Integer cantidad) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
        if (producto.getStock() < cantidad) {
            throw new RuntimeException("Stock insuficiente");
        }
        producto.setStock(producto.getStock() - cantidad);
        return productoRepository.save(producto);
    }

    // Crear producto a partir de multipart/form-data con imagen
    public ProductoDTO crearProductoConFoto(String nombre, String descripcion, Integer precio, Integer stock,
                                            Integer idMarca, Integer idCategoria, MultipartFile foto) {
        // Buscar Marca y Categoria
        Marca marca = marcaRepositorio.findById(idMarca)
                .orElseThrow(() -> new RuntimeException("Marca no encontrada con id: " + idMarca));
        CategoriaProducto categoria = categoriaProductoRepositorio.findById(idCategoria)
                .orElseThrow(() -> new RuntimeException("Categoria no encontrada con id: " + idCategoria));

        // Crear y guardar producto preliminarmente para obtener ID
        Producto producto = new Producto();
        producto.setNombre_producto(nombre);
        producto.setDescripcion(descripcion);
        producto.setPrecio(precio);
        producto.setStock(stock);
        producto.setMarca(marca);
        producto.setCategoria(categoria);

        producto = productoRepository.save(producto);

        // Si viene imagen, guardar en uploads/productos y setear ruta
        if (foto != null && !foto.isEmpty()) {
            try {
                String original = foto.getOriginalFilename();
                String extension = "";
                if (original != null && original.contains(".")) {
                    extension = original.substring(original.lastIndexOf('.') + 1).toLowerCase();
                }
                if (extension.isBlank()) {
                    extension = "png";
                }

                Path dir = Paths.get(System.getProperty("user.dir"), "uploads", "productos");
                Files.createDirectories(dir);

                String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
                String filename = "producto-" + producto.getId_producto() + "-" + timestamp + "." + extension;
                Path filePath = dir.resolve(filename);
                Files.write(filePath, foto.getBytes());

                // Guardamos ruta absoluta o relativa; aquí absoluta
                producto.setFoto_url(filePath.toString());
                producto = productoRepository.save(producto);
            } catch (IOException e) {
                // Si falla la escritura, dejamos foto_url en null
            }
        }

        return MapperProducto.toDTO(producto);
    }
    
    public String obtenerRutaFotoProducto(Integer id) {
        Optional<Producto> producto = productoRepository.findById(id);
        return producto.map(Producto::getFoto_url).orElse(null);
    }
    
    // Actualizar producto usando una URL pública (sin subir archivo)
    public ProductoDTO actualizarProductoConUrl(Integer id, String nombre, String descripcion, Integer precio, Integer stock,
                                                Integer idMarca, Integer idCategoria, String fotoUrl) {
        // Buscar producto en BD
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con id: " + id));

        // Actualizar campos básicos
        producto.setNombre_producto(nombre);
        producto.setDescripcion(descripcion);
        producto.setPrecio(precio);
        producto.setStock(stock);

        // Actualizar asociaciones
        Marca marca = marcaRepositorio.findById(idMarca)
                .orElseThrow(() -> new RuntimeException("Marca no encontrada con id: " + idMarca));
        CategoriaProducto categoria = categoriaProductoRepositorio.findById(idCategoria)
                .orElseThrow(() -> new RuntimeException("Categoria no encontrada con id: " + idCategoria));
        producto.setMarca(marca);
        producto.setCategoria(categoria);

        // Si viene una URL válida, reemplazar foto_url (limitando a 255)
        if (fotoUrl != null) {
            String trimmed = fotoUrl.trim();
            if (!trimmed.isBlank()) {
                producto.setFoto_url(trimmed.length() > 255 ? trimmed.substring(0, 255) : trimmed);
            }
        }

        // Guardar cambios
        producto = productoRepository.save(producto);
        return MapperProducto.toDTO(producto);
    }
    
    // Crear producto a partir de una URL de imagen pública (sin subir archivo)
    public ProductoDTO crearProductoConUrl(String nombre, String descripcion, Integer precio, Integer stock,
                                           Integer idMarca, Integer idCategoria, String fotoUrl) {
        // Buscar Marca y Categoria
        Marca marca = marcaRepositorio.findById(idMarca)
                .orElseThrow(() -> new RuntimeException("Marca no encontrada con id: " + idMarca));
        CategoriaProducto categoria = categoriaProductoRepositorio.findById(idCategoria)
                .orElseThrow(() -> new RuntimeException("Categoria no encontrada con id: " + idCategoria));

        // Crear producto y setear campos
        Producto producto = new Producto();
        producto.setNombre_producto(nombre);
        producto.setDescripcion(descripcion);
        producto.setPrecio(precio);
        producto.setStock(stock);
        producto.setMarca(marca);
        producto.setCategoria(categoria);

        // Setear URL si viene y limitar a 255
        if (fotoUrl != null) {
            String trimmed = fotoUrl.trim();
            if (!trimmed.isBlank()) {
                producto.setFoto_url(trimmed.length() > 255 ? trimmed.substring(0, 255) : trimmed);
            }
        }

        producto = productoRepository.save(producto);
        return MapperProducto.toDTO(producto);
    }
    
}
