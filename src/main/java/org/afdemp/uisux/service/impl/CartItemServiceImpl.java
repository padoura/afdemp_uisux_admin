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
			
			System.out.print("\n\nCartItem =new\n\n");
			cartItem.setShoppingCart(shoppingCart);
			cartItem.setProduct(product);
			cartItem.setQty(qty);
			
			System.out.print("\n\nCartItem Before SAVE\n\n");
			cartItemRepository.save(cartItem);
			
			System.out.println("\n\nCartItem created");
			
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
	public boolean commitSale(ShoppingCart shoppingCart)
	{
		
		AbstractSale abstractSale=new ClientOrder();
		abstractSale=clientOrderService.createClientOrder(shoppingCart.getUserRole(),shoppingCartService.CalculateGrandTotal(shoppingCart),null,null, null);
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
				ci.setCurrentPrice(ci.getProduct().getOurPrice()); 
				ci.setAbstractSale(abstractSale);
				cartItemRepository.save(ci);
			}
			return true;
		}
	}

}
