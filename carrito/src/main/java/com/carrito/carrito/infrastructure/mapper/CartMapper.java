package com.carrito.carrito.infrastructure.mapper;

import com.carrito.carrito.domain.dto.CarritoDTO;
import com.carrito.carrito.domain.model.Carrito;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface CartMapper {

    @Mapping(target = "totalProductos", source = "carrito", qualifiedByName = "mapTotalProductos")
    CarritoDTO toDto(Carrito carrito);

    Carrito toEntity(CarritoDTO carritoDTO);

    @Named("mapTotalProductos")
    static int mapTotalProductos(Carrito carrito) {
        return (carrito.getProductos() != null) ? carrito.getProductos().size() : 0;
    }
}
