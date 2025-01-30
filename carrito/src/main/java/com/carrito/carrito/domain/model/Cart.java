package com.carrito.carrito.domain.model;


import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Cart {
    private Long cartId;
    private Long userId;
    private String status; // 'ACTIVE', 'COMPLETED', etc.
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private List<CartItem> items = new ArrayList<>();

    public Cart() { }

    public Cart(Long cartId, Long userId, String status) {
        this.cartId = cartId;
        this.userId = userId;
        this.status = status;
    }

    // GETTERS y SETTERS

    public void addItem(CartItem item) {
        // Lógica para agregar o actualizar un ítem en la lista
        // Verificar si productId ya existe
        boolean found = false;
        for (CartItem ci : items) {
            if (ci.getProductId().equals(item.getProductId())) {
                ci.setQuantity(ci.getQuantity() + item.getQuantity());
                found = true;
                break;
            }
        }
        if (!found) {
            items.add(item);
        }
    }

    public void removeItem(Long productId) {
        items.removeIf(i -> i.getProductId().equals(productId));
    }

    public void updateItemQuantity(Long productId, int newQuantity) {
        for (CartItem item : items) {
            if (item.getProductId().equals(productId)) {
                if (newQuantity <= 0) {
                    removeItem(productId);
                } else {
                    item.setQuantity(newQuantity);
                }
                break;
            }
        }
    }

    public double getTotal() {
        return items.stream()
                .mapToDouble(CartItem::getSubtotal)
                .sum();
    }
}