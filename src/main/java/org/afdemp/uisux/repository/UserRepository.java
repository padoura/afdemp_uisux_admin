package org.afdemp.uisux.repository;

import java.util.Set;

import org.afdemp.uisux.domain.User;
import org.afdemp.uisux.domain.security.UserRole;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface UserRepository extends CrudRepository<User, Long> {
	
	User findByUsername(String username);
	
	User findByEmail(String email);
	
	@Transactional
	@Modifying
	@Query("UPDATE User u SET u.firstName =:firstName,u.lastName=:lastName, u.phone=:phone, u.enabled=:status, u.userRoles=:userRoles  WHERE u.username = :user")
	void update(@Param("firstName") String firstName,@Param("lastName") String lastName,@Param("phone") String phone,@Param("status") boolean status,@Param("userRoles") Set<UserRole> userRoles, @Param("user") String username);
}
