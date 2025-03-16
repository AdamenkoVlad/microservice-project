package com.example.addressservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
public class AddressService {

    @Autowired
    private JmsTemplate jmsTemplate;

    @JmsListener(destination = "addressServiceQueue")
    public void processClientName(String clientName) {
        String clientAddress = fetchClientAddress(clientName); // Імітація отримання адреси
        jmsTemplate.convertAndSend("contactServiceQueue", clientAddress);
    }

    private String fetchClientAddress(String clientName) {
        // Імітація отримання адреси
        return "Vinnytsia";
    }
}