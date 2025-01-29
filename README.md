Carrito de Compras - API REST
Este proyecto es una API RESTful desarrollada con Java Spring Boot, que permite gestionar un carrito de compras, incluyendo la adición de productos, actualización de cantidades, eliminación de productos, aplicación de descuentos y persistencia en PostgreSQL. Además, se proporciona un entorno Dockerizado para facilitar su ejecución.

Tecnologías Utilizadas
Java 17
Spring Boot 3.4.2
PostgreSQL 13
Docker & Docker Compose
Lombok
MapStruct
SpringDoc OpenAPI (para documentación Swagger)
JUnit & Mockito (para pruebas unitarias)
Prerrequisitos
Antes de instalar y ejecutar el proyecto, asegúrate de contar con los siguientes programas instalados:

Java 17
Maven
Docker & Docker Compose
PostgreSQL 13+
También puedes utilizar Postman para probar los endpoints de la API.

Configuración del Proyecto
1️⃣ Clonar el Repositorio
bash
Copiar
Editar
git clone https://github.com/usuario/carrito-api.git
cd carrito-api
2️⃣ Configuración de la Base de Datos
Opción 1: Configuración Manual (PostgreSQL local)
Crear la base de datos en PostgreSQL:

sql
Copiar
Editar
CREATE DATABASE carrito;
Configurar las credenciales en el archivo application.yml:

yaml
Copiar
Editar
spring:
datasource:
url: jdbc:postgresql://localhost:5432/carrito
username: postgres
password: password
jpa:
hibernate:
ddl-auto: update
show-sql: true
Opción 2: Configuración con Docker
Si prefieres ejecutar PostgreSQL en un contenedor, usa el siguiente comando:

bash
Copiar
Editar
docker-compose up -d
Esto iniciará un contenedor de PostgreSQL con:

Usuario: postgres
Contraseña: password
Base de datos: carrito
Puerto: 5432
Ejecutar el Proyecto
Opción 1: Con Maven
Si ya tienes PostgreSQL configurado, ejecuta:

bash
Copiar
Editar
mvn spring-boot:run
Opción 2: Con Docker (API + BD en Contenedor)
bash
Copiar
Editar
docker-compose up --build
La API estará disponible en:

arduino
Copiar
Editar
http://localhost:8080
Documentación de la API (Swagger)
Una vez ejecutado el proyecto, puedes acceder a la documentación de los endpoints en:

Swagger UI → http://localhost:8080/swagger-ui.html
OpenAPI JSON → http://localhost:8080/v3/api-docs
Estructura del Proyecto
bash
Copiar
Editar
carrito/
│── src/
│   ├── main/
│   │   ├── java/com/carrito/carrito/
│   │   │   ├── application/
│   │   │   │   ├── service/
│   │   │   │   ├── usecase/
│   │   │   ├── domain/
│   │   │   │   ├── dto/
│   │   │   │   ├── exception/
│   │   │   │   ├── model/
│   │   │   ├── infrastructure/
│   │   │   │   ├── configuration/
│   │   │   │   ├── controller/
│   │   │   │   ├── mapper/
│   │   │   │   ├── repository/
│   │   │   ├── utils/
│   ├── test/
│── docker-compose.yml
│── pom.xml
│── application.yml
│── README.md
Explicación del Diagrama de Base de Datos


📌 Entidades y Relaciones
1️⃣ Carrito
id (PK): Identificador único del carrito.
usuario_id: Identificador del usuario propietario del carrito.
total: Monto total de los productos en el carrito.
🔹 Relación: Un carrito puede contener múltiples productos (1:N con CarritoProducto).
2️⃣ Producto
id (PK): Identificador único del producto.
nombre: Nombre del producto.
precio: Precio unitario.
stock: Cantidad disponible en inventario.
🔹 Relación: Un producto puede estar en múltiples carritos (1:N con CarritoProducto).
3️⃣ CarritoProducto (Tabla Intermedia)
id (PK): Identificador único de la relación Carrito - Producto.
carrito_id (FK): Referencia al Carrito.
producto_id (FK): Referencia al Producto.
cantidad: Número de unidades de un producto en el carrito.
🔹 Relación: Administra la relación N:M entre Carrito y Producto.
Ejecución de Pruebas Unitarias
Para ejecutar las pruebas, usa:

bash
Copiar
Editar
mvn test
Esto ejecutará las pruebas implementadas con JUnit y Mockito.

Endpoints Principales
Método	Endpoint	Descripción
POST	/cart/{carritoId}/add-product?productoId=1&cantidad=2	Agregar un producto al carrito
PUT	/cart/update-quantity?carritoProductoId=1&cantidad=5	Actualizar cantidad de un producto en el carrito
DELETE	/cart/remove-product?carritoProductoId=1	Eliminar un producto del carrito
GET	/cart/{carritoId}	Ver el contenido del carrito
POST	/cart/{carritoId}/apply-coupon?couponCode=DESC10	Aplicar un cupón de descuento
Gestión de Descuentos y Validaciones
El sistema maneja dos tipos de descuentos:

1️⃣ Descuento de Temporada
Los productos tienen un descuento basado en la época del año:

Primer semestre (enero - junio): 5% de descuento.
Segundo semestre (julio - diciembre): 15% de descuento.
2️⃣ Cupones de Descuento
Los usuarios pueden aplicar códigos de descuento:

DESC10 → 10% de descuento.
DESC20 → 20% de descuento.
Todos los descuentos se calculan dinámicamente en DiscountUtils.java.

Contacto y Soporte
Desarrollador: Jhon Heiler
GitHub: JhonHeiler
Correo: heylerty7@gmail.com
