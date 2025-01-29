package com.carrito.carrito.application.service;

import com.carrito.carrito.domain.model.Carrito;
import com.carrito.carrito.domain.model.CarritoProducto;
import com.carrito.carrito.domain.model.Producto;
import com.carrito.carrito.infrastructure.repository.CartRepository;
import com.carrito.carrito.utils.DiscountUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CartServiceTest {

    @Mock
    private CartRepository cartRepository;

    @InjectMocks
    private CartService cartService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createCart_ShouldReturnNewCart() {
        // Arrange
        Carrito expectedCart = new Carrito();
        expectedCart.setTotal(BigDecimal.ZERO);

        when(cartRepository.save(any(Carrito.class))).thenReturn(expectedCart);

        // Act
        Carrito createdCart = cartService.createCart();

        // Assert
        assertNotNull(createdCart);
        assertEquals(BigDecimal.ZERO, createdCart.getTotal());
        verify(cartRepository, times(1)).save(any(Carrito.class));
    }

    @Test
    void getCartWithDiscount_ShouldCalculateTotalCorrectly() {
        // Arrange
        Carrito carrito = new Carrito();
        Producto producto1 = new Producto();
        producto1.setNombre("Producto 1");
        producto1.setPrecio(new BigDecimal("100.00"));

        Producto producto2 = new Producto();
        producto2.setNombre("Producto 2");
        producto2.setPrecio(new BigDecimal("200.00"));

        CarritoProducto item1 = new CarritoProducto();
        item1.setProducto(producto1);
        item1.setCantidad(2);

        CarritoProducto item2 = new CarritoProducto();
        item2.setProducto(producto2);
        item2.setCantidad(1);

        carrito.setProductos(List.of(item1, item2));

        when(cartRepository.findById(1L)).thenReturn(Optional.of(carrito));

        // Mocking DiscountUtils
        mockStatic(DiscountUtils.class);
        when(DiscountUtils.calculateSeasonalDiscount("Producto 1")).thenReturn(0.10);
        when(DiscountUtils.calculateSeasonalDiscount("Producto 2")).thenReturn(0.20);

        // Act
        Carrito carritoConDescuento = cartService.getCartWithDiscount(1L);

        // Assert
        BigDecimal expectedTotal = new BigDecimal("280.00"); // (100 * 0.9 * 2) + (200 * 0.8 * 1)
        assertEquals(expectedTotal, carritoConDescuento.getTotal());

        verify(cartRepository, times(1)).findById(1L);
    }

    @Test
    void getCartWithDiscount_ShouldThrowException_WhenCartNotFound() {
        // Arrange
        when(cartRepository.findById(1L)).thenReturn(Optional.empty());

        // Act & Assert
        Exception exception = assertThrows(RuntimeException.class, () -> cartService.getCartWithDiscount(1L));
        assertEquals("Carrito no encontrado", exception.getMessage());

        verify(cartRepository, times(1)).findById(1L);
    }
}
