package org.afdemp.uisux.service;

import java.util.List;

import org.afdemp.uisux.domain.User;

public interface UserRoleService {

	
	List<User> findAllMembers();

	boolean hasThisRole(String roleName, User user);

	List<User> fetchUsersOfRole(String roleName);
}
