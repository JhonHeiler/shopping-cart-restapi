package com.carrito.carrito.infrastructure.adapter;

import com.carrito.carrito.domain.model.Cart;
import com.carrito.carrito.domain.repository.CartRepository;
import com.carrito.carrito.infrastructure.persistence.mapper.CartMapper;
import com.carrito.carrito.infrastructure.persistence.SpringDataCartRepository;
import com.carrito.carrito.infrastructure.persistence.entity.CartEntity;
import org.springframework.stereotype.Component;

@Component
public class CartPersistenceAdapter implements CartRepository {

    private final SpringDataCartRepository springDataCartRepository;
    private final CartMapper cartMapper;

    public CartPersistenceAdapter(SpringDataCartRepository springDataCartRepository,
                                  CartMapper cartMapper) {
        this.springDataCartRepository = springDataCartRepository;
        this.cartMapper = cartMapper;
    }

    @Override
    public Cart findActiveCartByUser(Long userId) {
        CartEntity entity = springDataCartRepository.findByUserIdAndStatus(userId, "ACTIVE");
        return entity != null ? cartMapper.toDomain(entity) : null;
    }

    @Override
    public Cart save(Cart cart) {
        CartEntity entity = cartMapper.toEntity(cart);
        CartEntity saved = springDataCartRepository.save(entity);
        return cartMapper.toDomain(saved);
    }
}