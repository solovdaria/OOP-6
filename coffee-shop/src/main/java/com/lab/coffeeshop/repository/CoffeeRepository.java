package com.lab.coffeeshop.repository;

import com.lab.coffeeshop.model.Coffee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoffeeRepository extends JpaRepository<Coffee, Long> {
}
