package org.afdemp.uisux.repository;

import java.util.ArrayList;

import org.afdemp.uisux.domain.User;
import org.afdemp.uisux.domain.security.Role;
import org.afdemp.uisux.domain.security.UserRole;
import org.springframework.data.repository.CrudRepository;

public interface UserRoleRepository extends CrudRepository<UserRole, Long> {

	public UserRole findByRoleAndUser(Role role, User user);
    
    public ArrayList<UserRole> findByRole(Role role);

	public UserRole findByUserAndRole(User user, Role findByName);
   	
	
}
