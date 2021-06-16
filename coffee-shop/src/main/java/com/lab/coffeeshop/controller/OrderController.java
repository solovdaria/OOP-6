package com.lab.coffeeshop.controller;

import com.lab.coffeeshop.model.Coffee;
import com.lab.coffeeshop.model.Order;
import com.lab.coffeeshop.service.OrderService;
import com.lab.coffeeshop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final UserService userService;

    @GetMapping
    public String get(Model model, @CookieValue String username, @RequestParam Optional<Long> delete) {
        delete.ifPresent(orderService::deleteById);
        boolean admin = userService.isAdmin(username);
        List<Order> orders = admin ? orderService.findAll() : orderService.findByUser(username);
        model.addAttribute("orders", orders);
        model.addAttribute("admin", admin);
        if (admin) {
            long total = orders.stream()
                    .map(Order::getCoffee)
                    .mapToLong(Coffee::getCost)
                    .sum();
            model.addAttribute("total", total);
        }
        return "order";
    }

    @PostMapping
    public String post(Model model, @CookieValue String username, @RequestParam Long cost,
                       @RequestParam("tour_id") Long tourId, @RequestParam("user_id") Long userId,
                       @RequestParam(value = "agent_id", required = false) Long agentId) {
        orderService.create(cost, tourId, userId, agentId);
        boolean admin = userService.isAdmin(username);
        List<Order> orders = admin ? orderService.findAll() : orderService.findByUser(username);
        model.addAttribute("orders", orders);
        model.addAttribute("admin", admin);
        if (admin) {
            long total = orders.stream()
                    .map(Order::getCoffee)
                    .mapToLong(Coffee::getCost)
                    .sum();
            model.addAttribute("total", total);
        }
        return "order";
    }
}
