package org.afdemp.uisux.repository;

import org.springframework.data.repository.CrudRepository;

import org.afdemp.uisux.domain.security.Role;


public interface RoleRepository extends CrudRepository<Role, Long> {
	Role findByName(String name);
}
