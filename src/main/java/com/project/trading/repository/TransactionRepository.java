/**
 * 
 */
package com.project.trading.repository;

import com.project.trading.model.entity.Account;
import com.project.trading.model.entity.Transaction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TransactionRepository extends CrudRepository<Transaction, Integer>{
    public List<Transaction> findByAccountId(Integer id);
}
