package com.carrito.carrito.infrastructure.persistence;

import com.carrito.carrito.infrastructure.persistence.entity.CartEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringDataCartRepository extends JpaRepository<CartEntity, Long> {
    CartEntity findByUserIdAndStatus(Long userId, String status);
}