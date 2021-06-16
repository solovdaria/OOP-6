package com.lab.coffeeshop.controller;

import com.lab.coffeeshop.service.AgentService;
import com.lab.coffeeshop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/agent")
@RequiredArgsConstructor
public class AgentController {

    private final AgentService agentService;
    private final UserService userService;

    @GetMapping
    public String get(Model model, @CookieValue String username, @RequestParam Optional<Long> delete) {
        delete.ifPresent(agentService::deleteById);
        model.addAttribute("agents", agentService.findAll());
        model.addAttribute("admin", userService.isAdmin(username));
        return "agent";
    }

    @PostMapping
    public String post(Model model, @CookieValue String username, @RequestParam("agent_name") String name,
                       @RequestParam("agency_id") Long agencyId) {
        agentService.create(name, agencyId);
        model.addAttribute("agents", agentService.findAll());
        model.addAttribute("admin", userService.isAdmin(username));
        return "agent";
    }
}
