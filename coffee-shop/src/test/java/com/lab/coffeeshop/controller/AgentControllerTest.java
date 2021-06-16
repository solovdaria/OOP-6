package com.lab.coffeeshop.controller;

import com.lab.coffeeshop.repository.AgentRepository;
import com.lab.coffeeshop.repository.ShopRepository;
import com.lab.coffeeshop.repository.UserRepository;
import com.lab.coffeeshop.service.AgentService;
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

@WebMvcTest(AgentController.class)
@Import({AgentService.class, UserService.class})
class AgentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AgentRepository agentRepository;

    @MockBean
    private ShopRepository shopRepository;

    @MockBean
    private UserRepository userRepository;

    @Test
    void testGet() throws Exception {
        when(agentRepository.existsById(42L)).thenReturn(true);

        mockMvc.perform(
                get("/agent")
                        .cookie(new Cookie("username", "admin"))
                        .param("delete", "42"))
                .andExpect(status().isOk())
                .andExpect(forwardedUrl("/WEB-INF/agent.jsp"));
    }

    @Test
    void testPost() throws Exception {
        mockMvc.perform(
                post("/agent")
                        .cookie(new Cookie("username", "admin"))
                        .param("agent_name", "John")
                        .param("agency_id", "23"))
                .andExpect(status().isOk())
                .andExpect(forwardedUrl("/WEB-INF/agent.jsp"));
    }
}