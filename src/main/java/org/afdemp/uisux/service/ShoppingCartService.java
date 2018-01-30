package org.afdemp.uisux.service;

import org.afdemp.uisux.domain.security.UserRole;

public interface ShoppingCartService {

	boolean createShoppingCart(UserRole userRole);
	
}
