package org.afdemp.uisux.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.afdemp.uisux.domain.User;
import org.afdemp.uisux.domain.security.Role;
import org.afdemp.uisux.domain.security.UserRole;
import org.afdemp.uisux.repository.RoleRepository;
import org.afdemp.uisux.repository.UserRoleRepository;
import org.afdemp.uisux.service.UserRoleService;
import org.afdemp.uisux.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.stereotype.Service;

@Service
public class UserRoleServiceImpl implements UserRoleService{
	
	private static final Logger LOG = LoggerFactory.getLogger(UserRoleService.class);
	
	@Autowired
	private UserRoleRepository userRoleRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Override
	public boolean createUserRole(UserRole userRole)
	{
		
		UserRole ur=userRoleRepository.findByRoleAndUser(userRole.getRole(), userRole.getUser());
		if(ur==null && userRole.getUser()!=null && userRole.getRole()!=null)
		{			
			userRole=userRoleRepository.save(userRole);
			LOG.info("\n\\nSUCCESS: Added UserRole to user {} \n\n", userRole.getUser().getUsername());
			return true;
		}
		else
		{
			LOG.info("\n\nFAILURE:User {} already has this role.\n\n", userRole.getUser().getUsername());
			return false;
		}
			
		
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
