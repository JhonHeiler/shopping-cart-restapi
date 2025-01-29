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
    private final CartMapper cartMapper;

    public CarritoDTO viewCart(Long carritoId) {
        var carrito = cartService.getCartWithDiscount(carritoId);
        return cartMapper.toDto(carrito);
    }
}
