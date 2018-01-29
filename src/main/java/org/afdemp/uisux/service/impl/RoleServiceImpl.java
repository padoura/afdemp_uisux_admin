package org.afdemp.uisux.service.impl;

import org.afdemp.uisux.domain.security.Role;
import org.afdemp.uisux.repository.RoleRepository;
import org.afdemp.uisux.service.RoleService;
import org.afdemp.uisux.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService{
	
	private static final Logger LOG = LoggerFactory.getLogger(UserService.class);
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Override
	public boolean createRole(Role role)
	{
		
		Role rl=roleRepository.findByName(role.getName());
		if(rl==null)
		{			
			role=roleRepository.save(role);
			LOG.info("\n\n\nSUCCESS: Added Role  {} \n\n",role.getName());
			return true;
		}
		else
		{
			LOG.info("\n\n\nFAILURE:Role {} already exists.\n\n", role.getName());
			return false;
		}
			
		
	}

}
