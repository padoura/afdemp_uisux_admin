package org.afdemp.uisux.service.impl;

import java.util.HashSet;

import org.afdemp.uisux.domain.AbstractSale;
import org.afdemp.uisux.domain.CartItem;
import org.afdemp.uisux.domain.ClientOrder;
import org.afdemp.uisux.domain.Product;
import org.afdemp.uisux.domain.ShoppingCart;
import org.afdemp.uisux.repository.CartItemRepository;
import org.afdemp.uisux.service.CartItemService;
import org.afdemp.uisux.service.ClientOrderService;
import org.afdemp.uisux.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartItemServiceImpl implements CartItemService{
	
	@Autowired
	private CartItemRepository cartItemRepository;
	
	@Autowired
	private ShoppingCartService shoppingCartService;
	
	@Autowired
	private ClientOrderService clientOrderService;
	
	
	//ShoppingCart,Product CANNOT be null AND qty CANNOT be any value less than 1 (Controller has to check)
	@Override
	public boolean addToCart(ShoppingCart shoppingCart, Product product, int qty)
	{
		CartItem cartItem=new CartItem();
		cartItem=cartItemRepository.findByShoppingCartAndProduct(shoppingCart, product);
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
			
			System.out.println("\n\nCartItem modified");
			return true;
		}
		return false;
	}
	
	@Override
	public HashSet<Product> commitSale(ShoppingCart shoppingCart)
	{
		AbstractSale abstractSale=new ClientOrder();
		HashSet<Product> itemsUnavailable=new HashSet<Product>();
		HashSet<CartItem> itemsInCart=new HashSet<CartItem>();
		itemsInCart=cartItemRepository.findByShoppingCart(shoppingCart);
		
		//Checks if CartItem X in shopping Cart exists in coop's warehouse
		//and if not then it is removed from itemsInCart about to be passed for sale
		for(CartItem ci: itemsInCart)
		{
			if(cartItemRepository.checkAvailabilityAndUpdate(ci.getProduct(),ci.getQty())==0)
			{
				itemsUnavailable.add(ci.getProduct());
				itemsInCart.remove(ci);
			}
		}
				
		if(itemsInCart.isEmpty())
		{
			return itemsUnavailable;
		}
		else
		{
			ClientOrder clientOrder=new ClientOrder();
			clientOrder.setUserRole(shoppingCart.getUserRole());
			clientOrder.setTotal(shoppingCartService.CalculateGrandTotal(shoppingCart));
			abstractSale=clientOrderService.createClientOrder(clientOrder);
			for(CartItem ci: itemsInCart)
			{
				ci.setShoppingCart(null);
				ci.setCurrentPrice(ci.getProduct().getOurPrice()); 
				ci.setAbstractSale(abstractSale);
				cartItemRepository.save(ci);
			}
			return itemsUnavailable;
		}
	}
	
	//@Override
	public boolean putProductUpForSale(ShoppingCart shoppingCart, Product product, int qty)
	{
		CartItem cartItem=new CartItem();
		cartItem=cartItemRepository.findByShoppingCartAndProduct(shoppingCart, product);
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
			
			System.out.println("\n\nCartItem modified");
			return true;
		}
		return false;
	}

}
