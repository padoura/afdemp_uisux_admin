package org.afdemp.uisux.service;

import java.util.List;

import org.afdemp.uisux.domain.Category;

public interface CategoryService {

	List<Category> findAll();
	
	Category findOne(Long id);
	
	void removeOne(Long id);
}
