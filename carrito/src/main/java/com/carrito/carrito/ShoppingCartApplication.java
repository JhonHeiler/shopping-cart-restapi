package com.carrito.carrito;

import com.carrito.carrito.infrastructure.persistence.SpringDataCouponRepository;
import com.carrito.carrito.infrastructure.persistence.SpringDataDiscountRepository;
import com.carrito.carrito.infrastructure.persistence.SpringDataProductRepository;
import com.carrito.carrito.infrastructure.persistence.SpringDataUserRepository; // si lo tienes
import com.carrito.carrito.infrastructure.persistence.entity.CouponEntity;
import com.carrito.carrito.infrastructure.persistence.entity.ProductEntity;
import com.carrito.carrito.infrastructure.persistence.entity.SeasonalDiscountEntity;
import com.carrito.carrito.infrastructure.persistence.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.time.LocalDate;
import java.time.LocalDateTime;

@SpringBootApplication
public class ShoppingCartApplication implements CommandLineRunner {

	@Autowired
	private SpringDataUserRepository userRepository;
	@Autowired
	private SpringDataProductRepository productRepository;
	@Autowired
	private SpringDataCouponRepository couponRepository;
	@Autowired
	private SpringDataDiscountRepository discountRepository;

	public static void main(String[] args) {
		SpringApplication.run(ShoppingCartApplication.class, args);
	}

	@Override
	public void run(String... args) {
		// ===== 1. Insertar usuarios (sin duplicar) =====
		if (!userRepository.existsByEmail("jhon@example.com")) {
			UserEntity user1 = new UserEntity();
			user1.setUsername("jhon_doe");
			user1.setPassword("1234");
			user1.setEmail("jhon@example.com");
			user1.setCreatedAt(LocalDateTime.now());
			userRepository.save(user1);
			System.out.println("Usuario 'jhon@example.com' creado.");
		} else {
			System.out.println("Usuario 'jhon@example.com' ya existe. Omitiendo creación.");
		}

		if (!userRepository.existsByEmail("jane@example.com")) {
			UserEntity user2 = new UserEntity();
			user2.setUsername("jane_doe");
			user2.setPassword("pass");
			user2.setEmail("jane@example.com");
			user2.setCreatedAt(LocalDateTime.now());
			userRepository.save(user2);
			System.out.println("Usuario 'jane@example.com' creado.");
		} else {
			System.out.println("Usuario 'jane@example.com' ya existe. Omitiendo creación.");
		}

		// ===== 2. Insertar productos =====
		ProductEntity p1 = new ProductEntity();
		p1.setName("Laptop");
		p1.setDescription("Laptop gaming 16GB RAM");
		p1.setPrice(1200.0);
		p1.setCreatedAt(LocalDateTime.now());
		p1.setUpdatedAt(LocalDateTime.now());
		productRepository.save(p1);

		ProductEntity p2 = new ProductEntity();
		p2.setName("Smartphone");
		p2.setDescription("Celular Android 8GB RAM");
		p2.setPrice(800.0);
		p2.setCreatedAt(LocalDateTime.now());
		p2.setUpdatedAt(LocalDateTime.now());
		productRepository.save(p2);

		// ===== 3. Insertar descuentos de temporada =====
		SeasonalDiscountEntity sd1 = new SeasonalDiscountEntity();
		sd1.setProductId(p1.getProductId());
		sd1.setStartMonth(1);
		sd1.setEndMonth(6);
		sd1.setDiscountPercentage(5.0);
		discountRepository.save(sd1);

		SeasonalDiscountEntity sd2 = new SeasonalDiscountEntity();
		sd2.setProductId(p1.getProductId());
		sd2.setStartMonth(7);
		sd2.setEndMonth(12);
		sd2.setDiscountPercentage(15.0);
		discountRepository.save(sd2);

		// ===== 4. Insertar cupones (sin duplicar) =====
		if (!couponRepository.existsByCode("WELCOME2025")) {
			CouponEntity c1 = new CouponEntity();
			c1.setCode("WELCOME2025");
			c1.setDescription("Cupón de bienvenida");
			c1.setDiscountPercentage(10.0);
			c1.setValidFrom(LocalDate.of(2025, 1, 1));
			c1.setValidTo(LocalDate.of(2025, 12, 31));
			c1.setUsageLimit(5);
			c1.setUsedCount(0);
			c1.setCreatedAt(LocalDateTime.now());
			couponRepository.save(c1);
			System.out.println("Cupón 'WELCOME2025' creado.");
		} else {
			System.out.println("Cupón 'WELCOME2025' ya existe. Omitiendo creación.");
		}

		if (!couponRepository.existsByCode("FLASHSALE")) {
			CouponEntity c2 = new CouponEntity();
			c2.setCode("FLASHSALE");
			c2.setDescription("Cupón de venta relámpago");
			c2.setDiscountPercentage(20.0);
			c2.setValidFrom(LocalDate.of(2025, 1, 1));
			c2.setValidTo(LocalDate.of(2025, 2, 1));
			c2.setUsageLimit(1);
			c2.setUsedCount(0);
			c2.setCreatedAt(LocalDateTime.now());
			couponRepository.save(c2);
			System.out.println("Cupón 'FLASHSALE' creado.");
		} else {
			System.out.println("Cupón 'FLASHSALE' ya existe. Omitiendo creación.");
		}

		System.out.println("***** DATOS DE PRUEBA INSERTADOS *****");
	}
}