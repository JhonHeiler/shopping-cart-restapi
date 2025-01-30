package com.carrito.carrito.domain.model;


import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
public class CartItem {
    private Long cartItemId;
    private Long productId;
    private int quantity;
    private double price; // precio unitario al agregar
    private double discountApplied; // descuento aplicado
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public CartItem() {
    }

    public CartItem(Long cartItemId, Long productId, int quantity, double price, double discountApplied) {
        this.cartItemId = cartItemId;
        this.productId = productId;
        this.quantity = quantity;
        this.price = price;
        this.discountApplied = discountApplied;
    }


    public double getSubtotal() {
        double subtotalSinDescuento = price * quantity;
        return subtotalSinDescuento - discountApplied;
    }
}