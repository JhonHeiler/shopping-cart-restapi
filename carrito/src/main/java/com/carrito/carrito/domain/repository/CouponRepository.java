package com.carrito.carrito.domain.repository;
import com.carrito.carrito.domain.model.Coupon;

public interface CouponRepository {
    Coupon findByCode(String code);
    Coupon update(Coupon coupon);
}