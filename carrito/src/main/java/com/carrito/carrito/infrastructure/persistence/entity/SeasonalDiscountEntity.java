package com.carrito.carrito.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "seasonal_discounts")
public class SeasonalDiscountEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seasonal_discount_id")
    private Long seasonalDiscountId;

    @Column(name = "product_id", nullable = false)
    private Long productId;

    @Column(name = "start_month", nullable = false)
    private Integer startMonth;

    @Column(name = "end_month", nullable = false)
    private Integer endMonth;

    @Column(name = "discount_percentage", nullable = false)
    private Double discountPercentage;
}