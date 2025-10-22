# NatyCejasApis

## Descripción del proyecto
Backend para un sitio web de estética compuesto por dos microservicios:
- `GestionUsuariosApi` (puerto `8096`): gestión de usuarios, regiones, comunas, pedidos y proceso de checkout.
- `InventarioApi` (puerto `8089`): inventario de productos, servicios, marcas y citas.

Frontend relacionado:
- `NatyCejasWeb`: https://github.com/gabriel1-du/NatyCejasWeb

## Tecnologías utilizadas
- Lenguaje: Java 17
- Framework: Spring Boot 3.x (Web, Data JPA, Validation)
- Base de datos: MariaDB/MySQL (recomendado vía XAMPP)
- Cliente BD: `org.mariadb.jdbc:mariadb-java-client`
- Documentación: Springdoc OpenAPI (Swagger UI)
- Utilidades: Lombok
- Testing: JUnit 5 y Mockito
- Build: Maven (wrapper `mvnw.cmd` incluido)

## Instrucciones de instalación
- Requisitos previos:
  - Instalar y activar XAMPP (iniciar `Apache` y `MySQL`/`MariaDB`).
  - JDK 17 y Maven, o usar el wrapper `mvnw.cmd`.
- Base de datos:
  - Crear la base `naty_cejas_bda` en MariaDB (phpMyAdmin: `http://localhost/phpmyadmin`).
  - Importar el script SQL (el archivo está disponible en este repositorio para descargarse).
  - Credenciales por defecto (ajustables en `application.properties` de ambos servicios):
    - `spring.datasource.url=jdbc:mariadb://localhost:3306/naty_cejas_bda`
    - `spring.datasource.username=root`
    - `spring.datasource.password=` (vacío por defecto en XAMPP)
- Compilación por proyecto:
  - En `InventarioApi`:
    ```bash
    mvnw.cmd clean install
    ```
  - En `GestionUsuariosApi`:
    ```bash
    mvnw.cmd clean install
    ```

## Instrucciones de ejecución
- Orden sugerido: levantar primero `InventarioApi` y luego `GestionUsuariosApi`.
- `InventarioApi` (puerto 8089):
  ```bash
  mvnw.cmd spring-boot:run
  ```
- `GestionUsuariosApi` (puerto 8096):
  ```bash
  mvnw.cmd spring-boot:run
  ```
- Configuración relacionada:
  - `GestionUsuariosApi` consume `InventarioApi` vía `inventario.api.base-url=http://localhost:8089/inventario`.

## Documentación de API (Swagger / Postman)
- Swagger UI:
  - Inventario: `http://localhost:8089/swagger-ui/index.html`
  - Gestión de Usuarios: `http://localhost:8096/swagger-ui/index.html`
- OpenAPI JSON:
  - Inventario: `http://localhost:8089/v3/api-docs`
  - Gestión de Usuarios: `http://localhost:8096/v3/api-docs`
- Postman:
  - No se incluye una colección en este repositorio; puedes crearla desde Swagger o importarla desde las URLs `v3/api-docs` de cada servicio.

## Credenciales y payloads de prueba (basadas en archivos de test)
- Crear usuario (archivo `GestionUsuariosApi/user_create_payload.json`):
  - `rut`: `99.999.999-9`
  - `email`: `checkout_tester_999@example.com`
  - `contrasena`: `secret`
  - Otros campos: `nombre`, `apellido`, `telefono`, `direccion`, `admin`
- Checkout válido (`GestionUsuariosApi/checkout_payload.json`):
  - `runUsuario`: `99.999.999-9`
  - `items`: `[ { "idProducto": 1, "cantidad": 2 }, { "idProducto": 1, "cantidad": 1 }, { "idProducto": 2, "cantidad": 1 } ]`
- Checkout inválido (`GestionUsuariosApi/checkout_invalid.json`):
  - `items` con cantidad `0` para validar error de negocio.
- Datos de usuario usados en tests (`UsuarioServiceImplTest`):
  - `rut`: `11.111.111-1`
  - `email`: `naty@example.com`
  - `contrasena`: `secreta`

## Notas adicionales
- Requiere XAMPP para correr la base de datos local.
- Si cambias las credenciales de la base en XAMPP, actualiza `application.properties` en ambos servicios.
- Aunque `spring.jpa.hibernate.ddl-auto=update` mantiene el esquema, se recomienda importar el script SQL del repositorio para datos iniciales.

