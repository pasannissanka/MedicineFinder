package com.pasan.medifinder.cloud.oauth.service.repositories;

import com.pasan.medifinder.cloud.oauth.service.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, String> {
    Optional<Client> findByClientId(String clientId);
}