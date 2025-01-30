package com.carrito.carrito.usecase;

import com.carrito.carrito.domain.model.Cart;
import com.carrito.carrito.domain.model.Coupon;
import com.carrito.carrito.domain.repository.CartRepository;
import com.carrito.carrito.domain.repository.CouponRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDate;

@Service
public class ApplyCouponUseCase {
    private final CartRepository cartRepository;
    private final CouponRepository couponRepository;

    public ApplyCouponUseCase(CartRepository cartRepository, CouponRepository couponRepository) {
        this.cartRepository = cartRepository;
        this.couponRepository = couponRepository;
    }

    public Cart execute(Long userId, String couponCode) {
        Cart cart = cartRepository.findActiveCartByUser(userId);
        if (cart == null) {
            throw new RuntimeException("No hay carrito activo para este usuario.");
        }

        Coupon coupon = couponRepository.findByCode(couponCode);
        if (coupon == null) {
            throw new RuntimeException("Cupón inválido");
        }

        // Validar vigencia
        LocalDate today = LocalDate.now();
        if (today.isBefore(coupon.getValidFrom()) || today.isAfter(coupon.getValidTo())) {
            throw new RuntimeException("Cupón no está vigente");
        }

        // Validar uso
        if (coupon.getUsedCount() >= coupon.getUsageLimit()) {
            throw new RuntimeException("Este cupón ya alcanzó su límite de uso");
        }

        // Aplicar descuento al total del carrito
        double total = cart.getTotal();
        double descuento = total * (coupon.getDiscountPercentage() / 100.0);

        // Podríamos distribuir este descuento en un campo discountApplied global o en un item específico.
        // Ejemplo simple: sumar el descuento en cada item proporcionalmente, etc.

        // Marcar cupón como usado (simple, +1)
        coupon.setUsedCount(coupon.getUsedCount() + 1);
        couponRepository.update(coupon);

        // Guardar cambios en el carrito
        return cartRepository.save(cart);
    }
}