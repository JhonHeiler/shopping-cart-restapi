package com.carrito.carrito.domain.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
@Getter
@Setter
@Data
public class ProductoDTO {
    private Long id;
    private String nombre;
    private BigDecimal precio;
    private int stock;
}