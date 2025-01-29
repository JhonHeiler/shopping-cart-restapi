package com.carrito.carrito.application.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.carrito.carrito.domain.model.Carrito;
import com.carrito.carrito.domain.model.Producto;
import com.carrito.carrito.domain.model.CarritoProducto;
import com.carrito.carrito.infrastructure.repository.CartRepository;
import com.carrito.carrito.utils.DiscountUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class CartServiceTest {

    @Mock
    private CartRepository cartRepository;

    @InjectMocks
    private CartService cartService;

    private Carrito carrito;
    private Producto producto;

    @BeforeEach
    void setUp() {
        carrito = new Carrito();
        carrito.setId(1L);
        carrito.setTotal(BigDecimal.ZERO);

        producto = new Producto();
        producto.setId(1L);
        producto.setNombre("Producto Test");
        producto.setPrecio(new BigDecimal("100.00"));
    }

    @Test
    void testCreateCart() {
        when(cartRepository.save(any(Carrito.class))).thenReturn(carrito);
        Carrito result = cartService.createCart();

        assertNotNull(result);
        assertEquals(BigDecimal.ZERO, result.getTotal());
        verify(cartRepository, times(1)).save(any(Carrito.class));
    }

    @Test
    void testGetCartWithDiscount_Success() {
        CarritoProducto carritoProducto = new CarritoProducto();
        carritoProducto.setProducto(producto);
        carritoProducto.setCantidad(2);

        carrito.setProductos(List.of(carritoProducto));
        when(cartRepository.findById(1L)).thenReturn(Optional.of(carrito));
        when(DiscountUtils.calculateSeasonalDiscount("Producto Test")).thenReturn(0.10); // 10% de descuento

        Carrito result = cartService.getCartWithDiscount(1L);

        BigDecimal expectedTotal = new BigDecimal("180.00"); // (100 - 10%) * 2 = 180
        assertEquals(expectedTotal, result.getTotal());
    }

    @Test
    void testGetCartWithDiscount_CartNotFound() {
        when(cartRepository.findById(1L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> cartService.getCartWithDiscount(1L));
        assertEquals("Carrito no encontrado", exception.getMessage());
    }
}