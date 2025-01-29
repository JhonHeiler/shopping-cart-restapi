package com.carrito.carrito.infrastructure.mapper;

import com.carrito.carrito.domain.dto.ProductoDTO;
import com.carrito.carrito.domain.model.Producto;
import org.mapstruct.Mapper;
@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductoDTO toDto(Producto producto);
    Producto toEntity(ProductoDTO productoDTO);
}