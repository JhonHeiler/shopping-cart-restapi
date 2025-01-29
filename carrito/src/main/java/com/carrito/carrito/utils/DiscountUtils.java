package com.carrito.carrito.utils;

import java.time.LocalDate;

public class DiscountUtils {
    public static double calculateSeasonalDiscount(String productName) {
        LocalDate today = LocalDate.now();
        int month = today.getMonthValue();

        if (month <= 6) {
            return 0.05; // 5% de descuento en los primeros 6 meses
        } else {
            return 0.15; // 15% de descuento en los últimos 6 meses
        }
    }

    public static double calculateCouponDiscount(String couponCode) {
        // Descuentos basados en códigos de cupones
        if ("DESC10".equalsIgnoreCase(couponCode)) {
            return 0.10; // 10% de descuento
        } else if ("DESC20".equalsIgnoreCase(couponCode)) {
            return 0.20; // 20% de descuento
        }
        return 0.05; // Descuento base del 5% si el cupón no coincide con los predefinidos
    }
}