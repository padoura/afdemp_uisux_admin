package org.afdemp.uisux.service;

import org.afdemp.uisux.domain.Product;
import org.afdemp.uisux.domain.ShoppingCart;

public interface CartItemService {

	boolean addToCart(ShoppingCart shoppingCart, Product product, int qty);
	
	boolean commitSale(ShoppingCart shoppingCart);
	
}
