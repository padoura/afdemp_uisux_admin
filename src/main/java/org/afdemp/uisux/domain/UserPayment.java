package org.afdemp.uisux.domain;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

import org.afdemp.uisux.domain.security.UserRole;

@Entity
@PrimaryKeyJoinColumn(name="id") 
public class UserPayment extends CreditCard {
	
	private boolean defaultPayment;
	
	@OneToOne(fetch=FetchType.EAGER)
	private Address billingAddress;
	
	@ManyToOne
	@JoinColumn(name="user_role_id")
	private UserRole userRole;

	public Address getBillingAddress() {
		return billingAddress;
	}

	public void setBillingAddress(Address billingAddress) {
		this.billingAddress = billingAddress;
	}

	public UserRole getUserRole() {
		return userRole;
	}

	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}

	public boolean isDefaultPayment() {
		return defaultPayment;
	}

	public void setDefaultPayment(boolean defaultPayment) {
		this.defaultPayment = defaultPayment;
	}
	
}
