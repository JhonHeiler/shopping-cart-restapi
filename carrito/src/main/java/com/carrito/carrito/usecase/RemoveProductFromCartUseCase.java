package com.carrito.carrito.usecase;

import com.carrito.carrito.domain.model.Cart;
import com.carrito.carrito.domain.repository.CartRepository;
import org.springframework.stereotype.Service;

@Service
public class RemoveProductFromCartUseCase {

    private final CartRepository cartRepository;

    public RemoveProductFromCartUseCase(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    public Cart execute(Long userId, Long productId) {
        Cart cart = cartRepository.findActiveCartByUser(userId);
        if (cart == null) {
            throw new RuntimeException("No existe un carrito activo para este usuario");
        }

        // Eliminar producto
        cart.removeItem(productId);

        // Guardar
        return cartRepository.save(cart);
    }
}