package com.lab.coffeeshop.repository;

import com.lab.coffeeshop.model.Shop;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShopRepository extends JpaRepository<Shop, Long> {
}
