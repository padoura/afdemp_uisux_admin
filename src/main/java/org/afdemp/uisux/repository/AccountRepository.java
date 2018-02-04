package org.afdemp.uisux.repository;

import org.afdemp.uisux.domain.Account;
import org.afdemp.uisux.domain.security.UserRole;
import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends CrudRepository<Account, Long> {

	Account findByUserRole(UserRole userRole);
	
	Account findOne(Long id);
	
}
