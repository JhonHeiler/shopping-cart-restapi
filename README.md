# 🛒 Carrito de Compras - API REST

Este proyecto es una API REST de Carrito de Compras desarrollada en **Java Spring Boot** con **PostgreSQL** como base de datos y usando **Docker** para su despliegue.

---

## 🚀 Tecnologías Utilizadas

- **Java 17**
- **Spring Boot 3.4.2**
- **PostgreSQL 13**
- **Docker & Docker Compose**
- **Lombok**
- **MapStruct**
- **SpringDoc OpenAPI** (para documentación Swagger)
- **JUnit & Mockito** (para pruebas unitarias)

---

## 📌 Prerrequisitos

Antes de instalar y ejecutar el proyecto, asegúrate de tener instalados los siguientes programas:

- [Java 17](https://adoptium.net/)
- [Maven](https://maven.apache.org/download.cgi)
- [Docker & Docker Compose](https://www.docker.com/get-started)
- [PostgreSQL 13+](https://www.postgresql.org/download/)

Opcionalmente, puedes usar **Postman** para probar los endpoints.

---

## ⚙️ Configuración del Proyecto

### 1️⃣ Clonar el repositorio

```bash
  git clone https://github.com/usuario/carrito-api.git
  cd carrito-api
```

### 2️⃣ Configurar la base de datos

Si prefieres usar PostgreSQL localmente:

1. Crear la base de datos manualmente:

   ```sql
   CREATE DATABASE carrito;
   ```

2. Configurar las credenciales en `application.yml`:

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

Si prefieres usar **Docker**, ve al siguiente paso.

### 3️⃣ Configurar con Docker

Ejecutar el siguiente comando en la raíz del proyecto:

```bash
  docker-compose up -d
```

Esto iniciará PostgreSQL en un contenedor con los siguientes datos:

- **Usuario**: `postgres`
- **Contraseña**: `password`
- **Base de datos**: `carrito`
- **Puerto**: `5432`

---

## ▶️ Ejecutar el Proyecto

### Opción 1: Con Maven

Si PostgreSQL ya está configurado, ejecuta el siguiente comando en la raíz del proyecto:

```bash
  mvn spring-boot:run
```

### Opción 2: Con Docker (Incluye la API y la BD)

Si prefieres ejecutar la API y la base de datos en Docker:

```bash
  docker-compose up --build
```

La API estará disponible en: `http://localhost:8080`

---

## 📖 Documentación de la API

La documentación de los endpoints está disponible en Swagger:

- **Swagger UI**: [`http://localhost:8080/swagger-ui.html`](http://localhost:8080/swagger-ui.html)
- **OpenAPI JSON**: [`http://localhost:8080/v3/api-docs`](http://localhost:8080/v3/api-docs)

---

![img_1.png](img_1.png)

Explicación del Diagrama Entidad-Relación (DER) del Carrito de Compras 🛒
El diagrama muestra la estructura y las relaciones entre las entidades principales de la base de datos del sistema de carrito de compras. Aquí está la explicación detallada:

📌 Entidades y Relaciones
Carrito (🛒)

id (PK): Identificador único del carrito.
usuario_id: Identificador del usuario al que pertenece el carrito.
total: Monto total de los productos en el carrito.
🔹 Relación:

Un Carrito puede contener múltiples productos, por lo que se establece una relación 1:N con CarritoProducto.
Producto (📦)

id (PK): Identificador único del producto.
nombre: Nombre del producto.
precio: Precio unitario del producto.
stock: Cantidad disponible en inventario.
🔹 Relación:

Un Producto puede estar en varios carritos al mismo tiempo, estableciendo una relación 1:N con CarritoProducto.
CarritoProducto (Intermedia)

id (PK): Identificador único de la relación entre Carrito y Producto.
carrito_id (FK): Clave foránea que referencia al Carrito.
producto_id (FK): Clave foránea que referencia al Producto.
cantidad: Número de unidades del producto en el carrito.
🔹 Función:

Actúa como una tabla intermedia para la relación muchos a muchos (N:M) entre Carrito y Producto.
Un carrito puede tener varios productos, y un producto puede estar en varios carritos.
🔗 Relaciones en el Diagrama
Carrito → CarritoProducto → (1:N) → Un carrito puede contener múltiples productos.
Producto → CarritoProducto → (1:N) → Un producto puede estar en varios carritos.
🛠 Consideraciones Técnicas
Las claves primarias (PK) garantizan la unicidad de cada registro.
Las claves foráneas (FK) mantienen la integridad referencial entre las entidades.
La entidad CarritoProducto maneja la cantidad de cada producto dentro del carrito.

## ✅ Ejecutar Pruebas Unitarias

Ejecuta las pruebas unitarias con el siguiente comando:

```bash
  mvn test
```

Esto ejecutará las pruebas con **JUnit y Mockito**.

---

## 🛠️ Endpoints Principales

| Método | Endpoint | Descripción |
|--------|------------------------------|--------------------------------|
| `POST` | `/cart/{carritoId}/add-product?productoId=1&cantidad=2` | Agregar un producto al carrito |
| `PUT`  | `/cart/update-quantity?carritoProductoId=1&cantidad=5` | Actualizar cantidad de un producto en el carrito |
| `DELETE` | `/cart/remove-product?carritoProductoId=1` | Eliminar un producto del carrito |
| `GET` | `/cart/{carritoId}` | Ver el contenido del carrito |
| `POST` | `/cart/{carritoId}/apply-coupon?couponCode=DESC10` | Aplicar un cupón de descuento |

---



## 🏆 Autor

- **Nombre:** Jhon heiler
- **GitHub:** https://github.com/JhonHeiler
- **Correo:** heylerty7@gmail.com

---



## 🚀 ¡Listo para usar!

Ahora puedes comenzar a usar la API de Carrito de Compras. Para cualquier problema, abre un **issue** en GitHub.

