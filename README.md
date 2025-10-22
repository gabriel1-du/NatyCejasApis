# NatyCejas APIs

Backend del sitio de belleza NatyCejas, compuesto por dos microservicios independientes que comparten la base de datos `naty_cejas_bda` (MariaDB/MySQL mediante XAMPP) y exponen documentación Swagger (Springdoc OpenAPI 2.3.0).

- Frontend relacionado: `https://github.com/gabriel1-du/NatyCejasWeb`
- Base de datos: `localhost:3306/naty_cejas_bda` (MariaDB via XAMPP)
- Microservicios:
  - GestionUsuariosApi (puerto `8096`)
  - InventarioApi (puerto `8089`)

---

## Parte I — GestionUsuariosApi

API centrada en usuarios, carritos, pedidos, comunas/regiones y proceso de checkout. Además, integra un cliente HTTP para comunicar stock con `InventarioApi`.

### Estructura de carpetas

- `ControllerFolder/` Controladores REST de dominio:
  - `UsuarioController.java` — CRUD de usuarios.
  - `RegionController.java` — CRUD de regiones.
  - `ComunaController.java` — CRUD de comunas.
  - `CarritoController.java` — CRUD de carritos por usuario.
  - `CarritoProductoController.java` — CRUD de productos dentro del carrito.
  - `PedidoController.java` — CRUD de pedidos.
  - `CheckoutController.java` — proceso de compra (checkout).
- `auth/` Autenticación básica:
  - `AuthController.java` — login por email y contraseña.
  - `AuthRequest.java` — DTO de credenciales.
- `DTOFolder/` DTOs organizados por contexto (Usuarios, Carrito, CarritoProducto, Pedido, Región, Comuna, Checkout).
- `ModelFolder/` Entidades JPA (Usuario, Carrito, CarritoProducto, Pedido, Región, Comuna).
- `RepositoryFolder/` Interfaces `JpaRepository` por entidad.
- `Service/` Interfaces de servicios de dominio.
- `ServiceImpl/` Implementaciones de servicios.
- `MapperFolder/` Mappers entre entidades y DTOs.
- `ExternalClientFolder/` Cliente HTTP hacia Inventario:
  - `InventarioClient.java` — builder sobre `java.net.http.HttpClient`.
- `ConfigFolder/` Beans y configuración:
  - `ExternalClientsConfig.java` — bean de `InventarioClient` usando `inventario.api.base-url`.

### Controladores y rutas base

- `UsuarioController` — base `\api\usuarios`
  - GET `/{id}` — obtener usuario por ID
  - GET `` — listar usuarios
  - POST `` — crear usuario
  - PUT `/{id}` — actualizar usuario
  - DELETE `/{id}` — eliminar usuario
  - GET `/crudo/{id}`, GET `/crudo` — devuelven entidad `Usuario` completa

- `RegionController` — base `\api\regiones`
  - CRUD completo (GET, POST, PUT, DELETE)

- `ComunaController` — base `\api\comunas`
  - CRUD completo (GET, POST, PUT, DELETE)

- `CarritoController` — base `\api\carritos`
  - CRUD completo (GET, POST, PUT, DELETE)

- `CarritoProductoController` — base `\api\carrito-producto`
  - CRUD completo (GET, POST, PUT, DELETE)

- `PedidoController` — base `\pedidos`
  - CRUD completo (GET, POST, PUT, DELETE)

- `CheckoutController` — base `\usuarios\ventas`
  - POST `/checkout` — procesa compra: valida RUN del usuario, descuenta stock llamando a `InventarioApi`, registra venta y devuelve resumen

- `AuthController` — base `\api\auth`
  - POST `/login` — autentica con email y contraseña (devuelve `UsuarioDTO`)

### Configuración y comunicación

- Puerto: `8096`
- DB: `spring.datasource.url=jdbc:mysql://localhost:3306/naty_cejas_bda`
- Cliente externo:
  - `inventario.api.base-url=http://localhost:8089/inventario`
  - `inventario.api.connect-timeout-ms` (opcional)
- Swagger/OpenAPI:
  - Swagger UI: `http://localhost:8096/swagger-ui/index.html`
  - OpenAPI JSON: `http://localhost:8096/v3/api-docs`

---

## Parte II — InventarioApi

API de catálogo, marcas, categorías, servicios y citas. Expone endpoints para gestión de stock e imágenes de producto.

### Estructura de carpetas

- `Controller/` Controladores REST por dominio:
  - `ProductoControllerFolder/ProductoController.java` — productos, stock e imágenes.
  - `ProductoControllerFolder/CategoriaProductoController.java` — categorías de producto.
  - `MarcaContoller.java` — marcas.
  - `ServicioControllerFolder/ServicioController.java` — servicios.
  - `CitaControllerFolder/CitaController.java` — citas.
- `DTO/` DTOs de producto y servicio.
- `Model/` Entidades JPA (Producto, CategoriaProducto, Marca, Servicio, Cita).
- `Repository/` Repositorios `JpaRepository` por dominio.
- `Service/` Interfaces de servicios de dominio.
- `ServiceImpl/` Implementaciones de servicios.

### Controladores y rutas base

- `ProductoController` — base `\inventario\productos`
  - GET `` — listar productos (`ProductoDTO`)
  - GET `/{id}` — obtener producto
  - POST `` — crear producto
  - PUT `/{id}` — actualizar producto
  - PATCH `/{id}/sumar?cantidad={n}` — sumar stock
  - PATCH `/{id}/restar?cantidad={n}` — restar stock
  - DELETE `/{id}` — eliminar producto
  - POST `/con-foto` (multipart/form-data) — crear producto con imagen
  - PUT `/{id}/con-foto` (multipart/form-data) — actualizar con imagen
  - GET `/{id}/foto` — obtener bytes de imagen (local o URL remota)
  - PUT `/{id}/con-url` — actualizar con URL de imagen pública

- `CategoriaProductoController` — base `\inventario\categorias`
  - GET `` — listar categorías
  - GET `/{id}` — obtener categoría
  - POST `` — crear categoría
  - DELETE `/{id}` — eliminar categoría

- `MarcaContoller` — base `\inventario\marcas`
  - GET `` — listar marcas
  - GET `/{id}` — obtener marca
  - POST `` — crear marca
  - DELETE `/{id}` — eliminar marca

- `ServicioController` — base `\inventario\servicios`
  - CRUD completo (GET, POST, PUT, DELETE)

- `CitaController` — base `\inventario\citas`
  - CRUD completo (GET, POST, PUT, DELETE)

### Configuración

- Puerto: `8089`
- DB: `spring.datasource.url=jdbc:mysql://localhost:3306/naty_cejas_bda`
- Swagger/OpenAPI:
  - Swagger UI: `http://localhost:8089/swagger-ui/index.html`
  - OpenAPI JSON: `http://localhost:8089/v3/api-docs`

---

## Notas y prerequisitos

- XAMPP (MariaDB/MySQL) requerido para levantar `naty_cejas_bda` en `localhost:3306`.
- Importar el script SQL disponible en el repositorio para poblar datos iniciales.
- Ajustar credenciales de DB en `application.properties` si difieren de su entorno.
- Orden sugerido de ejecución: primero `InventarioApi` (8089), luego `GestionUsuariosApi` (8096) para que las llamadas entre servicios funcionen.

