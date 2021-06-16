package com.lab.coffeeshop.controller;

import com.lab.coffeeshop.repository.CoffeeRepository;
import com.lab.coffeeshop.repository.ShopRepository;
import com.lab.coffeeshop.repository.UserRepository;
import com.lab.coffeeshop.service.CoffeeService;
import com.lab.coffeeshop.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import javax.servlet.http.Cookie;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CoffeeController.class)
@Import({CoffeeService.class, UserService.class})
class CoffeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CoffeeRepository coffeeRepository;

    @MockBean
    private ShopRepository shopRepository;

    @MockBean
    private UserRepository userRepository;

    @Test
    void testGet() throws Exception {
        when(coffeeRepository.existsById(42L)).thenReturn(true);

        mockMvc.perform(
                get("/tour")
                        .cookie(new Cookie("username", "admin"))
                        .param("delete", "42"))
                .andExpect(status().isOk())
                .andExpect(forwardedUrl("/WEB-INF/tour.jsp"));
    }

    @Test
    void testPost() throws Exception {
        mockMvc.perform(
                post("/tour")
                        .cookie(new Cookie("username", "admin"))
                        .param("tour_name", "Espresso")
                        .param("tour_description", "Coffee")
                        .param("tour_agency", "23")
                        .param("tour_cost", "15"))
                .andExpect(status().isOk())
                .andExpect(forwardedUrl("/WEB-INF/tour.jsp"));
    }
}