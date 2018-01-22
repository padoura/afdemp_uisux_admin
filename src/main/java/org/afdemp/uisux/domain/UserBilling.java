package org.afdemp.uisux.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name="id") 
public class UserBilling extends Address{
	
	@OneToOne(mappedBy="userBilling", cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	private UserPayment userPayment;

	public UserPayment getUserPayment() {
		return userPayment;
	}

	public void setUserPayment(UserPayment userPayment) {
		this.userPayment = userPayment;
	}
	
	
	
}
