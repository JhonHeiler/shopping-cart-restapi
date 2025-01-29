package com.carrito.carrito.infrastructure.mapper;

import com.carrito.carrito.domain.dto.ProductoDTO;
import com.carrito.carrito.domain.model.Producto;


public class ProductMapper {
    public static ProductoDTO toDto(Producto producto) {
        if (producto == null) {
            return null;
        }

        ProductoDTO dto = new ProductoDTO();
        dto.setId(producto.getId());
        dto.setNombre(producto.getNombre());
        dto.setPrecio(producto.getPrecio());
        dto.setStock(producto.getStock());
        return dto;
    }

    // Convierte de DTO a entidad
    public static Producto toEntity(ProductoDTO productoDTO) {
        if (productoDTO == null) {
            return null;
        }

        Producto producto = new Producto();
        producto.setId(productoDTO.getId());
        producto.setNombre(productoDTO.getNombre());
        producto.setPrecio(productoDTO.getPrecio());
        producto.setStock(productoDTO.getStock());
        return producto;
    }
}