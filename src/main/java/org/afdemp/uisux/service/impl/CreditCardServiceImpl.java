package org.afdemp.uisux.service.impl;

import java.util.List;

import org.afdemp.uisux.domain.CreditCard;
import org.afdemp.uisux.domain.security.UserRole;
import org.afdemp.uisux.repository.CreditCardRepository;
import org.afdemp.uisux.service.CreditCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreditCardServiceImpl implements CreditCardService {
	
	@Autowired
	private CreditCardRepository creditCardRepository;

	@Override
	public CreditCard createCreditCard(CreditCard creditCard) {
		
		CreditCard tempCard=creditCardRepository.findByCardNumberAndUserRole(creditCard.getCardNumber(),creditCard.getUserRole());
		if(tempCard==null)
		{
			
			tempCard=new CreditCard();
			tempCard.setUserRole(creditCard.getUserRole());
			tempCard.setBillingAddress(creditCard.getBillingAddress());
			tempCard.setCardNumber(creditCard.getCardNumber());
			tempCard.setCvc(creditCard.getCvc());
			tempCard.setExpiryMonth(creditCard.getExpiryMonth());
			tempCard.setExpiryYear(creditCard.getExpiryYear());
			tempCard.setHolderName(creditCard.getHolderName());
			tempCard.setType(creditCard.getType());
			tempCard=creditCardRepository.save(creditCard);
		}
		
		return tempCard;
	}

	@Override
	public CreditCard findById(Long creditCardId) {
		return creditCardRepository.findOne(creditCardId);
	}
	
	@Override
	public void setDefaultCreditCard(Long defaultCreditCardId, UserRole userRole) {
		
		List<CreditCard> creditCardList = userRole.getCreditCardList();
		
		for (CreditCard cc : creditCardList) {
			if (cc.isDefaultCreditCard()) {
				cc.setDefaultCreditCard(false);
				creditCardRepository.save(cc);
			}
			if (cc.getId() == defaultCreditCardId) {
				cc.setDefaultCreditCard(true);
				creditCardRepository.save(cc);
			}
		}	
	}

	@Override
	public void removeFromUserRole(Long creditCardId, UserRole userRole) {
		CreditCard creditCard = creditCardRepository.findOne(creditCardId);
		creditCard.setUserRole(null);
		creditCardRepository.save(creditCard);
	}

}
