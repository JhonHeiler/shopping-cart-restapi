package com.carrito.carrito.domain.dto;

import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class CarritoDTO {
    private Long id;
    private List<CarritoProductoDTO> productos;
    private BigDecimal total;
    private int totalProductos;
}
