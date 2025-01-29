package com.carrito.carrito.infrastructure.mapper;

import com.carrito.carrito.domain.dto.CarritoDTO;
import com.carrito.carrito.domain.model.Carrito;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CartMapper {
    @Mapping(target = "totalProductos", source = "productos.size")
    CarritoDTO toDto(Carrito carrito);

    Carrito toEntity(CarritoDTO carritoDTO);
}
