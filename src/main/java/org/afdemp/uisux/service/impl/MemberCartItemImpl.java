package org.afdemp.uisux.service.impl;

import java.util.Date;
import java.util.List;

import org.afdemp.uisux.domain.AbstractSale;
import org.afdemp.uisux.domain.MemberCartItem;
import org.afdemp.uisux.domain.MemberSale;
import org.afdemp.uisux.domain.Product;
import org.afdemp.uisux.domain.ShoppingCart;
import org.afdemp.uisux.repository.MemberCartItemRepository;
import org.afdemp.uisux.service.ClientOrderService;
import org.afdemp.uisux.service.MemberCartItemService;
import org.afdemp.uisux.service.MemberSaleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberCartItemImpl implements MemberCartItemService{
	
	private static final Logger LOG = LoggerFactory.getLogger(ClientOrderService.class);
	
	@Autowired
	private MemberCartItemRepository memberCartItemRepository;
	
	@Autowired 
	private MemberSaleService memberSaleService;
	
	@Override
	public MemberCartItem findById(Long memberCartItemId) {
		
		return memberCartItemRepository.findOne(memberCartItemId);
	}

	@Override
	public boolean putUpForSale(Product product, int qty, ShoppingCart shoppingCart) {

		MemberCartItem memberCartItem;
		memberCartItem=memberCartItemRepository.findByShoppingCartAndProduct(shoppingCart, product);
		if(memberCartItem==null)
		{	
			memberCartItem=new MemberCartItem();
			
			memberCartItem.setShoppingCart(shoppingCart);
			memberCartItem.setProduct(product);
			memberCartItem.setQty(qty);
			memberCartItem.setCurrentPurchasePrice(product.getPriceBought());
			
			memberCartItemRepository.save(memberCartItem);
			
			return true;
		}
		else if (memberCartItem !=null && qty >0)
		{
			memberCartItem.setQty(memberCartItem.getQty()+qty);
			memberCartItem.setCurrentPurchasePrice(memberCartItem.getProduct().getPriceBought());
			memberCartItemRepository.save(memberCartItem);
			
			
			System.out.println("\n\nMemberCartItem modified");
			return true;
		}
		return false;
	
	}

	@Override
	public boolean fullPurchaseFromMember(MemberCartItem memberCartItem) {
		
		MemberSale memberSale=new MemberSale();
		memberSale.setUserRole(memberCartItem.getShoppingCart().getUserRole());
		memberSale.setSubmittedDate(new Date());
		AbstractSale abstractSale=memberSaleService.createMemberSale(memberSale);
		
		if(memberCartItemRepository.fullPurchase(memberCartItem.getProduct(),memberCartItem.getShoppingCart(),abstractSale)>0)
		{
			LOG.info("\n\n\nSUCCESS: Purchase successful\n\n");
			return true;
		}
		
		return false;
	}

	@Override
	public boolean partialPurchaseFromMember(MemberCartItem memberCartItem, int qty) {

		if(memberCartItemRepository.partialPurchase(memberCartItem.getProduct(), qty, memberCartItem.getShoppingCart())>0)
		{
		MemberSale memberSale=new MemberSale();
		memberSale.setUserRole(memberCartItem.getShoppingCart().getUserRole());
		memberSale.setSubmittedDate(new Date());
		AbstractSale abstractSale=memberSaleService.createMemberSale(memberSale);
		
		MemberCartItem soldCartItem=new MemberCartItem();
		soldCartItem.setAbstractSale(abstractSale);
		soldCartItem.setCurrentPurchasePrice(memberCartItem.getCurrentPurchasePrice());
		soldCartItem.setProduct(memberCartItem.getProduct());
		soldCartItem.setQty(qty);
		soldCartItem.setVisible(true);
		memberCartItemRepository.save(soldCartItem);
		LOG.info("\n\n\nSUCCESS: Purchase of {} successful\n\n",qty);
		return true;
		}
		return false;
	}

	@Override
	public List<MemberCartItem> findAllAvailableItems(Long productId) {
		
		return memberCartItemRepository.findByProductAndIsVisibleTrue(productId);
	}

	@Override
	public boolean toggleVisible(MemberCartItem memberCartItem) 
	{
		memberCartItem.setVisible(!memberCartItem.isVisible());
		memberCartItem.setCurrentPurchasePrice(memberCartItem.getProduct().getPriceBought());
		
		try 
		{
			memberCartItemRepository.save(memberCartItem);
			LOG.info("\n\n\nSUCCESS: Visibility switch concluded\n\n");
			return true;
		}
		catch(Exception e)
		{
			LOG.info("\n\n\nFAILURE: Failed to switch Visibility\n\n");
			return false;
		}
		
		
	}
	
	
	
	

}
