package com.carrito.carrito.infrastructure.mapper;


import com.carrito.carrito.domain.dto.CarritoProductoDTO;
import com.carrito.carrito.domain.model.CarritoProducto;

public class CartProductMapper {

    // Convierte de entidad a DTO
    public static CarritoProductoDTO toDto(CarritoProducto carritoProducto) {
        if (carritoProducto == null) {
            return null;
        }

        CarritoProductoDTO dto = new CarritoProductoDTO();
        dto.setIdProducto(carritoProducto.getProducto().getId());
        dto.setNombreProducto(carritoProducto.getProducto().getNombre());
        dto.setCantidad(carritoProducto.getCantidad());
        dto.setSubtotal(carritoProducto.getSubtotal());

        return dto;
    }

    // Convierte de DTO a entidad
    public static CarritoProducto toEntity(CarritoProductoDTO carritoProductoDTO) {
        if (carritoProductoDTO == null) {
            return null;
        }

        CarritoProducto carritoProducto = new CarritoProducto();
        carritoProducto.setCantidad(carritoProductoDTO.getCantidad());
        return carritoProducto;
    }
}