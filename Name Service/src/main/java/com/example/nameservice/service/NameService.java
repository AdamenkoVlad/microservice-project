package com.example.nameservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
public class NameService {

    @Autowired
    private JmsTemplate jmsTemplate;

    @JmsListener(destination = "nameServiceQueue")
    public void processClientId(String clientId) {
        String clientName = fetchClientName(clientId); // Імітація отримання ПІБ
        jmsTemplate.convertAndSend("addressServiceQueue", clientName);
    }

    private String fetchClientName(String clientId) {
        // Імітація отримання ПІБ
        return "Name X";
    }
}