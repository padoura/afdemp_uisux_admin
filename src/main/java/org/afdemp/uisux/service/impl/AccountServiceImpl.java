package org.afdemp.uisux.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.afdemp.uisux.domain.Account;
import org.afdemp.uisux.domain.security.UserRole;
import org.afdemp.uisux.repository.AccountRepository;
import org.afdemp.uisux.repository.UserRoleRepository;
import org.afdemp.uisux.service.AccountService;
import org.afdemp.uisux.service.UserRoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {
	
	private static final Logger LOG = LoggerFactory.getLogger(AccountService.class);

	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private UserRoleRepository userRoleRepository;
	
	@Override
	public Account createAccount(UserRole userRole, double initialBalance)
	{
		Account account=accountRepository.findByUserRole(userRole);
		if(account==null)
		{
			account=new Account();
			account.setBalance(BigDecimal.valueOf(initialBalance));
			account.setUserRole(userRole);
			account=accountRepository.save(account);
			if(account!=null)
			{
				LOG.info("\n\n\nSUCCESS: Account for user {} succesfully created!\n\n",userRole.getUser().getUsername());
				return account;
			}
		}
		LOG.info("\n\n\nFAILURE: Failed to create account for user {}!\n\n",userRole.getUser().getUsername());
		return null;
	}
	
	
	

	@Override
	public List<Account> findAll() {
		return (List<Account>) accountRepository.findAll();
	}

	@Override
	public Account findOne(Long id) {
		return accountRepository.findOne(id);
	}




	@Override
	public boolean deposit(Account account, BigDecimal amount) 
	{
		account=accountRepository.findOne(account.getId());
		if(account!=null)
		{
			account.setBalance(account.getBalance().add(amount));
			if(accountRepository.save(account)!=null)
			{
				LOG.info("\n\n SUCCESS: Completed deposit of {}\n",amount);
				return true;
			}
		}
		LOG.info("\n\nFAILURE: Unable to complete deposit\n");
		return false;
	}




	@Override
	public boolean withdraw(Account account, BigDecimal amount) 
	{
		account=accountRepository.findOne(account.getId());
		if(account!=null && account.getBalance().compareTo(amount)>=0)
		{
			account.setBalance(account.getBalance().subtract(amount));
			if(accountRepository.save(account)!=null)
			{
				LOG.info("\n\n SUCCESS: Completed withdraw of {}\n",amount);
				return true;
			}
			
		}
		LOG.info("\n\nFAILURE: Withdraw Failed\n");
		return false;
	}




	@Override
	public boolean hasEnoughBalance(BigDecimal amount) {
		UserRole userRole = userRoleRepository.findOne(1L);
		BigDecimal currentBalance = accountRepository.findByUserRole(userRole).getBalance();
		return amount.compareTo(currentBalance) <= 0;
	}
	

}
