package org.afdemp.uisux.service;

import java.util.List;

import org.afdemp.uisux.domain.MemberCartItem;
import org.afdemp.uisux.domain.Product;
import org.afdemp.uisux.domain.ShoppingCart;

public interface MemberCartItemService {
	
	boolean emptyMemberCart(Long shoppingCartId);
	
	boolean removeMemberCartItem(Long id,Long shoppingCartId);
	
	MemberCartItem findById(Long memberCartItemId);
	
	boolean putUpForSale(Product product, int qty, ShoppingCart shoppingCart);
	
	boolean fullPurchaseFromMember(MemberCartItem memberCartItem);

	boolean partialPurchaseFromMember(MemberCartItem memberCartItem, int qty);
	
	List<MemberCartItem> findAllAvailableItems(Long productId);
	
	boolean toggleVisible(MemberCartItem memberCartItem);
	
	void activate(Long id);
	
	void deactivate(Long id);

	List<MemberCartItem> findAll();
	
}
