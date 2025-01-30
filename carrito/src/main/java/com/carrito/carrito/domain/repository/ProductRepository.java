package com.carrito.carrito.domain.repository;
import com.carrito.carrito.domain.model.Product;

public interface ProductRepository {
    Product findById(Long productId);
}