package com.carrito.carrito.infrastructure.adapter;

import com.carrito.carrito.domain.model.SeasonalDiscount;
import com.carrito.carrito.domain.repository.DiscountRepository;
import com.carrito.carrito.infrastructure.persistence.SpringDataDiscountRepository;
import com.carrito.carrito.infrastructure.persistence.mapper.SeasonalDiscountMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class DiscountPersistenceAdapter implements DiscountRepository {

    private final SpringDataDiscountRepository springDataDiscountRepository;
    private final SeasonalDiscountMapper discountMapper;

    public DiscountPersistenceAdapter(SpringDataDiscountRepository springDataDiscountRepository,
                                      SeasonalDiscountMapper discountMapper) {
        this.springDataDiscountRepository = springDataDiscountRepository;
        this.discountMapper = discountMapper;
    }

    @Override
    public List<SeasonalDiscount> findAllByProductId(Long productId) {
        return springDataDiscountRepository
                .findAllByProductId(productId)
                .stream()
                .map(discountMapper::toDomain)
                .collect(Collectors.toList());
    }
}