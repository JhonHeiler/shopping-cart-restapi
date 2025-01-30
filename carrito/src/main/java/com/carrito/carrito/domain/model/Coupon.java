package com.carrito.carrito.domain.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class Coupon {
    private Long couponId;
    private String code;
    private String description;
    private double discountPercentage;
    private LocalDate validFrom;
    private LocalDate validTo;
    private int usageLimit;
    private int usedCount;
    private LocalDateTime createdAt;

}