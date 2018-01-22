package org.afdemp.uisux.repository;

import org.afdemp.uisux.domain.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
	User findByUsername(String username);
}
