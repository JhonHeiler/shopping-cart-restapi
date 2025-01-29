package com.carrito.carrito.application.usecase;

import com.carrito.carrito.domain.exception.CarritoNotFoundException;
import com.carrito.carrito.domain.exception.ProductoNotFoundException;
import com.carrito.carrito.domain.model.Carrito;
import com.carrito.carrito.domain.model.CarritoProducto;
import com.carrito.carrito.domain.model.Producto;
import com.carrito.carrito.infrastructure.repository.CartRepository;
import com.carrito.carrito.infrastructure.repository.ProductRepository;
import com.carrito.carrito.infrastructure.repository.CartProductRepository;
import com.carrito.carrito.utils.ValidationUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddProductUseCase {
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final CartProductRepository cartProductRepository;

    public void addProduct(Long carritoId, Long productoId, int cantidad) {
        ValidationUtils.validateQuantity(cantidad);

        Carrito carrito = cartRepository.findById(carritoId)
                .orElseThrow(() -> new CarritoNotFoundException("Carrito no encontrado"));

        Producto producto = productRepository.findById(productoId)
                .orElseThrow(() -> new ProductoNotFoundException("Producto no encontrado"));

        // Buscar si el producto ya está en el carrito
        CarritoProducto carritoProducto = cartProductRepository.findByCarritoAndProducto(carrito, producto)
                .orElseGet(() -> {
                    CarritoProducto nuevoCarritoProducto = new CarritoProducto();
                    nuevoCarritoProducto.setCarrito(carrito);
                    nuevoCarritoProducto.setProducto(producto);
                    nuevoCarritoProducto.setCantidad(0);
                    return nuevoCarritoProducto;
                });

        // Sumar la cantidad
        carritoProducto.setCantidad(carritoProducto.getCantidad() + cantidad);
        cartProductRepository.save(carritoProducto);
    }
}
