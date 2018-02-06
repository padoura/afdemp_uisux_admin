package org.afdemp.uisux.repository;

import java.sql.Timestamp;
import java.util.List;

import org.afdemp.uisux.domain.Account;
import org.afdemp.uisux.domain.Transaction;
import org.springframework.data.repository.CrudRepository;

public interface TransactionRepository extends CrudRepository <Transaction, Long>{

	List<Transaction> findByWithdrawAccountAndDateTimeBetween(Account withdrawAccount, Timestamp from, Timestamp to);
	
	List<Transaction> findByDepositAccountAndDateTimeBetween(Account depositAccount, Timestamp from, Timestamp to);
	
}
