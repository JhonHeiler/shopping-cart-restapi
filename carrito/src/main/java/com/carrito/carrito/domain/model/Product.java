package com.carrito.carrito.domain.model;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
public class Product {
    private Long productId;
    private String name;
    private String description;
    private double price;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}