package org.afdemp.uisux.repository;

import org.springframework.data.repository.CrudRepository;

import org.afdemp.uisux.domain.User;

public interface UserRepository extends CrudRepository<User, Long> {
	User findByUsername(String username);
}
