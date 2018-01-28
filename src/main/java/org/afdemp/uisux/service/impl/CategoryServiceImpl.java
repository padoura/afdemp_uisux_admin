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
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
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
		try 
		{
			category=categoryRepository.save(category);
			System.out.println("\nSUCCESS: Added category "+category.getType()+".\n");
			return category;
		}
		catch (DataIntegrityViolationException e)
		{
			System.out.println("\nFAILURE:There's already a category of the same type.\n");
			return category;
		}
		catch (InvalidDataAccessApiUsageException e)
		{
			System.out.println("\nFAILURE:Illegal Object. (Category category is null)\n");
			return null;
		}	
		
		
		
	}

}
