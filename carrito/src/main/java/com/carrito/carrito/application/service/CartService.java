package com.carrito.carrito.application.service;
import com.carrito.carrito.domain.model.Carrito;
import com.carrito.carrito.domain.model.Producto;
import com.carrito.carrito.infrastructure.repository.CartRepository;
import com.carrito.carrito.utils.DiscountUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cartRepository;

    public Carrito createCart() {
        Carrito carrito = new Carrito();
        carrito.setTotal(BigDecimal.ZERO);
        return cartRepository.save(carrito);
    }

    public Carrito getCartWithDiscount(Long carritoId) {
        Carrito carrito = cartRepository.findById(carritoId)
                .orElseThrow(() -> new RuntimeException("Carrito no encontrado"));

        BigDecimal total = carrito.getProductos().stream()
                .map(item -> {
                    Producto producto = item.getProducto();
                    BigDecimal discount = BigDecimal.valueOf(DiscountUtils.calculateSeasonalDiscount(producto.getNombre()));
                    BigDecimal finalPrice = producto.getPrecio().multiply(BigDecimal.ONE.subtract(discount));
                    return finalPrice.multiply(BigDecimal.valueOf(item.getCantidad()));
                })
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        carrito.setTotal(total);
        return carrito;
    }
}
