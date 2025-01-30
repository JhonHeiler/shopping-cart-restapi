package com.carrito.carrito.domain.service;

import com.carrito.carrito.domain.model.SeasonalDiscount;
import com.carrito.carrito.domain.repository.DiscountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DiscountCalculatorServiceTest {

    @Mock
    private DiscountRepository discountRepository;

    @InjectMocks
    private DiscountCalculatorService discountCalculatorService;

    private final Long productId = 101L;
    private final double productPrice = 1000.0;

    @BeforeEach
    void setUp() {
        // No es necesario en este caso, ya que usamos Mockito con @InjectMocks y @Mock
    }

    @Test
    void testCalculateSeasonalDiscount_WhenNoDiscountsExist() {
        // Simular que no hay descuentos para el producto
        when(discountRepository.findAllByProductId(productId)).thenReturn(Collections.emptyList());

        double discount = discountCalculatorService.calculateSeasonalDiscount(productId, productPrice);

        assertEquals(0.0, discount, "Si no hay descuentos, el descuento debe ser 0.");
        verify(discountRepository, times(1)).findAllByProductId(productId);
    }

    @Test
    void testCalculateSeasonalDiscount_WhenDiscountExistsAndApplies() {
        int currentMonth = LocalDate.now().getMonthValue();

        List<SeasonalDiscount> discounts = Arrays.asList(
                new SeasonalDiscount(1L, productId, currentMonth - 1, currentMonth + 1, 10.0) // Descuento del 10%
        );

        when(discountRepository.findAllByProductId(productId)).thenReturn(discounts);

        double discount = discountCalculatorService.calculateSeasonalDiscount(productId, productPrice);

        assertEquals(100.0, discount, "El descuento debe ser el 10% de 1000 = 100.");
        verify(discountRepository, times(1)).findAllByProductId(productId);
    }

    @Test
    void testCalculateSeasonalDiscount_WhenDiscountExistsButDoesNotApply() {
        int currentMonth = LocalDate.now().getMonthValue();

        List<SeasonalDiscount> discounts = Arrays.asList(
                new SeasonalDiscount(1L, productId, currentMonth + 1, currentMonth + 2, 15.0) // Fuera del rango
        );

        when(discountRepository.findAllByProductId(productId)).thenReturn(discounts);

        double discount = discountCalculatorService.calculateSeasonalDiscount(productId, productPrice);

        assertEquals(0.0, discount, "Si el descuento no aplica para el mes actual, debe ser 0.");
        verify(discountRepository, times(1)).findAllByProductId(productId);
    }

    @Test
    void testCalculateSeasonalDiscount_WhenMultipleDiscountsExistAndOneApplies() {
        int currentMonth = LocalDate.now().getMonthValue();

        List<SeasonalDiscount> discounts = Arrays.asList(
                new SeasonalDiscount(1L, productId, currentMonth - 2, currentMonth - 1, 5.0),  // Expirado
                new SeasonalDiscount(2L, productId, currentMonth, currentMonth + 1, 20.0)  // Aplica
        );

        when(discountRepository.findAllByProductId(productId)).thenReturn(discounts);

        double discount = discountCalculatorService.calculateSeasonalDiscount(productId, productPrice);

        assertEquals(200.0, discount, "Debe aplicar el descuento del 20%, es decir, 200.");
        verify(discountRepository, times(1)).findAllByProductId(productId);
    }
}
