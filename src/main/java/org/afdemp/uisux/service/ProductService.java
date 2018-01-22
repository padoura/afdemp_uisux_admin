package org.afdemp.uisux.service;

import java.util.List;

import org.afdemp.uisux.domain.Product;

public interface ProductService {
	
	Product save(Product product);

	List<Product> findAll();
	
	Product findOne(Long id);
	
	void removeOne(Long id);
}
