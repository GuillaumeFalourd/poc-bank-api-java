package com.example.bankpoc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import com.example.bankpoc.models.entity.CashOut;

@Repository
public interface CashOutRepository extends JpaRepository<CashOut, Long> {

    @Query(value = "SELECT * FROM cash_out WHERE account_id = ?1", name =
            "findCaschOut", nativeQuery = true)
    List<CashOut> getCashOutById_account(Long accountId);
}
