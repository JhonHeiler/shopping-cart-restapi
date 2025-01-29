package com.carrito.carrito.domain.exception;

public class CarritoNotFoundException extends RuntimeException {
    public CarritoNotFoundException(String message) {
        super(message);
    }
}
