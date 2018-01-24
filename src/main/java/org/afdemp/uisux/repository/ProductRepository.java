package org.afdemp.uisux.repository;

import org.afdemp.uisux.domain.Category;
import org.afdemp.uisux.domain.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {
	Product findByName(String name);
	
}
