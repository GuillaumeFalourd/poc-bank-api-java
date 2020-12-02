package com.example.bankpoc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.bankpoc.models.entity.Transfer;

@Repository
public interface TransferRepository extends JpaRepository<Transfer, Long> {

    @Query(value = "SELECT * FROM transfer WHERE account_id_recipient = ?1 OR account_id_desposit = ?1", name =
            "findTransactions", nativeQuery = true)
    List<Transfer> findByTransactionWithId(Long id);
}
