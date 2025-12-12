package com.example.InventarioApi.Service.ProductoServicesFolder;

import java.util.List;

import com.example.InventarioApi.DTO.ProductoDTOs.CreateProductoDTO;
import com.example.InventarioApi.DTO.ProductoDTOs.ProductoDTO;
import com.example.InventarioApi.DTO.ProductoDTOs.UpdateProductoDTO;
import com.example.InventarioApi.Model.ProductoModelsFolder.Producto;
import org.springframework.web.multipart.MultipartFile;

public interface ProductoService {

    List<ProductoDTO> obtenerTodosLosProductos();

    ProductoDTO obtenerProductoPorId(Integer id);

    ProductoDTO crearProducto(CreateProductoDTO createProductoDTO);

    ProductoDTO crearProductoConFoto(String nombre, String descripcion, Integer precio, Integer stock,
                                     Integer idMarca, Integer idCategoria, MultipartFile foto);
    
    ProductoDTO crearProductoConUrl(String nombre, String descripcion, Integer precio, Integer stock,
                                     Integer idMarca, Integer idCategoria, String fotoUrl);
    //Sin Foto
    ProductoDTO actualizarProducto(Integer id, UpdateProductoDTO updateDTO);

    //Con foto
    ProductoDTO actualizarProductoConFoto(Integer id, String nombre, String descripcion, Integer precio, Integer stock,
                                          Integer idMarca, Integer idCategoria, org.springframework.web.multipart.MultipartFile foto);
    ProductoDTO actualizarProductoConUrl(Integer id, String nombre, String descripcion, Integer precio, Integer stock,
                                         Integer idMarca, Integer idCategoria, String fotoUrl);

    void eliminarProducto(Integer id);

    Producto sumarStock(Integer id, Integer cantidad);

    Producto restarStock(Integer id, Integer cantidad);

    String obtenerRutaFotoProducto(Integer id);

}
