package com.lab.coffeeshop.controller;

import com.lab.coffeeshop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class HomeController {

    private final UserService userService;

    @GetMapping
    public String get(Model model, @CookieValue Optional<String> username) {
        if (username.isPresent()) {
            model.addAttribute("admin", userService.isAdmin(username.get()));
            return "index";
        }
        return "redirect:login";
    }
}
