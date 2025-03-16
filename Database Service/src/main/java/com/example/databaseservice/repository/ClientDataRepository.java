package com.example.databaseservice.repository;

import com.example.databaseservice.model.ClientData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientDataRepository extends JpaRepository<ClientData, Long> {
}