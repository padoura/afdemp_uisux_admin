package org.afdemp.uisux.service;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;

import org.afdemp.uisux.domain.AbstractSale;
import org.afdemp.uisux.domain.Address;
import org.afdemp.uisux.domain.CartItem;
import org.afdemp.uisux.domain.ClientOrder;
import org.afdemp.uisux.domain.CreditCard;
import org.afdemp.uisux.domain.Product;
import org.afdemp.uisux.domain.ShoppingCart;

public interface CartItemService {

	boolean addToCart(ShoppingCart shoppingCart, Product product, int qty);
	
	boolean removeCartItem(Long id,Long shoppingCartId);
	
	boolean emptyCart(Long shoppingCartId);
	
	HashSet<Product> commitSale(ShoppingCart shoppingCart,CreditCard creditCard,Address billingAddress,Address shippingAddress,String shippingMethod);
	
	ClientOrder commitAndGetSale(ShoppingCart shoppingCart,CreditCard creditCard, Address billingAddress,Address shippingAddress,String shippingMethod);


	List<CartItem> findByAbstractSale(AbstractSale abstractSale);

	ClientOrder commitPastSale(ShoppingCart shoppingCart, CreditCard creditCard, Address billingAddress,
			Address shippingAddress, String shippingMethod, Timestamp pastOrderDate);

	
}
