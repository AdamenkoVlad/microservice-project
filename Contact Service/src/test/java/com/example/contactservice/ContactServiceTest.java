package com.example.contactservice;

import com.example.contactservice.service.ContactService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jms.core.JmsTemplate;

import static org.mockito.Mockito.*;

@SpringBootTest
public class ContactServiceTest {

    @Autowired
    private ContactService contactService;

    @Mock
    private JmsTemplate jmsTemplate;

    @Test
    public void testProcessClientAddress() {
        contactService.processClientAddress("Vinnytsia");

        verify(jmsTemplate, times(1)).convertAndSend(eq("databaseServiceQueue"), anyString());
    }
}