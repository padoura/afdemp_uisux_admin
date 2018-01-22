package org.afdemp.uisux.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name="id") 
public class UserPayment extends CreditCard {
	
	
	private boolean defaultPayment;
	
	@OneToOne(fetch=FetchType.EAGER)
	private UserBilling userBilling;
	
	public boolean isDefaultPayment() {
		return defaultPayment;
	}

	public void setDefaultPayment(boolean defaultPayment) {
		this.defaultPayment = defaultPayment;
	}
	
}
