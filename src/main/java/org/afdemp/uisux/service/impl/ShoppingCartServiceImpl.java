package org.afdemp.uisux.service.impl;

import org.afdemp.uisux.domain.ShoppingCart;
import org.afdemp.uisux.repository.ShoppingCartRepository;
import org.afdemp.uisux.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.stereotype.Service;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService{
	
	@Autowired
	private ShoppingCartRepository shoppingCartRepository;
	
	@Override
	public boolean createShoppingCart(ShoppingCart shoppingCart)
	{
		try 
		{
			shoppingCart=shoppingCartRepository.save(shoppingCart);
			System.out.println("\nSUCCESS: Added ShoppingCart for user "+shoppingCart.getUserRole().getUser().getUsername()+".\n");
			return true;
		}
		catch (DataIntegrityViolationException e)
		{
			System.out.println("\nFAILURE:There's already a shopping cart for that user.\n");
			return false;
		}
		catch (InvalidDataAccessApiUsageException e)
		{
			System.out.println("\nFAILURE:Illegal Object. (ShoppingCart shoppingCart is null)\n");
			return false;
		}	
	}

}
