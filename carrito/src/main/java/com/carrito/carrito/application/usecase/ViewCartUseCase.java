package com.carrito.carrito.application.usecase;

import com.carrito.carrito.application.service.CartService;
import com.carrito.carrito.domain.dto.CarritoDTO;
import com.carrito.carrito.infrastructure.mapper.CartMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ViewCartUseCase {
    private final CartService cartService;

    public CarritoDTO viewCart(Long carritoId) {
        var carrito = cartService.getCartWithDiscount(carritoId);
        return CartMapper.toDto(carrito);
    }
}
