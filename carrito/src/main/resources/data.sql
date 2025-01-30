-- ============================================================
-- 1) Tabla de usuarios
-- ============================================================
CREATE TABLE IF NOT EXISTS users (
                                     user_id     SERIAL PRIMARY KEY,
                                     username    VARCHAR(50) UNIQUE NOT NULL,
    password    VARCHAR(200) NOT NULL,
    email       VARCHAR(100) UNIQUE NOT NULL,
    created_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP
    );

-- ============================================================
-- 2) Tabla de productos
-- ============================================================
CREATE TABLE IF NOT EXISTS products (
                                        product_id  SERIAL PRIMARY KEY,
                                        name        VARCHAR(100) NOT NULL,
    description TEXT,
    price       NUMERIC(10,2) NOT NULL,
    created_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP
    );

-- ============================================================
-- 3) Tabla de carritos
--     - Un usuario puede tener un carrito activo;
--       el estatus 'ACTIVE' indica el carrito en uso.
-- ============================================================
CREATE TABLE IF NOT EXISTS carts (
                                     cart_id     SERIAL PRIMARY KEY,
                                     user_id     INT NOT NULL,
                                     status      VARCHAR(20) NOT NULL DEFAULT 'ACTIVE',
    created_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_cart_user
    FOREIGN KEY (user_id)
    REFERENCES users (user_id)
    ON DELETE CASCADE
    );

-- ============================================================
-- 4) Tabla de items del carrito
--     - price: Precio unitario al momento de agregar al carrito
--              (para mantener histórico si el precio original cambia)
--     - discount_applied: Descuento acumulado aplicado a ese producto.
-- ============================================================
CREATE TABLE IF NOT EXISTS cart_items (
                                          cart_item_id     SERIAL PRIMARY KEY,
                                          cart_id          INT NOT NULL,
                                          product_id       INT NOT NULL,
                                          quantity         INT NOT NULL CHECK (quantity >= 0),
    price            NUMERIC(10,2) NOT NULL,
    discount_applied NUMERIC(10,2) NOT NULL DEFAULT 0.00,
    created_at       TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at       TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_item_cart
    FOREIGN KEY (cart_id)
    REFERENCES carts (cart_id)
    ON DELETE CASCADE,
    CONSTRAINT fk_item_product
    FOREIGN KEY (product_id)
    REFERENCES products (product_id)
    ON DELETE CASCADE
    );

-- ============================================================
-- 5) Tabla de cupones
--     - usage_limit: número de veces que se puede usar el cupón en total.
--     - used_count: veces que ha sido usado.
--     (Se puede crear una tabla intermedia user_coupon para
--     controlar el uso por usuario, si se requiere)
-- ============================================================
CREATE TABLE IF NOT EXISTS coupons (
                                       coupon_id        SERIAL PRIMARY KEY,
                                       code             VARCHAR(50) UNIQUE NOT NULL,
    description      TEXT,
    discount_percentage NUMERIC(5,2) NOT NULL,
    valid_from       DATE NOT NULL,
    valid_to         DATE NOT NULL,
    usage_limit      INT NOT NULL DEFAULT 1,
    used_count       INT NOT NULL DEFAULT 0,
    created_at       TIMESTAMP DEFAULT CURRENT_TIMESTAMP
    );

-- ============================================================
-- 6) Tabla para descuentos de temporada
--     - Define periodos de meses en los que se aplica descuento
--       específico a un producto.
-- ============================================================
CREATE TABLE IF NOT EXISTS seasonal_discounts (
                                                  seasonal_discount_id SERIAL PRIMARY KEY,
                                                  product_id           INT NOT NULL,
                                                  start_month          INT NOT NULL CHECK (start_month BETWEEN 1 AND 12),
    end_month            INT NOT NULL CHECK (end_month BETWEEN 1 AND 12),
    discount_percentage  NUMERIC(5,2) NOT NULL,
    CONSTRAINT fk_seasonal_product
    FOREIGN KEY (product_id)
    REFERENCES products (product_id)
    ON DELETE CASCADE
    );

-- ============================================================
-- OPCIONAL: Índices
-- ============================================================
CREATE INDEX idx_cart_user ON carts (user_id);
CREATE INDEX idx_cartitem_cart ON cart_items (cart_id);
CREATE INDEX idx_cartitem_product ON cart_items (product_id);
CREATE INDEX idx_coupon_code ON coupons (code);
CREATE INDEX idx_product_name ON products (name);
