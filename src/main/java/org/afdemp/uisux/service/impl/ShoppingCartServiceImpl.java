package org.afdemp.uisux.service.impl;

import java.math.BigDecimal;
import java.util.HashSet;

import org.afdemp.uisux.domain.CartItem;
import org.afdemp.uisux.domain.ShoppingCart;
import org.afdemp.uisux.domain.security.UserRole;
import org.afdemp.uisux.repository.CartItemRepository;
import org.afdemp.uisux.repository.ShoppingCartRepository;
import org.afdemp.uisux.service.ShoppingCartService;
import org.afdemp.uisux.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService{
	
	private static final Logger LOG = LoggerFactory.getLogger(UserService.class);
	
	@Autowired
	private ShoppingCartRepository shoppingCartRepository;
	
	@Autowired
	private CartItemRepository cartItemRepository;
	
	@Override
	public boolean createShoppingCart(UserRole userRole)
	{
	
		ShoppingCart shoppingCart=shoppingCartRepository.findByUserRole(userRole);
		
			if(userRole!=null && userRole.getUser()!=null)
			{
				shoppingCart=new ShoppingCart();
				shoppingCart.setUserRole(userRole);
				shoppingCart.setGrandTotal(BigDecimal.valueOf(0));
				shoppingCart=shoppingCartRepository.save(shoppingCart);
				
				LOG.info("\n\n\nSUCCESS: Shopping Cart for user {} succesfully created.\n\n",userRole.getUser().getUsername());
				return true;
			}
			else
			{
				LOG.info("\n\n\nFAILURE:Invalid Argument passed\n\n");
				return false;
			}
		
		
		
		

	}
	
	
}
