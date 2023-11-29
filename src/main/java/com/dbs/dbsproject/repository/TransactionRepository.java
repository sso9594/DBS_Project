package com.dbs.dbsproject.repository;

import com.dbs.dbsproject.domain.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

}
