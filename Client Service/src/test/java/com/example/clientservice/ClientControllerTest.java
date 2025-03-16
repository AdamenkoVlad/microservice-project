package com.example.clientservice;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.example.clientservice.controller.ClientController;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.http.MediaType;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(ClientController.class)
public class ClientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private JmsTemplate jmsTemplate;

    @Test
    public void testReceiveClientData() throws Exception {
        mockMvc.perform(post("/client/data")
                        .header("sid", "valid-sid")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\": \"123\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string("Request received"));

        verify(jmsTemplate, times(1)).convertAndSend(eq("nameServiceQueue"), eq("123"));
    }

    @Test
    public void testInvalidSid() throws Exception {
        mockMvc.perform(post("/client/data")
                        .header("sid", "invalid-sid")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\": \"123\"}"))
                .andExpect(status().isUnauthorized());
    }
}