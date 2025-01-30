package com.carrito.carrito.domain.repository;

import com.carrito.carrito.domain.model.Cart;

public interface CartRepository {
    Cart findActiveCartByUser(Long userId);
    Cart save(Cart cart);
}
