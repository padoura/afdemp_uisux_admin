package org.afdemp.uisux.repository;

import java.util.HashSet;

import org.afdemp.uisux.domain.CartItem;
import org.afdemp.uisux.domain.Product;
import org.afdemp.uisux.domain.ShoppingCart;
import org.springframework.data.repository.CrudRepository;

public interface CartItemRepository extends CrudRepository<CartItem, Long> {

	CartItem findByShoppingCartAndProduct(ShoppingCart shoppingCart, Product product);
	
	HashSet<CartItem> findByShoppingCart(ShoppingCart shoppingCart);
	
}
