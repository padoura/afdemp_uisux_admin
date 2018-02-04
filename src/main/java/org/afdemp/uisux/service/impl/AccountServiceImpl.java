package org.afdemp.uisux.service.impl;

import java.util.List;

import org.afdemp.uisux.domain.Account;
import org.afdemp.uisux.domain.security.UserRole;
import org.afdemp.uisux.repository.AccountRepository;
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
	
	@Override
	public Account createAccount(UserRole userRole, double initialBalance)
	{
		Account account=accountRepository.findByUserRole(userRole);
		if(account==null)
		{
			account=new Account();
			account.setBalance(initialBalance);
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

}
