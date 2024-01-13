package com.example.campingontop.orders.repository;


import com.example.campingontop.orders.model.OrderProducts;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderProductsRepository extends JpaRepository<OrderProducts, Long> {
}
