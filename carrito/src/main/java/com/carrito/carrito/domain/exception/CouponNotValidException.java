package com.carrito.carrito.domain.exception;

public class CouponNotValidException extends RuntimeException {
    public CouponNotValidException(String message) {
        super(message);
    }
}