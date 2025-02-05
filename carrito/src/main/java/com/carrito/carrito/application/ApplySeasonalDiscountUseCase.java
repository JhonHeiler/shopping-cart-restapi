package com.carrito.carrito.application;

import com.carrito.carrito.domain.model.Cart;
import com.carrito.carrito.domain.model.CartItem;
import com.carrito.carrito.domain.repository.CartRepository;
import com.carrito.carrito.domain.service.DiscountCalculatorService;
import org.springframework.stereotype.Service;

@Service
public class ApplySeasonalDiscountUseCase {

    private final CartRepository cartRepository;
    private final DiscountCalculatorService discountService;

    public ApplySeasonalDiscountUseCase(CartRepository cartRepository,
                                        DiscountCalculatorService discountService) {
        this.cartRepository = cartRepository;
        this.discountService = discountService;
    }

    public Cart execute(Long userId) {
        Cart cart = cartRepository.findActiveCartByUser(userId);

        if (cart == null) {
            throw new RuntimeException("No existe un carrito activo para este usuario");
        }

        // Recalcular descuentos para cada item
        for (CartItem item : cart.getItems()) {
            double newDiscount = discountService.calculateSeasonalDiscount(item.getProductId(), item.getPrice());
            item.setDiscountApplied(newDiscount);
        }

        return cartRepository.save(cart);
    }
}