package com.carrito.carrito.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SeasonalDiscount {
    private Long seasonalDiscountId;
    private Long productId;
    private int startMonth;
    private int endMonth;
    private double discountPercentage;
}
