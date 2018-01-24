package org.afdemp.uisux.service;

import java.util.List;
import java.util.Set;

import org.afdemp.uisux.domain.Product;
import org.afdemp.uisux.domain.User;
import org.afdemp.uisux.domain.security.UserRole;

public interface ProductService {

	List<Product> findAll();
	
	Product findOne(Long id);
	
	void removeOne(Long id);

	Product createProduct(Product product, String type);
}
