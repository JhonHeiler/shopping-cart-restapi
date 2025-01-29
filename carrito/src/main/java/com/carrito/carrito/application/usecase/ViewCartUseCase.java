package com.carrito.carrito.application.usecase;

import com.carrito.carrito.domain.dto.CarritoDTO;
import com.carrito.carrito.domain.model.Carrito;
import com.carrito.carrito.infrastructure.mapper.CartMapper;
import com.carrito.carrito.infrastructure.repository.CartRepository;
import com.carrito.carrito.utils.DiscountUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class ViewCartUseCase {
    private final CartRepository cartRepository;
    private final CartMapper cartMapper; // ✅ Inyectamos el mapeador

    public CarritoDTO viewCart(Long carritoId) {
        Carrito carrito = cartRepository.findById(carritoId)
                .orElseThrow(() -> new RuntimeException("Carrito no encontrado"));

        // Recalcular el total aplicando descuentos
        BigDecimal total = carrito.getProductos().stream()
                .map(item -> {
                    BigDecimal discount = BigDecimal.valueOf(DiscountUtils.calculateSeasonalDiscount(item.getProducto().getNombre()));
                    BigDecimal finalPrice = item.getProducto().getPrecio().multiply(BigDecimal.ONE.subtract(discount));
                    return finalPrice.multiply(BigDecimal.valueOf(item.getCantidad()));
                })
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        carrito.setTotal(total);

        return cartMapper.toDto(carrito);
    }
}
