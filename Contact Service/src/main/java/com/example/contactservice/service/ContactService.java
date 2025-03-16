package com.example.contactservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

// ContactService.java
@Service
public class ContactService {

    @Autowired
    private JmsTemplate jmsTemplate;

    @JmsListener(destination = "contactServiceQueue")
    public void processClientAddress(String clientAddress) {
        String clientContacts = fetchClientContacts(clientAddress); // Імітація отримання контактів
        jmsTemplate.convertAndSend("databaseServiceQueue", clientContacts);
    }

    private String fetchClientContacts(String clientAddress) {
        // Імітація отримання контактів
        return "name.x@example.com, +0000000";
    }
}