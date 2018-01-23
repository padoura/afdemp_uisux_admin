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
import org.afdemp.uisux.utility.SecurityUtility;

@SpringBootApplication
public class AfdempUisuxAdminApplication implements CommandLineRunner{
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private UserService userService;

	public static void main(String[] args) {
		SpringApplication.run(AfdempUisuxAdminApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		insertFirstAdmin();
		insertExampleProduct();
	}
	
	public void insertFirstAdmin() {
		User user1 = new User();
		user1.setUsername("admin");
		user1.setPassword(SecurityUtility.passwordEncoder().encode("admin"));
		user1.setEmail("padoura21@gmail.com");
		Set<UserRole> userRoles = new HashSet<>();
		Role role1= new Role();
		role1.setRoleId(0);
		role1.setName("ROLE_ADMIN");
		userRoles.add(new UserRole(user1, role1));
		
		try {
			userService.createUser(user1, userRoles);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void insertExampleProduct() {
		Product product = new Product();
		product.setDescription("Και γαμώ τα γάλατα!");
		product.setInStockNumber(10L);
		product.setListPrice(1.50);
		product.setMadeIn("Κίνα");
		product.setName("Γάλα με κακάο");
		product.setOurPrice(0.90);
		product.setPriceBought(0.30);
		
		Category category = new Category("Γάλα");
		
		product.setCategory(category);
		
		try {
			productService.createProduct(product);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void insertProductAndRemoveCategory() {
		insertExampleProduct();
		categoryService.removeOne(1L);
	}
	
}
