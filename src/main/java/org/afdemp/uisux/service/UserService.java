package org.afdemp.uisux.service;

import java.util.Set;

import org.afdemp.uisux.domain.User;
import org.afdemp.uisux.domain.security.UserRole;



public interface UserService {
	User createUser(User user, Set<UserRole> userRoles) throws Exception;
	
	User save(User user);
}
