package org.afdemp.uisux.repository;

import java.util.HashSet;

import org.afdemp.uisux.domain.CartItem;
import org.afdemp.uisux.domain.Product;
import org.afdemp.uisux.domain.ShoppingCart;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface CartItemRepository extends CrudRepository<CartItem, Long> {

	CartItem findByShoppingCartAndProduct(ShoppingCart shoppingCart, Product product);
	
	HashSet<CartItem> findByShoppingCart(ShoppingCart shoppingCart);
	
	@Transactional
	@Modifying
	@Query("UPDATE Product p SET p.inStockNumber=p.inStockNumber-:qty WHERE p=:product AND p.inStockNumber>=:qty")
	int checkAvailabilityAndUpdate(@Param("product") Product product,@Param("qty") long qty);
	
}
