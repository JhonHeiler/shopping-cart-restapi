package com.carrito.carrito.domain.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class CarritoDTO {
    private Long id;
    private List<CarritoProductoDTO> productos;
    private BigDecimal total;
    private int totalProductos;
}