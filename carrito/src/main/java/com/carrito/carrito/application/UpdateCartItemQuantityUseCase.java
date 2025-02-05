package com.carrito.carrito.application;

import com.carrito.carrito.domain.model.Cart;
import com.carrito.carrito.domain.repository.CartRepository;
import org.springframework.stereotype.Service;

@Service
public class UpdateCartItemQuantityUseCase {

    private final CartRepository cartRepository;

    public UpdateCartItemQuantityUseCase(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    public Cart execute(Long userId, Long productId, int newQuantity) {
        Cart cart = cartRepository.findActiveCartByUser(userId);
        if (cart == null) {
            throw new RuntimeException("No existe un carrito activo para este usuario");
        }

        cart.updateItemQuantity(productId, newQuantity);

        return cartRepository.save(cart);
    }
}