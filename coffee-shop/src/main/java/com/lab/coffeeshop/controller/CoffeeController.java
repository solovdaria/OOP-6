package com.lab.coffeeshop.controller;

import com.lab.coffeeshop.service.CoffeeService;
import com.lab.coffeeshop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/tour")
@RequiredArgsConstructor
public class CoffeeController {

    private final CoffeeService coffeeService;
    private final UserService userService;

    @GetMapping
    public String get(Model model, @CookieValue String username, @RequestParam Optional<Long> delete) {
        delete.ifPresent(coffeeService::deleteById);
        model.addAttribute("tours", coffeeService.findAll());
        model.addAttribute("admin", userService.isAdmin(username));
        return "tour";
    }

    @PostMapping
    public String post(Model model, @CookieValue String username, @RequestParam("tour_name") String name,
                       @RequestParam("tour_description") String description, @RequestParam("tour_agency") Long agencyId,
                       @RequestParam("tour_cost") Long cost) {
        coffeeService.create(name, description, cost, agencyId);
        model.addAttribute("tours", coffeeService.findAll());
        model.addAttribute("admin", userService.isAdmin(username));
        return "tour";
    }
}
