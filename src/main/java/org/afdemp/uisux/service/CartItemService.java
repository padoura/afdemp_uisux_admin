package org.afdemp.uisux.service;

import java.util.HashSet;

import org.afdemp.uisux.domain.Product;
import org.afdemp.uisux.domain.ShoppingCart;

public interface CartItemService {

	boolean addToCart(ShoppingCart shoppingCart, Product product, int qty);
	
	boolean removeCartItem(Long id,Long shoppingCartId);
	
	boolean emptyCart(Long shoppingCartId);
	
	HashSet<Product> commitSale(ShoppingCart shoppingCart);
	
}
