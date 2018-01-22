package org.afdemp.uisux;

import org.afdemp.uisux.domain.Category;
import org.afdemp.uisux.domain.Product;
import org.afdemp.uisux.service.CategoryService;
import org.afdemp.uisux.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AfdempUisuxAdminApplication implements CommandLineRunner{
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private CategoryService categoryService;

	public static void main(String[] args) {
		SpringApplication.run(AfdempUisuxAdminApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		Product product = new Product();
		product.setDescription("Και γαμώ τα γάλατα!");
		product.setInStockNumber(10L);
		product.setListPrice(1.50);
		product.setMadeIn("Κίνα");
		product.setName("Γάλα με κακάο");
		product.setOurPrice(0.90);
		product.setPriceBought(0.30);
		
		productService.save(product);
		
		Category category = new Category("Γάλα");
		
		product.setCategory(category);
		
		productService.save(product);
		
		categoryService.removeOne(1L);
	}
	
}
