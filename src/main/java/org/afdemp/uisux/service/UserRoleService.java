package org.afdemp.uisux.service;

import java.util.List;

import org.afdemp.uisux.domain.Address;
import org.afdemp.uisux.domain.CreditCard;
import org.afdemp.uisux.domain.User;
import org.afdemp.uisux.domain.security.UserRole;

public interface UserRoleService {

	boolean hasThisRole(String roleName, User user);

	List<User> fetchUsersOfRole(String roleName);
	
	boolean createUserRole(UserRole userRole);

	UserRole findByUserAndRole(User user, String roleType);

	void updateBillingAddress(Address billingAddress, CreditCard creditCard, UserRole userRole);

	void updateShippingAddress(Address shippingAddress, UserRole userRole);
	
}
