package com.carrito.carrito.application;

import com.carrito.carrito.domain.model.Cart;
import com.carrito.carrito.domain.model.CartItem;
import com.carrito.carrito.domain.model.Product;
import com.carrito.carrito.domain.repository.CartRepository;
import com.carrito.carrito.domain.repository.ProductRepository;
import com.carrito.carrito.domain.service.DiscountCalculatorService;
import org.springframework.stereotype.Service;

@Service
public class AddProductToCartUseCase {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final DiscountCalculatorService discountService;

    public AddProductToCartUseCase(CartRepository cartRepository,
                                   ProductRepository productRepository,
                                   DiscountCalculatorService discountService) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
        this.discountService = discountService;
    }

    public Cart execute(Long userId, Long productId, int quantity) {
        // 1) Buscar Carrito Activo
        Cart cart = cartRepository.findActiveCartByUser(userId);
        if (cart == null) {
            cart = new Cart(null, userId, "ACTIVE");
        }

        // 2) Obtener info de producto
        Product product = productRepository.findById(productId);
        if (product == null) {
            throw new RuntimeException("Producto no encontrado");
        }

        // 3) Calcular descuento de temporada (si aplica)
        double discount = discountService.calculateSeasonalDiscount(productId, product.getPrice());

        // 4) Crear CartItem
        CartItem item = new CartItem(null, productId, quantity, product.getPrice(), discount);

        // 5) Agregar el item al carrito
        cart.addItem(item);

        // 6) Guardar carrito
        return cartRepository.save(cart);
    }
}