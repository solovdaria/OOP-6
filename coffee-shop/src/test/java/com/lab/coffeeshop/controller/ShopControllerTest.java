package com.lab.coffeeshop.controller;

import com.lab.coffeeshop.model.Shop;
import com.lab.coffeeshop.repository.ShopRepository;
import com.lab.coffeeshop.repository.UserRepository;
import com.lab.coffeeshop.service.ShopService;
import com.lab.coffeeshop.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import javax.servlet.http.Cookie;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ShopController.class)
@Import({ShopService.class, UserService.class})
class ShopControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ShopRepository shopRepository;

    @MockBean
    private UserRepository userRepository;

    @Autowired
    private ShopService shopService;

    @Test
    void testGet() throws Exception {
        when(shopRepository.findAll()).thenReturn(List.of(new Shop()));
        when(shopRepository.existsById(42L)).thenReturn(true);

        assertFalse(shopService.findAll().isEmpty());

        mockMvc.perform(
                get("/agency")
                        .cookie(new Cookie("username", "admin"))
                        .param("delete", "42"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(forwardedUrl("/WEB-INF/agency.jsp"));
    }

    @Test
    void testPost() throws Exception {
        when(shopRepository.findAll()).thenReturn(List.of(new Shop()));
        when(shopRepository.existsById(42L)).thenReturn(true);

        assertFalse(shopService.findAll().isEmpty());

        mockMvc.perform(
                post("/agency")
                        .cookie(new Cookie("username", "admin"))
                        .param("agency_name", "123456"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(forwardedUrl("/WEB-INF/agency.jsp"));
    }
}