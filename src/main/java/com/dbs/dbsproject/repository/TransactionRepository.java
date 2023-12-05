package com.dbs.dbsproject.repository;

import com.dbs.dbsproject.domain.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    Page<Transaction> findAllByBuyerIdAndAndState (Pageable pageable, String buyerid, boolean state);
}
