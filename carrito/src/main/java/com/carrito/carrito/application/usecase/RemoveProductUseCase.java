package com.carrito.carrito.application.usecase;

import com.carrito.carrito.domain.model.CarritoProducto;
import com.carrito.carrito.infrastructure.repository.CartProductRepository;
import com.carrito.carrito.infrastructure.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class RemoveProductUseCase {
    private final CartProductRepository cartProductRepository;
    private final CartRepository cartRepository;

    public void removeProduct(Long carritoProductoId) {
        CarritoProducto carritoProducto = cartProductRepository.findById(carritoProductoId)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado en el carrito"));

        var carrito = carritoProducto.getCarrito();

        cartProductRepository.delete(carritoProducto);

        BigDecimal total = carrito.getProductos().stream()
                .filter(cp -> !cp.equals(carritoProducto))
                .map(cp -> cp.getProducto().getPrecio().multiply(BigDecimal.valueOf(cp.getCantidad())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        carrito.setTotal(total);
        cartRepository.save(carrito);
    }
}
