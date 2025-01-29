package com.carrito.carrito.infrastructure.repository;

import com.carrito.carrito.domain.model.Carrito;
import com.carrito.carrito.domain.model.CarritoProducto;
import com.carrito.carrito.domain.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CartProductRepository extends JpaRepository<CarritoProducto, Long> {
    @Query("SELECT cp FROM CarritoProducto cp WHERE cp.carrito = :carrito AND cp.producto = :producto")
    Optional<CarritoProducto> findByCarritoAndProducto(@Param("carrito") Carrito carrito, @Param("producto") Producto producto);
}