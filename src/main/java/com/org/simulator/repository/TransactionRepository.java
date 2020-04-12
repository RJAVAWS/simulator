package com.org.simulator.repository;

import com.org.simulator.domain.Transaction;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Transaction entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    Page<Transaction> findAllByBank_Id(Long id, Pageable pageable);

}
