package org.afdemp.uisux.service.impl;

import org.afdemp.uisux.domain.CartItem;
import org.afdemp.uisux.repository.CartItemRepository;
import org.afdemp.uisux.service.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.stereotype.Service;

@Service
public class CartItemServiceImpl implements CartItemService{
	
	@Autowired
	private CartItemRepository cartItemRepository;
	
	public boolean createCartItem(CartItem cartItem)
	{
		try 
		{
			cartItem=cartItemRepository.save(cartItem);
			System.out.println("\nSUCCESS: Added cartItem "+cartItem.getProduct().getName()+".\n");
			return true;
		}
		catch (DataIntegrityViolationException e)
		{
			System.out.println("\nFAILURE:Cannot add the same product in the same Shopping Cart.\n");
			return false;
		}
		catch (InvalidDataAccessApiUsageException e)
		{
			System.out.println("\nFAILURE:Illegal Object. (CartItem cartItem is null)\n");
			return false;
		}	
	}

}
