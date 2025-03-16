package com.example.clientservice.controller;


import com.example.clientservice.model.ClientRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/client")
public class ClientController {

    @Autowired
    private JmsTemplate jmsTemplate;

    @PostMapping("/data")
    public ResponseEntity<String> receiveClientData(@RequestHeader("sid") String sid, @RequestBody ClientRequest request) {
        if (!validateSid(sid)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid SID");
        }
        jmsTemplate.convertAndSend("nameServiceQueue", request.id());
        return ResponseEntity.ok("Request received");
    }

    private boolean validateSid(String sid) {
        // Проста імітація перевірки SID
        return "valid-sid".equals(sid);
    }
}