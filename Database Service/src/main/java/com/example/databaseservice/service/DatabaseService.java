package com.example.databaseservice.service;

import com.example.databaseservice.model.ClientData;
import com.example.databaseservice.repository.ClientDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

@Service
public class DatabaseService {

    @Autowired
    private ClientDataRepository clientDataRepository;

    @JmsListener(destination = "databaseServiceQueue")
    public void saveClientData(String contacts) {
        ClientData clientData = new ClientData();
        clientData.setName("Name X"); // Приклад даних
        clientData.setAddress("Vinnytsia");
        clientData.setContacts(contacts);
        clientDataRepository.save(clientData);
    }
}