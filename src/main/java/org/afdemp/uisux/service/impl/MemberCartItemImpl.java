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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean putUpForSale(Product product, int qty, ShoppingCart shoppingCart) {
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean toggleVisible(MemberCartItem memberCartItem) {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	
	

}
