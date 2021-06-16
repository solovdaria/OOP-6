package com.lab.coffeeshop.controller;

import com.lab.coffeeshop.model.User;
import com.lab.coffeeshop.repository.UserRepository;
import com.lab.coffeeshop.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(LoginController.class)
@Import({UserService.class})
class LoginControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserRepository userRepository;

    @Test
    void testGet() throws Exception {
        mockMvc.perform(
                get("/login"))
                .andExpect(status().isOk())
                .andExpect(forwardedUrl("/WEB-INF/login.jsp"));
    }

    @Test
    void testRegisterLoginSuccess() throws Exception {
        User user = new User();
        user.setId(42L);
        user.setUsername("user");
        user.setPassword("user");
        when(userRepository.findByUsername("user")).thenReturn(Optional.empty());
        when(userRepository.save(any())).thenReturn(user);

        mockMvc.perform(
                post("/login")
                        .param("name", "user")
                        .param("password", "user"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));

        when(userRepository.findByUsername("user")).thenReturn(Optional.of(user));

        mockMvc.perform(
                post("/login")
                        .param("name", "user")
                        .param("password", "user"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
    }

    @Test
    void testRegisterLoginFailure() throws Exception {
        User user = new User();
        user.setId(42L);
        user.setUsername("user");
        user.setPassword("user");
        when(userRepository.findByUsername("user")).thenReturn(Optional.empty());
        when(userRepository.save(any())).thenReturn(user);

        mockMvc.perform(
                post("/login")
                        .param("name", "user")
                        .param("password", "user"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));

        when(userRepository.findByUsername("user")).thenReturn(Optional.of(user));

        assertThrows(Exception.class,
                () -> mockMvc.perform(
                        post("/login")
                                .param("name", "user")
                                .param("password", "user1"))
                        .andExpect(status().isInternalServerError())
                        .andExpect(forwardedUrl("/WEB-INF/error.jsp")));
    }
}
