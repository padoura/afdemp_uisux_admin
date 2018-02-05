package org.afdemp.uisux.service.impl;

import org.afdemp.uisux.domain.CreditCard;
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
		
		CreditCard tempCard=creditCardRepository.findOne(creditCard.getId());
		if(tempCard==null)
		{
			tempCard=creditCard;
			tempCard=creditCardRepository.save(creditCard);
		}
		
		return tempCard;
	}

}
