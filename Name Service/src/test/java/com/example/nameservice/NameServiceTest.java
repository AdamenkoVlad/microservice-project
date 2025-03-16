package com.example.nameservice;

import com.example.nameservice.service.NameService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jms.core.JmsTemplate;

import static org.mockito.Mockito.*;

@SpringBootTest
public class NameServiceTest {

    @Autowired
    private NameService nameService;

    @Mock
    private JmsTemplate jmsTemplate;

    @Test
    public void testProcessClientId() {
        nameService.processClientId("123");

        verify(jmsTemplate, times(1)).convertAndSend(eq("addressServiceQueue"), anyString());
    }
}