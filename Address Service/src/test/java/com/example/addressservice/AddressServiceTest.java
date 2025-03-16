package com.example.addressservice;

import com.example.addressservice.service.AddressService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jms.core.JmsTemplate;

import static org.mockito.Mockito.*;

@SpringBootTest
public class AddressServiceTest {

    @Autowired
    private AddressService addressService;

    @Mock
    private JmsTemplate jmsTemplate;

    @Test
    public void testProcessClientName() {
        addressService.processClientName("Name X");

        verify(jmsTemplate, times(1)).convertAndSend(eq("contactServiceQueue"), anyString());
    }
}