package org.afdemp.uisux.service.impl;

import java.util.List;

import org.afdemp.uisux.domain.Category;
import org.afdemp.uisux.domain.Product;
import org.afdemp.uisux.repository.CategoryRepository;
import org.afdemp.uisux.service.CategoryService;
import org.afdemp.uisux.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {
	
	private static final Logger LOG = LoggerFactory.getLogger(CategoryService.class);
	
	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public List<Category> findAll() {
		return (List<Category>) categoryRepository.findAll();
	}

	@Override
	public Category findOne(Long id) {
		return categoryRepository.findOne(id);
	}

	@Override
	public void removeOne(Long id) {
		categoryRepository.delete(id);
	}

	@Override
	public Category save(Category category) {
		return categoryRepository.save(category);
	}

	@Override
	public Category createCategory(Category category) {
		Category localCategory = categoryRepository.findByType(category.getType());

		if (localCategory != null) {
			LOG.info("category {} already exists. Nothing will be done.", category.getType());
		} else {
			localCategory = categoryRepository.save(category);
		}

		return localCategory;
	}

}
