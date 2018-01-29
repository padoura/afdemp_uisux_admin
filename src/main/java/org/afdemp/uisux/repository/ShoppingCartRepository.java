package org.afdemp.uisux.repository;

import org.afdemp.uisux.domain.ShoppingCart;
import org.afdemp.uisux.domain.security.UserRole;
import org.springframework.data.repository.CrudRepository;

public interface ShoppingCartRepository extends CrudRepository<ShoppingCart, Long> {
	
	ShoppingCart findByUserRole(UserRole userRole);

}
