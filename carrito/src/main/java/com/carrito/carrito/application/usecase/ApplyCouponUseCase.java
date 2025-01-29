package com.carrito.carrito.application.usecase;

import com.carrito.carrito.domain.exception.CarritoNotFoundException;
import com.carrito.carrito.domain.exception.CouponNotValidException;
import com.carrito.carrito.domain.model.Carrito;
import com.carrito.carrito.infrastructure.repository.CartRepository;
import com.carrito.carrito.utils.DiscountUtils;
import com.carrito.carrito.utils.ValidationUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class ApplyCouponUseCase {
    private final CartRepository cartRepository;

    public void applyCoupon(Long carritoId, String couponCode) {
        ValidationUtils.validateCouponCode(couponCode);

        Carrito carrito = cartRepository.findById(carritoId)
                .orElseThrow(() -> new CarritoNotFoundException("Carrito no encontrado"));

        if (!isValidCoupon(couponCode)) {
            throw new CouponNotValidException("Cupón no válido o ya utilizado");
        }

        BigDecimal discount = BigDecimal.valueOf(DiscountUtils.calculateCouponDiscount(couponCode));
        BigDecimal seasonalDiscount = BigDecimal.valueOf(DiscountUtils.calculateSeasonalDiscount("ProductoGenerico"));
        BigDecimal totalDiscount = discount.add(seasonalDiscount);

        carrito.setTotal(carrito.getTotal().multiply(BigDecimal.ONE.subtract(totalDiscount)));

        cartRepository.save(carrito);
    }

    private boolean isValidCoupon(String couponCode) {
        return couponCode != null && couponCode.startsWith("DESC"); // Implementar lógica real de validación
    }
}
