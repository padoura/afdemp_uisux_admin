package org.afdemp.uisux.service;

import java.util.List;
import java.util.Set;

import org.afdemp.uisux.domain.Address;
import org.afdemp.uisux.domain.CreditCard;
import org.afdemp.uisux.domain.User;
import org.afdemp.uisux.domain.security.PasswordResetToken;
import org.afdemp.uisux.domain.security.UserRole;



public interface UserService {
	User createUser(User user, Set<UserRole> userRoles) throws Exception;
	
	User save(User user);
	
	User addRoleToExistingUser(User user,String rolename);
	
	//Non-Functional
	void updateUserThroughUsername(User user);
	//Non-Functional
	void updateUserThroughEmail(String email, User user);
	

	void createPasswordResetTokenForUser(final User user, final String token);
	
	User findByUsername(String username);
	
	User findByEmail (String email);

	User findOne(Long id);

	PasswordResetToken getPasswordResetToken(final String token);
}
