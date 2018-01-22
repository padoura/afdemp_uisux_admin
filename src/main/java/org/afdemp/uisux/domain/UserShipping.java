package org.afdemp.uisux.domain;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;

import org.afdemp.uisux.domain.security.UserRole;

@Entity
@PrimaryKeyJoinColumn(name="id") 
public class UserShipping extends Address{
	
	
	private boolean userShippingDefault;
	
	@ManyToOne
	@JoinColumn(name="user_role_id")
	private UserRole userRole;

	public UserRole getUserRole() {
		return userRole;
	}

	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}

	public boolean isUserShippingDefault() {
		return userShippingDefault;
	}

	public void setUserShippingDefault(boolean userShippingDefault) {
		this.userShippingDefault = userShippingDefault;
	}
	
	

}
