package com.example.order_consumer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.order_consumer.model.Transaction;

/*
 * NOTE: Table is made in the docker module
 */
@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}