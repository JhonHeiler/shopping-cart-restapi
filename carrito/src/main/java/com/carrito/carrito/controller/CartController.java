package com.carrito.carrito.controller;

import com.carrito.carrito.domain.model.Cart;
import com.carrito.carrito.usecase.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
@Tag(name = "Carrito de Compras", description = "Operaciones del carrito de compras")
public class CartController {

    private final AddProductToCartUseCase addProductToCartUseCase;
    private final RemoveProductFromCartUseCase removeProductFromCartUseCase;
    private final UpdateCartItemQuantityUseCase updateCartItemQuantityUseCase;
    private final ViewCartUseCase viewCartUseCase;
    private final ApplyCouponUseCase applyCouponUseCase;

    public CartController(AddProductToCartUseCase addProductToCartUseCase,
                          RemoveProductFromCartUseCase removeProductFromCartUseCase,
                          UpdateCartItemQuantityUseCase updateCartItemQuantityUseCase,
                          ViewCartUseCase viewCartUseCase,
                          ApplyCouponUseCase applyCouponUseCase) {
        this.addProductToCartUseCase = addProductToCartUseCase;
        this.removeProductFromCartUseCase = removeProductFromCartUseCase;
        this.updateCartItemQuantityUseCase = updateCartItemQuantityUseCase;
        this.viewCartUseCase = viewCartUseCase;
        this.applyCouponUseCase = applyCouponUseCase;
    }

    @PostMapping("/{userId}/add")
    public ResponseEntity<Cart> addProduct(
            @PathVariable Long userId,
            @RequestParam Long productId,
            @RequestParam int quantity
    ) {
        Cart cart = addProductToCartUseCase.execute(userId, productId, quantity);
        return ResponseEntity.ok(cart);
    }

    @DeleteMapping("/{userId}/remove")
    public ResponseEntity<Cart> removeProduct(
            @PathVariable Long userId,
            @RequestParam Long productId
    ) {
        Cart cart = removeProductFromCartUseCase.execute(userId, productId);
        return ResponseEntity.ok(cart);
    }

    @PatchMapping("/{userId}/update")
    public ResponseEntity<Cart> updateQuantity(
            @PathVariable Long userId,
            @RequestParam Long productId,
            @RequestParam int newQuantity
    ) {
        Cart cart = updateCartItemQuantityUseCase.execute(userId, productId, newQuantity);
        return ResponseEntity.ok(cart);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Cart> viewCart(@PathVariable Long userId) {
        Cart cart = viewCartUseCase.execute(userId);
        return ResponseEntity.ok(cart);
    }

    @PostMapping("/{userId}/coupon")
    public ResponseEntity<Cart> applyCoupon(
            @PathVariable Long userId,
            @RequestParam String couponCode
    ) {
        Cart cart = applyCouponUseCase.execute(userId, couponCode);
        return ResponseEntity.ok(cart);
    }
}