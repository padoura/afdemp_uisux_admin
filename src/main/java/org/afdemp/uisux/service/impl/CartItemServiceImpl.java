package org.afdemp.uisux.service.impl;

import java.math.BigDecimal;
import java.util.HashSet;

import org.afdemp.uisux.domain.AbstractSale;
import org.afdemp.uisux.domain.CartItem;
import org.afdemp.uisux.domain.ClientOrder;
import org.afdemp.uisux.domain.Product;
import org.afdemp.uisux.domain.ShoppingCart;
import org.afdemp.uisux.repository.CartItemRepository;
import org.afdemp.uisux.service.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartItemServiceImpl implements CartItemService{
	
	@Autowired
	private CartItemRepository cartItemRepository;
	
	
	//ShoppingCart,Product CANNOT be null AND qty CANNOT be any value less than 1 (Controller has to check)
	@Override
	public boolean addToCart(ShoppingCart shoppingCart, Product product, int qty)
	{
		CartItem cartItem=cartItemRepository.findByShoppingCartAndProduct(shoppingCart, product);
		if(cartItem==null)
		{	
			cartItem=new CartItem();
			cartItem.setShoppingCart(shoppingCart);
			cartItem.setProduct(product);
			cartItem.setQty(qty);
			cartItemRepository.save(cartItem);
			return true;
		}
		else if (cartItem !=null && qty >0)
		{
			cartItem.setQty(cartItem.getQty()+qty);
			cartItemRepository.save(cartItem);
			return true;
		}
		return false;
	}
	
	
	public boolean commitSale(ShoppingCart shoppingCart)
	{
		AbstractSale abstractSale=new ClientOrder();
		//create AbstractSale
		HashSet<CartItem> itemsInCart=new HashSet<CartItem>();
		itemsInCart=cartItemRepository.findByShoppingCart(shoppingCart);
		if(itemsInCart.isEmpty())
		{
			return false;
		}
		else
		{
			for(CartItem ci: itemsInCart)
			{
				ci.setShoppingCart(null);
				//Need to change double to BigDecimal on prices in Product and remove valueOf
				ci.setCurrentPrice(BigDecimal.valueOf(ci.getProduct().getOurPrice())); 
				ci.setAbstractSale(abstractSale);
				cartItemRepository.save(ci);
			}
			return true;
		}
	}

}
