package com.carrito.carrito.utils;

import java.time.LocalDate;

public class ValidationUtils {
    public static void validateQuantity(int cantidad) {
        if (cantidad < 0) {
            throw new IllegalArgumentException("La cantidad no puede ser negativa");
        }
    }

    public static void validateCouponCode(String couponCode) {
        if (couponCode == null || couponCode.isBlank()) {
            throw new IllegalArgumentException("El código de cupón no puede ser nulo o vacío");
        }
    }
}