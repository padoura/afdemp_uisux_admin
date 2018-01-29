package org.afdemp.uisux.service;

import java.util.List;

import org.afdemp.uisux.domain.User;
import org.afdemp.uisux.domain.security.UserRole;

public interface UserRoleService {

	boolean hasThisRole(String roleName, User user);

	List<User> fetchUsersOfRole(String roleName);
	
	boolean createUserRole(UserRole userRole);
	
}
