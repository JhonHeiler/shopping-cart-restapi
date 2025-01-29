package com.carrito.carrito.application.usecase;
import com.carrito.carrito.domain.model.Carrito;
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
        // Buscar la relación CarritoProducto
        CarritoProducto carritoProducto = cartProductRepository.findById(carritoProductoId)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado en el carrito"));

        // Obtener el carrito asociado
        Carrito carrito = carritoProducto.getCarrito();

        // Eliminar el producto de la relación
        cartProductRepository.delete(carritoProducto);

        // Recalcular el total del carrito
        BigDecimal total = carrito.getProductos().stream()
                .filter(cp -> !cp.equals(carritoProducto)) // Excluir el producto eliminado
                .map(cp -> cp.getProducto().getPrecio().multiply(BigDecimal.valueOf(cp.getCantidad())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        carrito.setTotal(total);

        // Guardar los cambios en el carrito
        cartRepository.save(carrito);
    }
}
