package com.lab.coffeeshop.service;

import com.lab.coffeeshop.model.Shop;
import com.lab.coffeeshop.repository.ShopRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ShopService {

    private final ShopRepository shopRepository;

    public void create(String name) {
        Shop shop = new Shop();
        shop.setAgencyName(name);
        shopRepository.save(shop);
    }

    public List<Shop> findAll() {
        return shopRepository.findAll();
    }

    public void deleteById(Long id) {
        if (shopRepository.existsById(id)) {
            shopRepository.deleteById(id);
        }
    }
}
