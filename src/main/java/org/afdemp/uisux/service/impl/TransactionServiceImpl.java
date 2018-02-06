package org.afdemp.uisux.service.impl;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import org.afdemp.uisux.domain.AbstractSale;
import org.afdemp.uisux.domain.Account;
import org.afdemp.uisux.domain.Transaction;
import org.afdemp.uisux.repository.TransactionRepository;
import org.afdemp.uisux.service.AccountService;
import org.afdemp.uisux.service.TransactionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionServiceImpl implements TransactionService {
	
	private static final Logger LOG = LoggerFactory.getLogger(TransactionService.class);
	
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private TransactionRepository transactionRepository;

	@Override
	public List<Transaction> fetchAccountWithdrawsByPeriod(Account withdrawAccount, Timestamp from, Timestamp to) {
		List<Transaction> transactionList=transactionRepository.findByWithdrawAccountAndDateTimeBetween(withdrawAccount, from, to);
		return transactionList;
	}

	@Override
	public List<Transaction> fetchAccountDepositsByPeriod(Account depositAccount, Timestamp from, Timestamp to) {
		List<Transaction> transactionList=transactionRepository.findByDepositAccountAndDateTimeBetween(depositAccount, from, to);
		return transactionList;
	}

	@Override
	public Transaction oneWayTransaction(BigDecimal amount, AbstractSale abstractSale) {
		Account account=accountService.findAdminAccount();
		if(accountService.deposit(account, amount))
		{
			Transaction transaction=new Transaction();
			transaction.setAbstractSale(abstractSale);
			transaction.setAmount(amount);
			transaction.setDateTime(Timestamp.valueOf(LocalDateTime.now()));
			transaction.setDepositAccount(account);
			transaction =transactionRepository.save(transaction);
			if(transaction!=null)
			{
				LOG.info("\n\nSUCCESS:{} Euros have been added to admin's account\n",amount);
				return transaction;
			}
			LOG.info("\n\nFAILURE: Succesfully deposited {} Euros to admin's account but failed to persist the transaction\n\n",amount);
		}
		LOG.info("\n\nFAILURE: Unable to deposit to admin's acount");
		return null;
	}

	@Override
	public Transaction twoWayTransaction(BigDecimal amount, Account fromAccount, Account toAccount, AbstractSale abstractSale) {

		if(accountService.withdraw(fromAccount, amount))
		{
			LOG.info("\n\nSUCCESS:{} Euros have been withdrawn from {}'s account.\n",amount,fromAccount.getUserRole().getUser().getUsername());
			accountService.deposit(toAccount, amount);
			LOG.info("\n\nSUCCESS:{} Euros have been deposited to {}'s account.\n",amount,toAccount.getUserRole().getUser().getUsername());
			Transaction transaction=new Transaction();
			transaction.setAbstractSale(abstractSale);
			transaction.setAmount(amount);
			transaction.setDateTime(Timestamp.valueOf(LocalDateTime.now()));
			transaction.setDepositAccount(toAccount);
			transaction.setWithdrawAccount(fromAccount);
			transaction=transactionRepository.save(transaction);
			if(transaction!=null)
			{
				LOG.info("\n\nSUCCESS: Transaction Added.");
				return transaction;
			}
		}
			
		return null;
	}

}
