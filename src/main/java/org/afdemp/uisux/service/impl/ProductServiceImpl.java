package org.afdemp.uisux.service.impl;

import java.util.List;

import org.afdemp.uisux.domain.Product;
import org.afdemp.uisux.repository.ProductRepository;
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

	@Override
	public Product save(Product product) {
		return productRepository.save(product);
	}

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
	public Product createProduct(Product product) {
		Product localProduct = productRepository.findByName(product.getName());

		if (localProduct != null) {
			LOG.info("product {} already exists. Nothing will be done.", product.getName());
		} else {
			localProduct = productRepository.save(product);
		}

		return localProduct;
	}

}
