package org.afdemp.uisux.service.impl;

import java.util.List;

import org.afdemp.uisux.domain.Category;
import org.afdemp.uisux.repository.CategoryRepository;
import org.afdemp.uisux.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {
	
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

}
