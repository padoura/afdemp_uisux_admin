package org.afdemp.uisux.service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

import org.afdemp.uisux.domain.AbstractSale;
import org.afdemp.uisux.domain.Account;
import org.afdemp.uisux.domain.Transaction;

public interface TransactionService {

	List<Transaction> fetchAccountWithdrawsByPeriod(Account account, Timestamp from, Timestamp to);

	List<Transaction> fetchAccountDepositsByPeriod(Account account, Timestamp from, Timestamp to);
	
	Transaction oneWayTransaction(BigDecimal amount, AbstractSale abstractSale);
	
	Transaction twoWayTransaction(BigDecimal amount, Account fromAccount, Account toAccount, AbstractSale abstractSale);

}
