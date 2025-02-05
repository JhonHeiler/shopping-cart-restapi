package com.carrito.carrito.controller;


import com.carrito.carrito.domain.model.Cart;
import com.carrito.carrito.application.*;
import com.carrito.carrito.infrastructure.controller.CartController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CartControllerTest {

    @Mock
    private AddProductToCartUseCase addProductToCartUseCase;

    @Mock
    private RemoveProductFromCartUseCase removeProductFromCartUseCase;

    @Mock
    private UpdateCartItemQuantityUseCase updateCartItemQuantityUseCase;

    @Mock
    private ViewCartUseCase viewCartUseCase;

    @Mock
    private ApplyCouponUseCase applyCouponUseCase;

    @InjectMocks
    private CartController cartController;

    private Cart mockCart;

    @BeforeEach
    void setUp() {
        // Crear un objeto Cart de prueba
        mockCart = new Cart();
    }

    @Test
    void testAddProduct() {
        // Configurar el mock para retornar un carrito de compras simulado
        when(addProductToCartUseCase.execute(1L, 101L, 2)).thenReturn(mockCart);

        ResponseEntity<Cart> response = cartController.addProduct(1L, 101L, 2);

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(mockCart, response.getBody());

        verify(addProductToCartUseCase, times(1)).execute(1L, 101L, 2);
    }

    @Test
    void testRemoveProduct() {
        when(removeProductFromCartUseCase.execute(1L, 101L)).thenReturn(mockCart);

        ResponseEntity<Cart> response = cartController.removeProduct(1L, 101L);

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(mockCart, response.getBody());

        verify(removeProductFromCartUseCase, times(1)).execute(1L, 101L);
    }

    @Test
    void testUpdateQuantity() {
        when(updateCartItemQuantityUseCase.execute(1L, 101L, 3)).thenReturn(mockCart);

        ResponseEntity<Cart> response = cartController.updateQuantity(1L, 101L, 3);

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(mockCart, response.getBody());

        verify(updateCartItemQuantityUseCase, times(1)).execute(1L, 101L, 3);
    }

    @Test
    void testViewCart() {
        when(viewCartUseCase.execute(1L)).thenReturn(mockCart);

        ResponseEntity<Cart> response = cartController.viewCart(1L);

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(mockCart, response.getBody());

        verify(viewCartUseCase, times(1)).execute(1L);
    }

    @Test
    void testApplyCoupon() {
        when(applyCouponUseCase.execute(1L, "DISCOUNT10")).thenReturn(mockCart);

        ResponseEntity<Cart> response = cartController.applyCoupon(1L, "DISCOUNT10");

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(mockCart, response.getBody());

        verify(applyCouponUseCase, times(1)).execute(1L, "DISCOUNT10");
    }
}
