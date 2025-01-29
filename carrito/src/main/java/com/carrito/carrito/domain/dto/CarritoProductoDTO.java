package com.carrito.carrito.domain.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CarritoProductoDTO {
    private Long idProducto;
    private String nombreProducto;
    private Integer cantidad;
    private BigDecimal subtotal;
}