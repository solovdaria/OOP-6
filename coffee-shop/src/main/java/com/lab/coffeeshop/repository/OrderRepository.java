package com.lab.coffeeshop.repository;

import com.lab.coffeeshop.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByUserUsername(String username);
}
