package com.example.databaseservice;

import com.example.databaseservice.model.ClientData;
import com.example.databaseservice.repository.ClientDataRepository;
import com.example.databaseservice.service.DatabaseService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.*;

@SpringBootTest
public class DatabaseServiceTest {

    @Autowired
    private DatabaseService databaseService;

    @Mock
    private ClientDataRepository clientDataRepository;

    @Test
    public void testSaveClientData() {
        String contacts = "name.x@example.com, +0000000";
        databaseService.saveClientData(contacts);

        verify(clientDataRepository, times(1)).save(any(ClientData.class));
    }
}