package com.carrito.carrito.application.usecase;

import com.carrito.carrito.domain.model.CarritoProducto;
import com.carrito.carrito.infrastructure.repository.CartProductRepository;
import com.carrito.carrito.utils.ValidationUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateQuantityUseCase {
    private final CartProductRepository cartProductRepository;

    public void updateQuantity(Long carritoProductoId, int cantidad) {
        ValidationUtils.validateQuantity(cantidad); // ✅ Validamos cantidad

        CarritoProducto carritoProducto = cartProductRepository.findById(carritoProductoId)
                .orElseThrow(() -> new RuntimeException("Relación carrito-producto no encontrada"));

        if (cantidad == 0) {
            cartProductRepository.delete(carritoProducto);
        } else {
            carritoProducto.setCantidad(cantidad);
            cartProductRepository.save(carritoProducto);
        }
    }
}
