package com.example.bankpoc.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.bankpoc.models.entity.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    Client findByCpf(String cpf);

    Client findByAccountId(Long accountId);
}
