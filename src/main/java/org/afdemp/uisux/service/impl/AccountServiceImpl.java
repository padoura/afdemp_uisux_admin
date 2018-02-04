package org.afdemp.uisux.service.impl;

import java.util.List;

import org.afdemp.uisux.domain.Account;
import org.afdemp.uisux.domain.Category;
import org.afdemp.uisux.repository.AccountRepository;
import org.afdemp.uisux.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountRepository accountRepository;

	@Override
	public List<Account> findAll() {
		// TODO Auto-generated method stub
		return (List<Account>) accountRepository.findAll();
	}

	@Override
	public Account findOne(Long id) {
		return accountRepository.findOne(id);
	}

}
