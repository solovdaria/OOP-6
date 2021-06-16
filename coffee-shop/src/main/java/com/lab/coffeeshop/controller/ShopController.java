package com.lab.coffeeshop.controller;

import com.lab.coffeeshop.service.ShopService;
import com.lab.coffeeshop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/agency")
@RequiredArgsConstructor
public class ShopController {

    private final ShopService shopService;
    private final UserService userService;

    @GetMapping
    public String get(Model model, @CookieValue String username, @RequestParam Optional<Long> delete) {
        delete.ifPresent(shopService::deleteById);
        model.addAttribute("agencies", shopService.findAll());
        model.addAttribute("admin", userService.isAdmin(username));
        return "agency";
    }

    @PostMapping
    public String post(Model model, @CookieValue String username, @RequestParam("agency_name") String name) {
        shopService.create(name);
        model.addAttribute("agencies", shopService.findAll());
        model.addAttribute("admin", userService.isAdmin(username));
        return "agency";
    }
}
