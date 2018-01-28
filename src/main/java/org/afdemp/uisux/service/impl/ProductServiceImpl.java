package org.afdemp.uisux.service.impl;

import java.util.List;

import org.afdemp.uisux.domain.Category;
import org.afdemp.uisux.domain.Product;
import org.afdemp.uisux.repository.CategoryRepository;
import org.afdemp.uisux.repository.ProductRepository;
import org.afdemp.uisux.service.CategoryService;
import org.afdemp.uisux.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {
	
	private static final Logger LOG = LoggerFactory.getLogger(ProductService.class);
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public List<Product> findAll() {
		return (List<Product>) productRepository.findAll();
	}

	@Override
	public Product findOne(Long id) {
		return productRepository.findOne(id);
	}

	@Override
	public void removeOne(Long id) {
		productRepository.delete(id);
	}

	@Override
	public boolean createProduct(Product product, String type) 
	{
		
		
		 if(type==null)
		 {
			 System.out.println("\nFAILURE: String type parameter cannot be null.\n");
			 return false;
		 }
		 
		 if(product.getName()==null)
		 {
			 System.out.println("\nFAILURE: Product.name parameter cannot be null.\n");
			 return false;
		 }
		 
		 Category category = categoryRepository.findByType(type);
		 
			if (category == null) 
			{
			    category = new Category();
			    category.setType(type);
			    categoryRepository.save(category);
			}
			
			product.setCategory(category);
				
			try 
			{
				productRepository.save(product);
				System.out.println("\nSUCCESS: Added product "+product.getName()+".\n");
				return true;
			}
			catch (DataIntegrityViolationException e)
			{
				System.out.println("\nFAILURE:There's already an item with the same name.\n");
				return false;
			}
			catch (InvalidDataAccessApiUsageException e)
			{
				System.out.println("\nFAILURE:Illegal Object. (Product product is null or incomplete)\n");
				return false;
			}	
	}

		
}


