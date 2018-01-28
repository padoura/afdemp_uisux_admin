package org.afdemp.uisux.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.afdemp.uisux.domain.User;
import org.afdemp.uisux.domain.security.Role;
import org.afdemp.uisux.domain.security.UserRole;
import org.afdemp.uisux.repository.RoleRepository;
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
	
	@Autowired
	private RoleRepository roleRepository;
	
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
	
	
	@Override
    public boolean hasThisRole(String roleName, User user)
    {
        Role role=roleRepository.findByName(roleName);
        if(role==null)
        {
            System.out.println("\n\nNo Such Role Exists\n\n");
            return false;
        }
        UserRole userRole=userRoleRepository.findByRoleAndUser(role,user);
        if(userRole!=null)
        {
            return true;
        }
        return false;
        
        
        
    }
    
    @Override
    public List<User> fetchUsersOfRole(String roleName)
    {
        Role role=roleRepository.findByName(roleName);
        ArrayList<User> userList=new ArrayList<User>();
        ArrayList<UserRole> userRoleList=new ArrayList<UserRole>();
        if(role==null)
        {
            System.out.println("\n\nNo Such Role Exists\n\n");
            return userList;
            
        }
        userRoleList=userRoleRepository.findByRole(role);
        if(userRoleList!=null)
        {
            System.out.println();
            for(UserRole usr: userRoleList)
            {
                userList.add(usr.getUser());
                System.out.println(usr.getUser().toString());
            }
            System.out.println();
        }
        
        return userList;
    }


}
