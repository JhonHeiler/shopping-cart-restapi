package com.carrito.carrito.infrastructure.persistence.mapper;

import com.carrito.carrito.domain.model.Coupon;
import com.carrito.carrito.infrastructure.persistence.entity.CouponEntity;
import org.springframework.stereotype.Component;

@Component
public class CouponMapper {

    public Coupon toDomain(CouponEntity entity) {
        if (entity == null) {
            return null;
        }
        Coupon coupon = new Coupon();
        coupon.setCouponId(entity.getCouponId());
        coupon.setCode(entity.getCode());
        coupon.setDescription(entity.getDescription());
        coupon.setDiscountPercentage(entity.getDiscountPercentage() != null ? entity.getDiscountPercentage() : 0.0);
        coupon.setValidFrom(entity.getValidFrom());
        coupon.setValidTo(entity.getValidTo());
        coupon.setUsageLimit(entity.getUsageLimit());
        coupon.setUsedCount(entity.getUsedCount());
        coupon.setCreatedAt(entity.getCreatedAt());
        return coupon;
    }

    public CouponEntity toEntity(Coupon coupon) {
        if (coupon == null) {
            return null;
        }
        CouponEntity entity = new CouponEntity();
        entity.setCouponId(coupon.getCouponId());
        entity.setCode(coupon.getCode());
        entity.setDescription(coupon.getDescription());
        entity.setDiscountPercentage(coupon.getDiscountPercentage());
        entity.setValidFrom(coupon.getValidFrom());
        entity.setValidTo(coupon.getValidTo());
        entity.setUsageLimit(coupon.getUsageLimit());
        entity.setUsedCount(coupon.getUsedCount());
        entity.setCreatedAt(coupon.getCreatedAt());
        return entity;
    }
}