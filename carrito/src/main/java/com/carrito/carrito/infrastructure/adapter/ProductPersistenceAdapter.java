package com.carrito.carrito.infrastructure.adapter;


import com.carrito.carrito.domain.model.Product;
import com.carrito.carrito.domain.repository.ProductRepository;
import com.carrito.carrito.infrastructure.persistence.SpringDataProductRepository;
import com.carrito.carrito.infrastructure.persistence.mapper.ProductMapper; // si tienes uno
import org.springframework.stereotype.Component;

@Component
public class ProductPersistenceAdapter implements ProductRepository {

    private final SpringDataProductRepository springDataProductRepository;
    private final ProductMapper productMapper;

    public ProductPersistenceAdapter(SpringDataProductRepository springDataProductRepository,
                                     ProductMapper productMapper) {
        this.springDataProductRepository = springDataProductRepository;
        this.productMapper = productMapper;
    }

    @Override
    public Product findById(Long productId) {
        return springDataProductRepository
                .findById(productId)
                .map(productMapper::toDomain)  // Convierte ProductEntity -> Product
                .orElse(null);
    }

    // Otros métodos que cumplan la interfaz ProductRepository
}