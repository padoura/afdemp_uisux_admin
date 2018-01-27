package org.afdemp.uisux.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.afdemp.uisux.domain.User;
import org.afdemp.uisux.domain.security.UserRole;
import org.afdemp.uisux.repository.UserRoleRepository;
import org.afdemp.uisux.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.stereotype.Service;

@Service
public class UserRoleServiceImpl implements UserRoleService{
	
	@Autowired
	private UserRoleRepository userRoleRepository;
	
	public boolean createUserRole(UserRole userRole)
	{
		try 
		{
			userRole=userRoleRepository.save(userRole);
			System.out.println("\nSUCCESS: Added UserRole to user "+userRole.getUser().getUsername()+".\n");
			return true;
		}
		catch (DataIntegrityViolationException e)
		{
			System.out.println("\nFAILURE:User " +userRole.getUser().getUsername()+ " already has this role.\n");
			return false;
		}
		catch (InvalidDataAccessApiUsageException e)
		{
			System.out.println("\nFAILURE:Illegal Object. (UserRole userRole is null)\n");
			return false;
		}	
	}
	
	@Override
	public List<User> findAllMembers() {
		// TODO this is just a simulation
		
		List<User> memberList = new ArrayList<User>();
		
		return memberList;
	}


}
