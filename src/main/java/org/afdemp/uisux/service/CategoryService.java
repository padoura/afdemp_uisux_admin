package org.afdemp.uisux.service;

import java.util.List;

import org.afdemp.uisux.domain.Category;
import org.afdemp.uisux.domain.Product;

public interface CategoryService {
	
	Category save(Category category);

	List<Category> findAll();
	
	Category findOne(Long id);
	
	void removeOne(Long id);

	Category createCategory(Category category);

	
}
