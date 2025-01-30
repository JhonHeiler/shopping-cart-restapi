# 🛒 Carrito de Compras - API REST

¡Bienvenido al **Carrito de Compras API**! 🚀
Esta API RESTful, desarrollada con **Java Spring Boot**, permite gestionar un carrito de compras, incluyendo la adición de productos, actualización de cantidades, eliminación de productos, aplicación de descuentos y persistencia en **PostgreSQL**. También se proporciona un entorno **Dockerizado** para facilitar su ejecución.

---

## 💻 Tecnologías Utilizadas

- **Java 17**
- **Spring Boot 3.4.2**
- **PostgreSQL 13**
- **Docker & Docker Compose**
- **Lombok**
- **SpringDoc OpenAPI (Swagger para documentación)**
- **JUnit & Mockito (para pruebas unitarias)**

---

## 📌 Requisitos Previos
Antes de instalar y ejecutar el proyecto, asegúrate de contar con lo siguiente:

- **Java 17**
- **Maven**
- **Docker & Docker Compose**
- **PostgreSQL 13+**
- **Postman (opcional, para probar los endpoints)**

---

## 🚀 Instalación y Configuración

### 🔹 1. Clonar el Repositorio
```bash
git clone https://github.com/usuario/carrito-api.git
cd carrito-api
```

### 🔹 2. Configuración de la Base de Datos

#### ✅ Opción 1: Configuración Manual (PostgreSQL Local)
Crea la base de datos en PostgreSQL:
```sql
CREATE DATABASE carrito;
```
Configura las credenciales en `application.yml`:
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

#### ✅ Opción 2: Configuración con Docker
Para ejecutar PostgreSQL en un contenedor, usa:
```bash
docker-compose up -d
```
Esto iniciará un contenedor con:
- **Usuario:** postgres
- **Contraseña:** password
- **Base de datos:** shoppingcart
- **Puerto:** 5432

---

## 🔄 Ejecución del Proyecto

### ▶️ Opción 1: Con Maven
Si PostgreSQL ya está configurado, ejecuta:
```bash
mvn spring-boot:run
```

### ▶️ Opción 2: Con Docker (API + BD en Contenedor)
```bash
docker-compose up --build
```
La API estará disponible en:
```
http://localhost:8080
```

---

## 📃 Documentación de la API (Swagger)
Accede a la documentación interactiva en:
- **Swagger UI:** [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)
- **OpenAPI JSON:** [http://localhost:8080/v3/api-docs](http://localhost:8080/v3/api-docs)

---

## 📂 Estructura del Proyecto
```
src
 ├── main
 │    ├── java
 │    │    └── com.example.shoppingcart
 │    │         ├── domain
 │    │         │    ├── model
 │    │         │    ├── repository
 │    │         │    ├── service
 │    │         ├── usecase
 │    │         ├── infrastructure
 │    │         ├── controller
 │    │         └── ShoppingCartApplication.java
 │    └── resources
 │         └── application.yml
 └── test
      └── java
           └── com.example.shoppingcart
```

---

## 📊 Entidades y Relaciones

![image](https://github.com/user-attachments/assets/1376bfd5-b8b4-42ea-ac04-fdf55bda5b54)

### 🔎 Descripción de Tablas
```sql
CREATE TABLE IF NOT EXISTS users (
    user_id     SERIAL PRIMARY KEY,
    username    VARCHAR(50) UNIQUE NOT NULL,
    password    VARCHAR(200) NOT NULL,
    email       VARCHAR(100) UNIQUE NOT NULL,
    created_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS products (
    product_id  SERIAL PRIMARY KEY,
    name        VARCHAR(100) NOT NULL,
    description TEXT,
    price       NUMERIC(10,2) NOT NULL,
    created_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS carts (
    cart_id     SERIAL PRIMARY KEY,
    user_id     INT NOT NULL,
    status      VARCHAR(20) NOT NULL DEFAULT 'ACTIVE',
    created_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_cart_user FOREIGN KEY (user_id) REFERENCES users (user_id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS cart_items (
    cart_item_id     SERIAL PRIMARY KEY,
    cart_id          INT NOT NULL,
    product_id       INT NOT NULL,
    quantity         INT NOT NULL CHECK (quantity >= 0),
    price            NUMERIC(10,2) NOT NULL,
    discount_applied NUMERIC(10,2) NOT NULL DEFAULT 0.00,
    created_at       TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_item_cart FOREIGN KEY (cart_id) REFERENCES carts (cart_id) ON DELETE CASCADE,
    CONSTRAINT fk_item_product FOREIGN KEY (product_id) REFERENCES products (product_id) ON DELETE CASCADE
);
```

---

## 🔥 Endpoints Principales

| Método  | Endpoint                 | Descripción |
|---------|--------------------------|-------------|
| `POST`  | `/cart/add`               | Agregar un producto al carrito |
| `PUT`   | `/cart/update`            | Actualizar cantidad de un producto |
| `DELETE`| `/cart/remove`            | Eliminar un producto del carrito |
| `GET`   | `/cart/view`              | Ver el contenido del carrito |
| `POST`  | `/cart/apply-coupon`      | Aplicar un cupón de descuento |

---

## 📢 Contacto y Soporte
- **Desarrollador:** Jhon Heiler
- **GitHub:** [JhonHeiler](https://github.com/JhonHeiler)
- **Correo:** heylerty7@gmail.com

🚀 ¡Esperamos que disfrutes usando esta API!
