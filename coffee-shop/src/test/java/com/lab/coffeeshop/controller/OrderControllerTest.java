package com.lab.coffeeshop.controller;

import com.lab.coffeeshop.model.Coffee;
import com.lab.coffeeshop.repository.AgentRepository;
import com.lab.coffeeshop.repository.CoffeeRepository;
import com.lab.coffeeshop.repository.OrderRepository;
import com.lab.coffeeshop.repository.UserRepository;
import com.lab.coffeeshop.service.OrderService;
import com.lab.coffeeshop.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import javax.servlet.http.Cookie;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(OrderController.class)
@Import({OrderService.class, UserService.class})
class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderRepository orderRepository;

    @MockBean
    private CoffeeRepository coffeeRepository;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private AgentRepository agentRepository;

    @Test
    void testGet() throws Exception {
        when(orderRepository.existsById(42L)).thenReturn(true);

        mockMvc.perform(
                get("/order")
                        .cookie(new Cookie("username", "admin"))
                        .param("delete", "42"))
                .andExpect(status().isOk())
                .andExpect(forwardedUrl("/WEB-INF/order.jsp"));
    }

    @Test
    void testPostOk() throws Exception {
        Coffee coffee = new Coffee();
        coffee.setCost(15L);
        when(coffeeRepository.findById(2L)).thenReturn(Optional.of(coffee));

        mockMvc.perform(
                post("/order")
                        .cookie(new Cookie("username", "admin"))
                        .param("cost", "20")
                        .param("tour_id", "2")
                        .param("user_id", "1")
                        .param("agent_id", "3"))
                .andExpect(status().isOk())
                .andExpect(forwardedUrl("/WEB-INF/order.jsp"));
    }

    @Test
    void testPostInvalid() {
        Coffee coffee = new Coffee();
        coffee.setCost(25L);
        when(coffeeRepository.findById(2L)).thenReturn(Optional.of(coffee));

        assertThrows(Exception.class,
                () -> mockMvc.perform(
                        post("/order")
                                .cookie(new Cookie("username", "admin"))
                                .param("cost", "20")
                                .param("tour_id", "2")
                                .param("user_id", "1")
                                .param("agent_id", "3"))
                        .andExpect(status().isInternalServerError())
                        .andExpect(forwardedUrl("/WEB-INF/error.jsp")));
    }

    @Test
    void testPostNotFound() {
        assertThrows(Exception.class,
                () -> mockMvc.perform(
                        post("/order")
                                .cookie(new Cookie("username", "admin"))
                                .param("cost", "20")
                                .param("tour_id", "2")
                                .param("user_id", "1")
                                .param("agent_id", "3"))
                        .andExpect(status().isInternalServerError())
                        .andExpect(forwardedUrl("/WEB-INF/error.jsp")));
    }
}