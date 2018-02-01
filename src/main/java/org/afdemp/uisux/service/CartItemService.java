package org.afdemp.uisux.service;

import java.util.HashSet;

import org.afdemp.uisux.domain.Product;
import org.afdemp.uisux.domain.ShoppingCart;

public interface CartItemService {

	boolean addToCart(ShoppingCart shoppingCart, Product product, int qty);
	
	HashSet<Product> commitSale(ShoppingCart shoppingCart);
	
}
