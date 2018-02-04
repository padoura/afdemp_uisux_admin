package org.afdemp.uisux.service;

import java.util.List;

import org.afdemp.uisux.domain.Account;
import org.afdemp.uisux.domain.security.UserRole;

public interface AccountService {
	
	Account createAccount(UserRole userRole, double initialBalance);

	List<Account> findAll();

	Account findOne(Long id);

}
