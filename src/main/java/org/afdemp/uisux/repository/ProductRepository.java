package org.afdemp.uisux.repository;

import java.util.ArrayList;
import java.util.List;

import org.afdemp.uisux.domain.Category;
import org.afdemp.uisux.domain.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {
	Product findByName(String name);
	
	ArrayList<Product> findByNameContaining(String name);

	List<Product> findByCategory(Category category);

	List<Product> findByActiveTrue();

	List<Product> findByCategoryAndActiveTrue(Category category);

	ArrayList<Product> findByNameContainingAndActiveTrue(String name);
	
}
