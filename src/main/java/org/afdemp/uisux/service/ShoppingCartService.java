package org.afdemp.uisux.service;

import java.math.BigDecimal;

import org.afdemp.uisux.domain.ShoppingCart;
import org.afdemp.uisux.domain.security.UserRole;

public interface ShoppingCartService {

	boolean createShoppingCart(UserRole userRole);
	
	
	
}
