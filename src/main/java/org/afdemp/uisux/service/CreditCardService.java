package org.afdemp.uisux.service;

import org.afdemp.uisux.domain.CreditCard;
import org.afdemp.uisux.domain.security.UserRole;

public interface CreditCardService {
	
	CreditCard createCreditCard(CreditCard creditCard);

	CreditCard findById(Long creditCardId);

	void setDefaultCreditCard(Long defaultCreditCardId, UserRole userRole);

	void removeFromUserRole(Long creditCardId, UserRole userRole);
	
	
	

}
