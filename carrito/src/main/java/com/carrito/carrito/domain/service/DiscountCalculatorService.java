package com.carrito.carrito.domain.service;

import com.carrito.carrito.domain.model.SeasonalDiscount;
import com.carrito.carrito.domain.repository.DiscountRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class DiscountCalculatorService {

    private final DiscountRepository discountRepository;

    public DiscountCalculatorService(DiscountRepository discountRepository) {
        this.discountRepository = discountRepository;
    }

    /**
     * Calcula el descuento para un producto dadas las reglas de temporada.
     */
    public double calculateSeasonalDiscount(Long productId, double price) {
        // Obtener la lista de descuentos configurados para el producto
        List<SeasonalDiscount> discounts = discountRepository.findAllByProductId(productId);
        if (discounts == null || discounts.isEmpty()) {
            return 0;
        }
        // Obtener mes actual
        int currentMonth = LocalDate.now().getMonthValue();

        // Verificar si hay un descuento aplicable
        for (SeasonalDiscount sd : discounts) {
            if (currentMonth >= sd.getStartMonth() && currentMonth <= sd.getEndMonth()) {
                // Calcular el descuento
                return price * (sd.getDiscountPercentage() / 100.0);
            }
        }
        return 0;
    }
}
