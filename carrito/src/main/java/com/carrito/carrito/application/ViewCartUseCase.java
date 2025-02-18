package com.carrito.carrito.application;

import com.carrito.carrito.domain.model.Cart;
import com.carrito.carrito.domain.repository.CartRepository;
import org.springframework.stereotype.Service;

@Service
public class ViewCartUseCase {

    private final CartRepository cartRepository;

    public ViewCartUseCase(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    public Cart execute(Long userId) {
        Cart cart = cartRepository.findActiveCartByUser(userId);
        if (cart == null) {
            throw new RuntimeException("No existe un carrito activo para este usuario");
        }
        return cart;
    }
}