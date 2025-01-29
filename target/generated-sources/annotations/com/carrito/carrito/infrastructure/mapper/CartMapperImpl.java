package com.carrito.carrito.infrastructure.mapper;

import com.carrito.carrito.domain.dto.CarritoDTO;
import com.carrito.carrito.domain.model.Carrito;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-01-28T21:23:04-0500",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.12 (Oracle Corporation)"
)
@Component
public class CartMapperImpl implements CartMapper {

    @Override
    public CarritoDTO toDto(Carrito carrito) {
        if ( carrito == null ) {
            return null;
        }

        CarritoDTO carritoDTO = new CarritoDTO();

        return carritoDTO;
    }

    @Override
    public Carrito toEntity(CarritoDTO carritoDTO) {
        if ( carritoDTO == null ) {
            return null;
        }

        Carrito carrito = new Carrito();

        return carrito;
    }
}
