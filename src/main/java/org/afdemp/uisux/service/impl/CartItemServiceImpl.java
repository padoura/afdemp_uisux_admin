package org.afdemp.uisux.service.impl;

import java.math.BigDecimal;
import java.util.HashSet;

import org.afdemp.uisux.domain.AbstractSale;
import org.afdemp.uisux.domain.Address;
import org.afdemp.uisux.domain.CartItem;
import org.afdemp.uisux.domain.ClientOrder;
import org.afdemp.uisux.domain.CreditCard;
import org.afdemp.uisux.domain.Product;
import org.afdemp.uisux.domain.ShoppingCart;
import org.afdemp.uisux.repository.CartItemRepository;
import org.afdemp.uisux.service.AccountService;
import org.afdemp.uisux.service.CartItemService;
import org.afdemp.uisux.service.ClientOrderService;
import org.afdemp.uisux.service.ShoppingCartService;
import org.afdemp.uisux.service.TransactionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartItemServiceImpl implements CartItemService{
	
	private static final Logger LOG = LoggerFactory.getLogger(CartItemService.class);
	
	@Autowired
	private CartItemRepository cartItemRepository;
	
	@Autowired
	private ShoppingCartService shoppingCartService;
	
	@Autowired
	private ClientOrderService clientOrderService;
	
	@Autowired
	private TransactionService transactionService;
	
	@Autowired
	private AccountService accountService;
	
	
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
	public HashSet<Product> commitSale(ShoppingCart shoppingCart,CreditCard creditCard, Address billingAddress,Address shippingAddress,String shippingMethod)
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
			BigDecimal total=CalculateGrandTotal(itemsInCart);
			ClientOrder clientOrder=new ClientOrder();
			clientOrder.setUserRole(shoppingCart.getUserRole());
			clientOrder.setTotal(total);
			clientOrder.setBillingAddress(billingAddress);
			clientOrder.setShippingAddress(shippingAddress);
			clientOrder.setShippingMethod(shippingMethod);
			clientOrder.setCreditCard(creditCard);
			abstractSale=clientOrderService.createClientOrder(clientOrder);
			for(CartItem ci: itemsInCart)
			{
				ci.setShoppingCart(null);
				ci.setCurrentPrice(ci.getProduct().getOurPrice()); 
				ci.setAbstractSale(abstractSale);
				cartItemRepository.save(ci);
			}
			transactionService.oneWayTransaction(total, abstractSale);
			
			
			return itemsUnavailable;
		}
	}
	
	@Override
	public boolean removeCartItem(Long id,Long shoppingCartId)
	{
		if(cartItemRepository.deleteByIdAndShoppingCartId(id,shoppingCartId)>0)
		{
			LOG.info("\n\nSUCCESS: Removed cartItem {} from shoppingCart\n",id);
			return true;
		}
		LOG.info("\n\nFAILURE: Removing cartItem {} failed miserably.\n",id);
		return false;
	}
	
	@Override
	public boolean emptyCart(Long shoppingCartId)
	{
		if(cartItemRepository.deleteByShoppingCartId(shoppingCartId)>0)
		{
			LOG.info("\n\nSUCCESS: Shopping Cart cleared.");
			return true;
		}
		LOG.info("\n\nFAILURE: Emptying the Shopping Cart {} failed miserably.");
		return false;
	}
	
	private BigDecimal CalculateGrandTotal(HashSet<CartItem> itemsInCart)
	{
		BigDecimal grandTotal=BigDecimal.valueOf(0);
			
		for(CartItem ci: itemsInCart)
		{
			grandTotal=grandTotal.add(ci.getProduct().getOurPrice().multiply(BigDecimal.valueOf(ci.getQty())));
		}
		
		return grandTotal;
	}
	
}
