package com.carrito.carrito.infrastructure.persistence.mapper;


import com.carrito.carrito.domain.model.SeasonalDiscount;
import com.carrito.carrito.infrastructure.persistence.entity.SeasonalDiscountEntity;
import org.springframework.stereotype.Component;

@Component
public class SeasonalDiscountMapper {

    public SeasonalDiscount toDomain(SeasonalDiscountEntity entity) {
        if (entity == null) {
            return null;
        }
        SeasonalDiscount discount = new SeasonalDiscount();
        discount.setSeasonalDiscountId(entity.getSeasonalDiscountId());
        discount.setProductId(entity.getProductId());
        discount.setStartMonth(entity.getStartMonth());
        discount.setEndMonth(entity.getEndMonth());
        discount.setDiscountPercentage(entity.getDiscountPercentage() != null ? entity.getDiscountPercentage() : 0.0);
        return discount;
    }

    public SeasonalDiscountEntity toEntity(SeasonalDiscount discount) {
        if (discount == null) {
            return null;
        }
        SeasonalDiscountEntity entity = new SeasonalDiscountEntity();
        entity.setSeasonalDiscountId(discount.getSeasonalDiscountId());
        entity.setProductId(discount.getProductId());
        entity.setStartMonth(discount.getStartMonth());
        entity.setEndMonth(discount.getEndMonth());
        entity.setDiscountPercentage(discount.getDiscountPercentage());
        return entity;
    }
}