package com.carrito.carrito.infrastructure.repository;


import com.carrito.carrito.domain.model.Carrito;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Carrito, Long> {
}