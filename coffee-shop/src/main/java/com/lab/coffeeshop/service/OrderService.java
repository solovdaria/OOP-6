package com.lab.coffeeshop.service;

import com.lab.coffeeshop.exception.ValidationException;
import com.lab.coffeeshop.model.Coffee;
import com.lab.coffeeshop.model.Order;
import com.lab.coffeeshop.repository.AgentRepository;
import com.lab.coffeeshop.repository.CoffeeRepository;
import com.lab.coffeeshop.repository.OrderRepository;
import com.lab.coffeeshop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final CoffeeRepository coffeeRepository;
    private final UserRepository userRepository;
    private final AgentRepository agentRepository;

    public void create(Long cost, Long tourId, Long userId, Long agentId) {
        validateCost(cost, tourId);
        Order order = new Order();
        order.setCost(cost);
        order.setCoffee(coffeeRepository.getOne(tourId));
        order.setUser(userRepository.getOne(userId));
        if (agentId != null) {
            order.setAgent(agentRepository.getOne(agentId));
        }
        orderRepository.save(order);
    }

    private void validateCost(Long cost, Long tourId) {
        Coffee coffee = coffeeRepository.findById(tourId)
                .orElseThrow(() -> new ValidationException("Coffee not found"));
        if (cost < coffee.getCost()) {
            throw new ValidationException("Coffee cost is lower");
        }
    }

    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    public List<Order> findByUser(String username) {
        return orderRepository.findByUserUsername(username);
    }

    public void deleteById(Long id) {
        if (orderRepository.existsById(id)) {
            orderRepository.deleteById(id);
        }
    }
}
