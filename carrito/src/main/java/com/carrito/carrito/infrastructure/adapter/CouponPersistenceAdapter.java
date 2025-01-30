package com.carrito.carrito.infrastructure.adapter;

import com.carrito.carrito.domain.model.Coupon;
import com.carrito.carrito.domain.repository.CouponRepository;
import com.carrito.carrito.infrastructure.persistence.SpringDataCouponRepository;
import com.carrito.carrito.infrastructure.persistence.mapper.CouponMapper;
import org.springframework.stereotype.Component;

@Component
public class CouponPersistenceAdapter implements CouponRepository {

    private final SpringDataCouponRepository springDataCouponRepository;
    private final CouponMapper couponMapper;

    public CouponPersistenceAdapter(SpringDataCouponRepository springDataCouponRepository,
                                    CouponMapper couponMapper) {
        this.springDataCouponRepository = springDataCouponRepository;
        this.couponMapper = couponMapper;
    }

    @Override
    public Coupon findByCode(String code) {
        var entity = springDataCouponRepository.findByCode(code);
        return entity != null ? couponMapper.toDomain(entity) : null;
    }

    @Override
    public Coupon update(Coupon coupon) {
        var entity = couponMapper.toEntity(coupon);
        var saved = springDataCouponRepository.save(entity);
        return couponMapper.toDomain(saved);
    }
}