package com.example.bankpoc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.example.bankpoc.models.entity.Deposit;

@Repository
public interface DepositRepository extends JpaRepository<Deposit, Long> {

    @Query(value = "SELECT * FROM deposit WHERE id_account = ?1", name =
            "findDeposits", nativeQuery = true)
    List<Deposit> findCustomerDeposits(Long id);
}
