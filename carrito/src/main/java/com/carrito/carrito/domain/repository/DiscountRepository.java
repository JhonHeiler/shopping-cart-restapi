package com.carrito.carrito.domain.repository;

import com.carrito.carrito.domain.model.SeasonalDiscount;

import java.util.List;

public interface DiscountRepository {
    List<SeasonalDiscount> findAllByProductId(Long productId);
}