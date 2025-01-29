# Carrito de Compras - API REST

Este proyecto es una API RESTful desarrollada con Java Spring Boot, que permite gestionar un carrito de compras, incluyendo la adición de productos, actualización de cantidades, eliminación de productos, aplicación de descuentos y persistencia en PostgreSQL. Además, se proporciona un entorno Dockerizado para facilitar su ejecución.

## 💻 Tecnologías Utilizadas
- **Java 17**
- **Spring Boot 3.4.2**
- **PostgreSQL 13**
- **Docker & Docker Compose**
- **Lombok**
- **MapStruct**
- **SpringDoc OpenAPI (para documentación Swagger)**
- **JUnit & Mockito (para pruebas unitarias)**

## 🔧 Prerrequisitos
Antes de instalar y ejecutar el proyecto, asegúrate de contar con los siguientes programas instalados:
- Java 17
- Maven
- Docker & Docker Compose
- PostgreSQL 13+
- Postman (opcional, para probar los endpoints de la API)

## 🛠️ Configuración del Proyecto

### 1️⃣ Clonar el Repositorio
```bash
git clone https://github.com/usuario/carrito-api.git
cd carrito-api
```

### 2️⃣ Configuración de la Base de Datos

#### Opción 1: Configuración Manual (PostgreSQL local)
Crear la base de datos en PostgreSQL:
```sql
CREATE DATABASE carrito;
```
Configurar las credenciales en el archivo `application.yml`:
```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/carrito
    username: postgres
    password: password
  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true
```

#### Opción 2: Configuración con Docker
Si prefieres ejecutar PostgreSQL en un contenedor, usa el siguiente comando:
```bash
docker-compose up -d
```
Esto iniciará un contenedor de PostgreSQL con:
- **Usuario:** postgres
- **Contraseña:** password
- **Base de datos:** carrito
- **Puerto:** 5432

## 🔄 Ejecución del Proyecto

### Opción 1: Con Maven
Si ya tienes PostgreSQL configurado, ejecuta:
```bash
mvn spring-boot:run
```

### Opción 2: Con Docker (API + BD en Contenedor)
```bash
docker-compose up --build
```
La API estará disponible en:
```
http://localhost:8080
```

## 📃 Documentación de la API (Swagger)
Una vez ejecutado el proyecto, puedes acceder a la documentación de los endpoints en:
- **Swagger UI:** [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)
- **OpenAPI JSON:** [http://localhost:8080/v3/api-docs](http://localhost:8080/v3/api-docs)

## 📚 Estructura del Proyecto
```
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
```

## 📊 Entidades y Relaciones

![Diagrama ER](https://github.com/user-attachments/assets/c9ca49d3-74ba-4e2b-963f-ab98534b7073)

### 🔎 Descripción de Tablas y Relaciones
#### **shoppingcarts (Carritos de Compra)**
- `id`: Identificador único del carrito.
- `total`: Monto total del carrito.
- `userEmail`: Correo electrónico del usuario asociado al carrito.

#### **products (Productos)**
- `id`: Identificador único del producto.
- `name`: Nombre del producto.
- `price`: Precio unitario del producto.
- `withSeasonalDiscount`: Indica si el producto tiene descuento de temporada (`TRUE` o `FALSE`).

#### **seasonaldiscounts (Descuentos Estacionales)**
- `id`: Identificador único del descuento.
- `name`: Nombre de la temporada de descuento.
- `startDate`: Fecha de inicio del descuento.
- `endDate`: Fecha de finalización del descuento.
- `discount`: Porcentaje de descuento aplicado durante la temporada.

#### **shoppingcart_x_products (Relación entre Carritos y Productos)**
- `id`: Identificador único de la relación.
- `shoppingCartId`: Referencia al carrito donde se añadió el producto.
- `productId`: Referencia al producto añadido al carrito.
- `quantity`: Cantidad de unidades del producto en el carrito.
- `discount`: Descuento aplicado al producto en este carrito.
- `couponCode`: Código de cupón aplicado al producto (si existe).

#### **coupons (Cupones de Descuento)**
- `id`: Identificador único del cupón.
- `code`: Código único del cupón (Combinación de letras y números de 8 caracteres).
- `amount`: Valor del descuento aplicado por el cupón.
- `state`: Estado del cupón (`valid` si está disponible, `used` si ya ha sido utilizado).

## ⚡ Endpoints Principales

| Método  | Endpoint  | Descripción |
|---------|----------|-------------|
| `POST`  | `/cart/{carritoId}/add-product?productoId=1&cantidad=2` | Agregar un producto al carrito |
| `PUT`   | `/cart/update-quantity?carritoProductoId=1&cantidad=5` | Actualizar cantidad de un producto en el carrito |
| `DELETE` | `/cart/remove-product?carritoProductoId=1` | Eliminar un producto del carrito |
| `GET`   | `/cart/{carritoId}` | Ver el contenido del carrito |
| `POST`  | `/cart/{carritoId}/apply-coupon?couponCode=DESC10` | Aplicar un cupón de descuento |

## 📢 Contacto y Soporte
- **Desarrollador:** Jhon Heiler
- **GitHub:** [JhonHeiler](https://github.com/JhonHeiler)
- **Correo:** heylerty7@gmail.com  

🚀 ¡Esperamos que disfrutes usando esta API!
