package com.carrito.carrito.application.service;

import com.carrito.carrito.domain.model.Carrito;
import com.carrito.carrito.domain.model.CarritoProducto;
import com.carrito.carrito.domain.model.Producto;
import com.carrito.carrito.infrastructure.repository.CartRepository;
import com.carrito.carrito.utils.DiscountUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class CartService {
    private final CartRepository carritoRepository;

    public Carrito createCart() {
        Carrito carrito = new Carrito();
        carrito.setTotal(BigDecimal.ZERO);
        return carritoRepository.save(carrito);
    }

    public Carrito getCartWithDiscount(Long carritoId) {
        Carrito carrito = carritoRepository.findById(carritoId)
                .orElseThrow(() -> new RuntimeException("Carrito no encontrado"));

        BigDecimal total = BigDecimal.ZERO;

        for (CarritoProducto item : carrito.getProductos()) {
            Producto producto = item.getProducto();
            BigDecimal discount = BigDecimal.valueOf(DiscountUtils.calculateSeasonalDiscount(producto.getNombre()));
            BigDecimal finalPrice = producto.getPrecio().multiply(BigDecimal.ONE.subtract(discount));
            total = total.add(finalPrice.multiply(BigDecimal.valueOf(item.getCantidad())));
        }

        carrito.setTotal(total);
        return carrito;
    }
}
