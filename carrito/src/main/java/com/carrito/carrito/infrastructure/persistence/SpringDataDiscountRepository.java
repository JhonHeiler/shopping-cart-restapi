package com.carrito.carrito.infrastructure.persistence;

import com.carrito.carrito.infrastructure.persistence.entity.SeasonalDiscountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpringDataDiscountRepository extends JpaRepository<SeasonalDiscountEntity, Long> {

    List<SeasonalDiscountEntity> findAllByProductId(Long productId);
}