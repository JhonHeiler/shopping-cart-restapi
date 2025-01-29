package com.carrito.carrito.infrastructure.controller;

import com.carrito.carrito.application.usecase.*;
import com.carrito.carrito.domain.dto.CarritoDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {
    private final AddProductUseCase addProductUseCase;
    private final UpdateQuantityUseCase updateQuantityUseCase;
    private final RemoveProductUseCase removeProductUseCase;
    private final ViewCartUseCase viewCartUseCase;
    private final ApplyCouponUseCase applyCouponUseCase;

    @PostMapping("/{carritoId}/add-product")
    public ResponseEntity<String> addProduct(
            @PathVariable Long carritoId,
            @RequestParam Long productoId,
            @RequestParam int cantidad) {
        try {
            addProductUseCase.addProduct(carritoId, productoId, cantidad);
            return ResponseEntity.ok("Producto agregado al carrito exitosamente.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al agregar producto: " + e.getMessage());
        }
    }

    @PutMapping("/update-quantity")
    public ResponseEntity<String> updateQuantity(
            @RequestParam Long carritoProductoId,
            @RequestParam int cantidad) {
        try {
            updateQuantityUseCase.updateQuantity(carritoProductoId, cantidad);
            return ResponseEntity.ok("Cantidad actualizada correctamente.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al actualizar cantidad: " + e.getMessage());
        }
    }

    @DeleteMapping("/remove-product")
    public ResponseEntity<String> removeProduct(@RequestParam Long carritoProductoId) {
        try {
            removeProductUseCase.removeProduct(carritoProductoId);
            return ResponseEntity.ok("Producto eliminado del carrito.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al eliminar producto: " + e.getMessage());
        }
    }

    @GetMapping("/{carritoId}")
    public ResponseEntity<CarritoDTO> viewCart(@PathVariable Long carritoId) {
        try {
            CarritoDTO carritoDTO = viewCartUseCase.viewCart(carritoId);
            return ResponseEntity.ok(carritoDTO);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
}
