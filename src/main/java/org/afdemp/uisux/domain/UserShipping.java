package org.afdemp.uisux.domain;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name="id") 
public class UserShipping extends Address{
	
	
	private boolean userShippingDefault;
	
	

}
