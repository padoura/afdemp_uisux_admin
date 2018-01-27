package org.afdemp.uisux.repository;

import org.afdemp.uisux.domain.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Long> {

	Category findByType(String type);
	
	
	
}
