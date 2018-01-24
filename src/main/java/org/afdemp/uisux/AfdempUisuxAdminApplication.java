package org.afdemp.uisux;

import java.util.HashSet;
import java.util.Set;

import org.afdemp.uisux.domain.Category;
import org.afdemp.uisux.domain.Product;
import org.afdemp.uisux.service.CategoryService;
import org.afdemp.uisux.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.afdemp.uisux.service.UserService;
import org.afdemp.uisux.domain.User;
import org.afdemp.uisux.domain.security.Role;
import org.afdemp.uisux.domain.security.UserRole;
import org.afdemp.uisux.repository.CategoryRepository;
import org.afdemp.uisux.utility.SecurityUtility;

@SpringBootApplication
public class AfdempUisuxAdminApplication implements CommandLineRunner{
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private UserService userService;

	public static void main(String[] args) {
		SpringApplication.run(AfdempUisuxAdminApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		insertFirstAdmin();
		insertSomeCategories();
		insertExampleProduct();
		duplicateCategoryViaProduct();
	}
	
	private void insertSomeCategories() {
		Category fruits = new Category("Fruits");
		categoryService.createCategory(fruits);
		Category vegetables = new Category("Vegetables");
		categoryService.createCategory(vegetables);
	}

	private void insertFirstAdmin() throws Exception {
		User user1 = new User();
		user1.setUsername("admin");
		user1.setPassword(SecurityUtility.passwordEncoder().encode("admin"));
		user1.setEmail("padoura21@gmail.com");
		Set<UserRole> userRoles = new HashSet<>();
		Role role1= new Role();
		role1.setRoleId(0);
		role1.setName("ROLE_ADMIN");
		userRoles.add(new UserRole(user1, role1));
		
		userService.createUser(user1, userRoles);
	}
	
	private void insertExampleProduct() throws Exception {
		Product product = new Product();
		product.setDescription("Awesome Choco Milk!");
		product.setInStockNumber(10L);
		product.setListPrice(1.50);
		product.setMadeIn("Keramia Crete");
		product.setName("Choco Milk 0.5L");
		product.setOurPrice(0.90);
		product.setPriceBought(0.30);
		
		String type = "Milk";
		productService.createProduct(product, type);
	}
	
	private void insertProductAndRemoveCategory() throws Exception {
		insertExampleProduct();
		categoryService.removeOne(1L);
	}
	
	private void duplicateCategoryViaProduct() {
		Product product = new Product();
		product.setDescription("Awesome Choco Milk!");
		product.setInStockNumber(10L);
		product.setListPrice(3);
		product.setMadeIn("Keramia Crete");
		product.setName("Choco Milk 1L");
		product.setOurPrice(1.80);
		product.setPriceBought(0.60);
		
		String type = "Milk";
		productService.createProduct(product, type);
	}
	
}
