package com.lab.coffeeshop.service;

import com.lab.coffeeshop.model.Coffee;
import com.lab.coffeeshop.repository.CoffeeRepository;
import com.lab.coffeeshop.repository.ShopRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CoffeeService {

    private final CoffeeRepository coffeeRepository;
    private final ShopRepository shopRepository;

    public void create(String name, String description, Long cost, Long agencyId) {
        Coffee coffee = new Coffee();
        coffee.setName(name);
        coffee.setDescription(description);
        coffee.setCost(cost);
        coffee.setShop(shopRepository.getOne(agencyId));
        coffeeRepository.save(coffee);
    }

    public List<Coffee> findAll() {
        return coffeeRepository.findAll();
    }

    public void deleteById(Long id) {
        if (coffeeRepository.existsById(id)) {
            coffeeRepository.deleteById(id);
        }
    }
}
