package com.carrito.carrito.infrastructure.controller;

import com.carrito.carrito.application.usecase.*;
import com.carrito.carrito.domain.dto.CarritoDTO;
import com.carrito.carrito.infrastructure.mapper.CartMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class CartControllerTest {

    private MockMvc mockMvc;

    @Mock
    private AddProductUseCase addProductUseCase;

    @Mock
    private UpdateQuantityUseCase updateQuantityUseCase;

    @Mock
    private RemoveProductUseCase removeProductUseCase;

    @Mock
    private ViewCartUseCase viewCartUseCase;

    @Mock
    private ApplyCouponUseCase applyCouponUseCase;

    @Mock
    private CartMapper cartMapper;

    @InjectMocks
    private CartController cartController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(cartController).build();
    }

    @Test
    void testAddProduct_Success() throws Exception {
        doNothing().when(addProductUseCase).addProduct(anyLong(), anyLong(), anyInt());

        mockMvc.perform(post("/cart/1/add-product")
                        .param("productoId", "2")
                        .param("cantidad", "3")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("Producto agregado al carrito exitosamente."));

        verify(addProductUseCase, times(1)).addProduct(1L, 2L, 3);
    }

    @Test
    void testAddProduct_Failure() throws Exception {
        doThrow(new RuntimeException("Error al agregar producto")).when(addProductUseCase).addProduct(anyLong(), anyLong(), anyInt());

        mockMvc.perform(post("/cart/1/add-product")
                        .param("productoId", "2")
                        .param("cantidad", "3")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Error: Error al agregar producto"));

        verify(addProductUseCase, times(1)).addProduct(1L, 2L, 3);
    }

    @Test
    void testUpdateQuantity_Success() throws Exception {
        doNothing().when(updateQuantityUseCase).updateQuantity(anyLong(), anyInt());

        mockMvc.perform(put("/cart/update-quantity")
                        .param("carritoProductoId", "1")
                        .param("cantidad", "5")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("Cantidad actualizada correctamente."));

        verify(updateQuantityUseCase, times(1)).updateQuantity(1L, 5);
    }

    @Test
    void testUpdateQuantity_Failure() throws Exception {
        doThrow(new RuntimeException("Error al actualizar cantidad")).when(updateQuantityUseCase).updateQuantity(anyLong(), anyInt());

        mockMvc.perform(put("/cart/update-quantity")
                        .param("carritoProductoId", "1")
                        .param("cantidad", "5")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Error: Error al actualizar cantidad"));

        verify(updateQuantityUseCase, times(1)).updateQuantity(1L, 5);
    }

    @Test
    void testRemoveProduct_Success() throws Exception {
        doNothing().when(removeProductUseCase).removeProduct(anyLong());

        mockMvc.perform(delete("/cart/remove-product")
                        .param("carritoProductoId", "1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("Producto eliminado del carrito."));

        verify(removeProductUseCase, times(1)).removeProduct(1L);
    }

    @Test
    void testRemoveProduct_Failure() throws Exception {
        doThrow(new RuntimeException("Error al eliminar producto")).when(removeProductUseCase).removeProduct(anyLong());

        mockMvc.perform(delete("/cart/remove-product")
                        .param("carritoProductoId", "1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Error: Error al eliminar producto"));

        verify(removeProductUseCase, times(1)).removeProduct(1L);
    }

    @Test
    void testViewCart_Success() throws Exception {
        CarritoDTO carritoDTO = new CarritoDTO();
        when(viewCartUseCase.viewCart(anyLong())).thenReturn(carritoDTO);

        mockMvc.perform(get("/cart/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(viewCartUseCase, times(1)).viewCart(1L);
    }

    @Test
    void testViewCart_Failure() throws Exception {
        doThrow(new RuntimeException("Carrito no encontrado")).when(viewCartUseCase).viewCart(anyLong());

        mockMvc.perform(get("/cart/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

        verify(viewCartUseCase, times(1)).viewCart(1L);
    }

    @Test
    void testApplyCoupon_Success() throws Exception {
        doNothing().when(applyCouponUseCase).applyCoupon(anyLong(), anyString());

        mockMvc.perform(post("/cart/1/apply-coupon")
                        .param("couponCode", "DESC10")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("Cupón aplicado exitosamente."));

        verify(applyCouponUseCase, times(1)).applyCoupon(1L, "DESC10");
    }

    @Test
    void testApplyCoupon_Failure() throws Exception {
        doThrow(new RuntimeException("Cupón inválido")).when(applyCouponUseCase).applyCoupon(anyLong(), anyString());

        mockMvc.perform(post("/cart/1/apply-coupon")
                        .param("couponCode", "DESC10")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Error: Cupón inválido"));

        verify(applyCouponUseCase, times(1)).applyCoupon(1L, "DESC10");
    }
}