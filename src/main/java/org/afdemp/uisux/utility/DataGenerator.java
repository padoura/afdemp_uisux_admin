package org.afdemp.uisux.utility;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import org.afdemp.uisux.domain.CartItem;
import org.afdemp.uisux.domain.Category;
import org.afdemp.uisux.domain.Product;
import org.afdemp.uisux.domain.ShoppingCart;
import org.afdemp.uisux.domain.User;
import org.afdemp.uisux.domain.security.Role;
import org.afdemp.uisux.domain.security.UserRole;
import org.afdemp.uisux.service.CartItemService;
import org.afdemp.uisux.service.CategoryService;
import org.afdemp.uisux.service.ProductService;
import org.afdemp.uisux.service.ShoppingCartService;
import org.afdemp.uisux.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DataGenerator {
	
	
	//===============================Autowire Section=================================
	
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private CartItemService cartItemService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ShoppingCartService shoppingCartService;

	
	
	//===============================Function Definitions=================================
	
	
	private void insertSomeCategories() {
		Category fruits = new Category("Fruits");
		categoryService.createCategory(fruits);
		Category vegetables = new Category("Vegetables");
		categoryService.createCategory(vegetables);
		Category vegetable = new Category("Vegetables");
		categoryService.createCategory(vegetable);
	}

	private void insertFirstAdmin() throws Exception {
		User user1 = new User();
		user1.setUsername("admin");
		user1.setPassword(SecurityUtility.passwordEncoder().encode("admin"));
		user1.setEmail("whatever@xxx.tv");
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
		product.setActive(true);
		
		String type = "Milk";
		productService.createProduct(product, type);
	}
	
	private void updateExampleProduct() throws Exception {
		Product product = productService.findOne(1L);
		product.setMadeIn("China");
		String type = "GTP Milk";
		productService.createProduct(product, type);
	}
	
	private ShoppingCart makeShoppingCart()
	{
		ShoppingCart shoppingCart=new ShoppingCart();
		UserRole userRole=new UserRole();
		shoppingCart.setUserRole(userRole);
		shoppingCartService.createShoppingCart(shoppingCart);
		return shoppingCart;
	}
	
	private void insertExampleProduct2() throws Exception {
		Product product = new Product();
		product.setDescription("Awesome Choco Milk!");
		product.setInStockNumber(10L);
		product.setListPrice(1.50);
		product.setMadeIn("Keramia Crete");
		product.setName("Choco Milk 1L");
		product.setOurPrice(0.90);
		product.setPriceBought(0.30);
		product.setActive(true);
		
		String type = "GTP Milk";
		productService.createProduct(product, type);
		
		ShoppingCart shoppingCart=makeShoppingCart();
		
		insertCartItem(product, 10, shoppingCart);
	}
	
	private void insertCartItem(Product product,int qty, ShoppingCart shoppingCart)
	{
		CartItem cartItem=new CartItem();
		cartItem.setQty(qty);
		cartItem.setSubtotal(BigDecimal.valueOf(product.getOurPrice()*cartItem.getQty()));
		cartItem.setProduct(product);
		cartItem.setShoppingCart(shoppingCart);
		
		
		
		cartItemService.createCartItem(cartItem);
		
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
	
	private void insertExampleMember() throws Exception{
		Role role1= new Role();
		role1.setRoleId(2);
		role1.setName("ROLE_MEMBER");
		
		User user1 = new User();
		user1.setUsername("member");
		user1.setPassword(SecurityUtility.passwordEncoder().encode("member"));
		user1.setEmail("member@fruitsynd.tk");
		Set<UserRole> userRoles = new HashSet<>();
		userRoles.add(new UserRole(user1, role1));
		
		userService.createUser(user1, userRoles);
		
		User user2=new User();
        user2.setUsername("madryoch");
        user2.setPassword(SecurityUtility.passwordEncoder().encode("guest2"));
        user2.setEmail("madryoch@gmail.com");
        userRoles = new HashSet<>();
        userRoles.add(new UserRole(user2, role1));
        
        userService.createUser(user2, userRoles);
	}

	
	//===============================Generate Method=================================
	
	public void generate() throws Exception 
	{
	insertFirstAdmin();
	insertSomeCategories();
	insertExampleProduct();
	updateExampleProduct();
	insertExampleMember();
	//insertExampleProduct2();
	}

}
