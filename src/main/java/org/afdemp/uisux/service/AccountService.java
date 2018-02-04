package org.afdemp.uisux.service;

import java.util.List;

import org.afdemp.uisux.domain.ClientOrder;
import org.afdemp.uisux.domain.Account;

public interface AccountService {

	List<Account> findAll();

	Account findOne(Long id);

	List<ClientOrder> findAllUndistributedEarnings();

}
