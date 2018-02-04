package org.afdemp.uisux.utility;

import java.math.BigDecimal;

import org.afdemp.uisux.domain.MemberCartItem;
import org.afdemp.uisux.domain.User;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

public class MemberCartItemWrapper {
	
	private BigDecimal currentPurchasePrice;
	
	private Long memberCartItemId;
	
	private String username;
	
	private String firstName;
	private String lastName;
	
	private int qty;

	
	public MemberCartItemWrapper(User user, MemberCartItem memberCartItem) {
		this.currentPurchasePrice = memberCartItem.getCurrentPurchasePrice();
		this.memberCartItemId = memberCartItem.getId();
		this.username = user.getUsername();
		this.qty = memberCartItem.getQty();
		this.firstName = user.getFirstName();
		this.lastName = user.getLastName();
	}
	
	

	public String getFirstName() {
		return firstName;
	}



	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}



	public String getLastName() {
		return lastName;
	}



	public void setLastName(String lastName) {
		this.lastName = lastName;
	}



	public BigDecimal getCurrentPurchasePrice() {
		return currentPurchasePrice;
	}

	public void setCurrentPurchasePrice(BigDecimal currentPurchasePrice) {
		this.currentPurchasePrice = currentPurchasePrice;
	}

	public Long getMemberCartItemId() {
		return memberCartItemId;
	}

	public void setMemberCartItemId(Long memberCartItemId) {
		this.memberCartItemId = memberCartItemId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}
	
	
}
