package org.afdemp.uisux.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.afdemp.uisux.domain.security.PasswordResetToken;
import org.afdemp.uisux.repository.PasswordResetTokenRepository;

import org.afdemp.uisux.domain.User;
import org.afdemp.uisux.domain.security.Role;
import org.afdemp.uisux.domain.security.UserRole;
import org.afdemp.uisux.repository.RoleRepository;
import org.afdemp.uisux.repository.UserRepository;
import org.afdemp.uisux.service.UserService;
import org.afdemp.uisux.utility.SecurityUtility;

@Service
public class UserServiceImpl implements UserService {

	private static final Logger LOG = LoggerFactory.getLogger(UserService.class);
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private PasswordResetTokenRepository passwordResetTokenRepository;

	@Override
	public User createUser(User user, Set<UserRole> userRoles) {
		User localUser = userRepository.findByUsername(user.getUsername());

		if (localUser != null) {
			LOG.info("\n\nFAILURE: User {} already exists. Nothing will be done.\n\n", user.getUsername());
		} 
		else 
			{
			localUser = userRepository.findByEmail(user.getEmail());
			if (localUser != null) 
				{
					LOG.info("\n\nFAILURE: User with email:{} already exists. Nothing will be done.\n\n", user.getEmail());
				}
			
			else
				{
					for (UserRole ur : userRoles) 
					{
					roleRepository.save(ur.getRole());
					}
			
					user.getUserRoles().addAll(userRoles);
					
					localUser = userRepository.save(user);
					
					LOG.info("\n\nSUCCESS: User {} modified. Database succesfully updated.\n\n", user.getUsername());
			
				}
			}

		return localUser;
	}

	@Override
	public User save(User user) {
		return userRepository.save(user);
	}

	@Override
	public void createPasswordResetTokenForUser(final User user, final String token) {
		final PasswordResetToken myToken = new PasswordResetToken(token, user);
		passwordResetTokenRepository.save(myToken);
	}
	
	@Override
	public User findByEmail (String email) {
		return userRepository.findByEmail(email);
	}
	
	@Override
	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

}
