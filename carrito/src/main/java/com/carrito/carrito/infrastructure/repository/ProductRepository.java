package com.carrito.carrito.infrastructure.repository;

import com.carrito.carrito.domain.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Producto, Long> {
}