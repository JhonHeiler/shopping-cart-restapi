package com.carrito.carrito.infrastructure.persistence.mapper;
import com.carrito.carrito.domain.model.Cart;
import com.carrito.carrito.domain.model.CartItem;
import com.carrito.carrito.infrastructure.persistence.entity.CartEntity;
import com.carrito.carrito.infrastructure.persistence.entity.CartItemEntity;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class CartMapper {

    /**
     * Convierte desde Cart (dominio) a CartEntity (JPA).
     */
    public CartEntity toEntity(Cart cart) {
        if (cart == null) {
            return null;
        }
        CartEntity entity = new CartEntity();
        entity.setCartId(cart.getCartId());
        entity.setUserId(cart.getUserId());
        entity.setStatus(cart.getStatus());
        entity.setCreatedAt(cart.getCreatedAt());
        entity.setUpdatedAt(cart.getUpdatedAt());

        // Items
        entity.setItems(cart.getItems().stream().map(item -> {
            CartItemEntity itemEntity = new CartItemEntity();
            itemEntity.setCartItemId(item.getCartItemId());
            itemEntity.setCart(entity); // relación bidireccional
            itemEntity.setProductId(item.getProductId());
            itemEntity.setQuantity(item.getQuantity());
            itemEntity.setPrice(item.getPrice());
            itemEntity.setDiscountApplied(item.getDiscountApplied());
            itemEntity.setCreatedAt(item.getCreatedAt());
            itemEntity.setUpdatedAt(item.getUpdatedAt());
            return itemEntity;
        }).collect(Collectors.toList()));

        return entity;
    }

    /**
     * Convierte desde CartEntity (JPA) a Cart (dominio).
     */
    public Cart toDomain(CartEntity entity) {
        if (entity == null) {
            return null;
        }
        Cart cart = new Cart();
        cart.setCartId(entity.getCartId());
        cart.setUserId(entity.getUserId());
        cart.setStatus(entity.getStatus());
        cart.setCreatedAt(entity.getCreatedAt());
        cart.setUpdatedAt(entity.getUpdatedAt());

        // Items
        if (entity.getItems() != null) {
            for (CartItemEntity itemEntity : entity.getItems()) {
                CartItem item = new CartItem();
                item.setCartItemId(itemEntity.getCartItemId());
                item.setProductId(itemEntity.getProductId());
                item.setQuantity(itemEntity.getQuantity());
                item.setPrice(itemEntity.getPrice());
                item.setDiscountApplied(itemEntity.getDiscountApplied());
                item.setCreatedAt(itemEntity.getCreatedAt());
                item.setUpdatedAt(itemEntity.getUpdatedAt());

                cart.getItems().add(item);
            }
        }
        return cart;
    }
}