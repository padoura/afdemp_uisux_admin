package org.afdemp.uisux.repository;

import org.afdemp.uisux.domain.CreditCard;
import org.afdemp.uisux.domain.security.UserRole;
import org.springframework.data.repository.CrudRepository;

public interface CreditCardRepository extends CrudRepository<CreditCard, Long> {
	
	CreditCard findByCardNumberAndUserRole(String creditCardNumber,UserRole userRole);

}
