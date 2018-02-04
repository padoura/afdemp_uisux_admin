package org.afdemp.uisux.utility;

import java.math.BigDecimal;

import org.afdemp.uisux.domain.MemberCartItem;
import org.afdemp.uisux.domain.User;
import org.springframework.stereotype.Component;

@Component
public class MemberCartItemWrapper {
	
	private BigDecimal currentPurchasePrice;
	
	private Long userId;
	
	private Long memberCartItemId;
	
	private String username;
	
	private int qty;

	public MemberCartItemWrapper(User user, MemberCartItem memberCartItem) {
		this.currentPurchasePrice = memberCartItem.getCurrentPurchasePrice();
		this.userId = user.getId();
		this.memberCartItemId = memberCartItem.getId();
		this.username = user.getUsername();
		this.qty = memberCartItem.getQty();
	}
	
	
}
