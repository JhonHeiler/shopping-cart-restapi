package com.carrito.carrito.domain.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SeasonalDiscount {
    private Long seasonalDiscountId;
    private Long productId;
    private int startMonth;
    private int endMonth;
    private double discountPercentage;

}