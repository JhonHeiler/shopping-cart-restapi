package com.carrito.carrito.infrastructure.mapper;

import com.carrito.carrito.domain.dto.CarritoDTO;
import com.carrito.carrito.domain.dto.CarritoProductoDTO;
import com.carrito.carrito.domain.model.Carrito;
import java.util.List;
import java.util.stream.Collectors;

public class CartMapper {

    // Convierte de entidad a DTO
    public static CarritoDTO toDto(Carrito carrito) {
        if (carrito == null) {
            return null;
        }

        CarritoDTO dto = new CarritoDTO();
        dto.setId(carrito.getId());
        dto.setTotal(carrito.getTotal());
        dto.setTotalProductos(mapTotalProductos(carrito));

        // Mapear productos si existen
        if (carrito.getProductos() != null) {
            List<CarritoProductoDTO> productosDTO = carrito.getProductos().stream()
                    .map(CartProductMapper::toDto)
                    .collect(Collectors.toList());
            dto.setProductos(productosDTO);
        }

        return dto;
    }

    // Convierte de DTO a entidad
    public static Carrito toEntity(CarritoDTO carritoDTO) {
        if (carritoDTO == null) {
            return null;
        }

        Carrito carrito = new Carrito();
        carrito.setId(carritoDTO.getId());
        carrito.setTotal(carritoDTO.getTotal());

        return carrito;
    }

    // Método auxiliar para contar productos
    private static int mapTotalProductos(Carrito carrito) {
        return (carrito.getProductos() != null) ? carrito.getProductos().size() : 0;
    }
}
