package org.afdemp.uisux.service.impl;

import java.util.ArrayList;
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
	public Product createProduct(Product product, String type) 
	{
		    Category category = categoryRepository.findByType(type);
		 
			if (category == null) 
			{
			    category = new Category();
			    category.setType(type);
			    categoryRepository.save(category);
			}
			
			product.setCategory(category);
				
			Product tempProduct=productRepository.findByName(product.getName());
			if(tempProduct==null)
			{
				productRepository.save(product);
				LOG.info("\n\n\nSUCCESS: Added product {}.\n\n",product.getName());
				return product;
			}
			else
			{
				System.out.println("\n\n\nFAILURE:There's already an item with the same name.\n\n");
				return tempProduct;
			}
				
				
	}

	@Override
	public void toggleActive(Product product) {
		product.setActive(!product.isActive());
		productRepository.save(product);
	}

	@Override
	public void deactivate(Long id) {
		Product product = findOne(id);
		product.setActive(false);
		productRepository.save(product);
	}

	@Override
	public void activate(Long id) {
		Product product = findOne(id);
		product.setActive(true);
		productRepository.save(product);
	}
	
	@Override
	public ArrayList<Product> search(String name)
	{
		ArrayList<Product> searchResult=new ArrayList<Product>();
		searchResult=productRepository.findByNameContaining(name);
		return searchResult;
		
	}

	@Override
	public boolean restock(Long productId, int qty)
	{
		Product product=findOne(productId);
		product.setInStockNumber(product.getInStockNumber()+qty);
		if(productRepository.save(product)!=null)
		{
			LOG.info("\n\nSUCCESS: Restocked for product {} completed.\n",productId);
			return true;
		}
		LOG.info("\n\nFAILURE: Unable to restock product {}",productId);
		return false;
	}

	@Override
	public List<Product> findByCategory(String categoryType) {
		return productRepository.findByCategoryId(categoryRepository.findByType(categoryType).getId());
	}
		
}


