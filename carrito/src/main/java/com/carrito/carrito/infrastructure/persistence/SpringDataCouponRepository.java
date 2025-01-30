package com.carrito.carrito.infrastructure.persistence;

import com.carrito.carrito.infrastructure.persistence.entity.CouponEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringDataCouponRepository extends JpaRepository<CouponEntity, Long> {

    CouponEntity findByCode(String code);
    boolean existsByCode(String code);
}