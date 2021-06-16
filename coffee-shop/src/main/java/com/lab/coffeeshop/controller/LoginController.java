package com.lab.coffeeshop.controller;

import com.lab.coffeeshop.model.User;
import com.lab.coffeeshop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/login")
@RequiredArgsConstructor
public class LoginController {

    private final UserService userService;

    @GetMapping
    public String get() {
        return "login";
    }

    @PostMapping
    public String post(HttpServletResponse response, @RequestParam("name") String username,
                       @RequestParam String password) {
        User user = userService.loginOrRegister(username, password);
        response.addCookie(new Cookie("username", user.getUsername()));
        response.addCookie(new Cookie("userid", String.valueOf(user.getId())));
        return "redirect:/";
    }
}
