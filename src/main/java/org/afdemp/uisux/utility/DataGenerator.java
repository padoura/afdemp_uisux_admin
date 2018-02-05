package org.afdemp.uisux.utility;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.afdemp.uisux.domain.Category;
import org.afdemp.uisux.domain.ClientOrder;
import org.afdemp.uisux.domain.MemberCartItem;
import org.afdemp.uisux.domain.Product;
import org.afdemp.uisux.domain.ShoppingCart;
import org.afdemp.uisux.domain.User;
import org.afdemp.uisux.domain.security.Role;
import org.afdemp.uisux.domain.security.UserRole;
import org.afdemp.uisux.repository.ClientOrderRepository;
import org.afdemp.uisux.repository.ProductRepository;
import org.afdemp.uisux.repository.RoleRepository;
import org.afdemp.uisux.repository.UserRepository;
import org.afdemp.uisux.repository.UserRoleRepository;
import org.afdemp.uisux.service.AccountService;
import org.afdemp.uisux.service.CartItemService;
import org.afdemp.uisux.service.CategoryService;
import org.afdemp.uisux.service.ClientOrderService;
import org.afdemp.uisux.service.MemberCartItemService;
import org.afdemp.uisux.service.ProductService;
import org.afdemp.uisux.service.ShoppingCartService;
import org.afdemp.uisux.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class DataGenerator {
	
	
	//===============================Autowire Section=================================
	
	@Autowired 
	private ClientOrderService clientOrderService;
	
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private CartItemService cartItemService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserRoleRepository userRoleRepository;
	
	@Autowired
	private ShoppingCartService shoppingCartService;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private ClientOrderRepository clientOrderRepository;

	@Autowired
	private MemberCartItemService memberCartItemService;
	
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
		product.setListPrice(BigDecimal.valueOf(1.50));
		product.setMadeIn("Keramia Crete");
		product.setName("Choco Milk 0.5L");
		product.setOurPrice(BigDecimal.valueOf(0.90));
		product.setPriceBought(BigDecimal.valueOf(0.30));
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
	
	private Product insertProductAndAddToCartExample() throws Exception {
		
		
		
		Product product = new Product();
		product.setDescription("Awesome Choco Milk!");
		product.setInStockNumber(10L);
		product.setListPrice(BigDecimal.valueOf(1.50));
		product.setMadeIn("Keramia Crete");
		product.setName("Choco Milk 1L");
		product.setOurPrice(BigDecimal.valueOf(0.90));
		product.setPriceBought(BigDecimal.valueOf(0.30));
		product.setActive(true);
		
		String type = "GTP Milk";
		product=productService.createProduct(product, type);
		
		User user=userRepository.findByUsername("Madryoch");
		Role role=roleRepository.findByName("ROLE_CLIENT");
		
		UserRole userRole=userRoleRepository.findByRoleAndUser(role, user);
				
		
		insertCartItem(userRole.getShoppingCart(), product, 5);
		
		concludeSale(userRole.getShoppingCart());
		
		ClientOrder clientOrder=new ClientOrder();
		clientOrder.setId(1L);
		clientOrderService.updateOrderStatusToShipped(clientOrder);
		
		clientOrderService.updateOrderStatusToDelivered(clientOrder);
		
		clientOrderService.updateShippingMethod(clientOrder, "Courier");
		clientOrderService.updateShippingDate(clientOrder, Date.valueOf(LocalDate.now().plusDays(5)));
		
		
		
		
		
		
		Timestamp from=Timestamp.valueOf(
				LocalDate.now().minusWeeks(1).atStartOfDay());
		Timestamp to=Timestamp.valueOf(
				LocalDate.now().atTime(23, 59, 59));
		
		List<ClientOrder> orders=clientOrderService.fetchOrdersByPeriod(from, to);
		
		System.out.println("\n\n\n");
		
		for (ClientOrder co: orders)
		{
			
			System.out.println(co.getId()+"\t"+co.getTotal());
			
		}
		
		
		
		System.out.println("\n\n\n");
		
		return product;
	}
	
	
	
	private boolean createMemberCartItemForTesting(int qty, String username)
	{
		Product product=productRepository.findByName("Choco Milk 1L");
		Role role=roleRepository.findByName("ROLE_MEMBER");
		User user=userRepository.findByUsername(username);
		UserRole userRole=userRoleRepository.findByRoleAndUser(role, user);
		if(memberCartItemService.putUpForSale(product, qty, userRole.getShoppingCart()))
		{
//			memberCartItemService.activate(1L);
			List<MemberCartItem> tempList=memberCartItemService.findAllAvailableItems(2L);
			for(MemberCartItem mci:tempList)
			{
				System.out.println("\n\n\n\n\n"+mci.getProduct().getName()+"\t"+mci.getProduct().getCategory().getType()+"\n\n\n\n\n");
			}
			
			return true;
		}
		return false;
	}
	
	private void makeAllMemberCartItemsVisible() {
		List<MemberCartItem> cartItemList = memberCartItemService.findAll();
		
		for (MemberCartItem ci : cartItemList) {
			memberCartItemService.activate(ci.getId());
		}
	}
	
	private boolean concludeSale(ShoppingCart shoppingCart)
	{
		HashSet<Product> itemsReturned=new HashSet<Product>();
		itemsReturned=cartItemService.commitSale(shoppingCart);
		System.out.println("\n\n");
		for(Product p:itemsReturned)
		{
			System.out.println(p.getName());
		}
		System.out.println("\n\n");
		
		
		if(itemsReturned.isEmpty())
		{
			System.out.println("\n\nClient Order successfully placed!\n\n");
			return true;
		}
		return false;
	}
	
	private void insertCartItem(ShoppingCart shoppingCart,Product product,int qty)
	{
		cartItemService.addToCart(shoppingCart,product,qty);
	}

	
	private void insertProductAndRemoveCategory() throws Exception {
		insertExampleProduct();
		categoryService.removeOne(1L);
	}
	
	private void duplicateCategoryViaProduct() {
		Product product = new Product();
		product.setDescription("Awesome Choco Milk!");
		product.setInStockNumber(10L);
		product.setListPrice(BigDecimal.valueOf(3));
		product.setMadeIn("Keramia Crete");
		product.setName("Choco Milk 1L");
		product.setOurPrice(BigDecimal.valueOf(1.80));
		product.setPriceBought(BigDecimal.valueOf(0.60));
		
		String type = "Milk";
		productService.createProduct(product, type);
		
		
	}
	
	private void insertExampleMember() throws Exception{
		Role role1= new Role();
		role1.setRoleId(1);
		role1.setName("ROLE_MEMBER");
		
		Role role2=new Role();
		role2.setRoleId(2);
		role2.setName("ROLE_CLIENT");
		
		// Member "member"
		User user1 = new User();
		user1.setUsername("member");
		user1.setPassword(SecurityUtility.passwordEncoder().encode("member"));
		user1.setEmail("member@fruitsynd.tk");
		Set<UserRole> userRoles = new HashSet<>();
		userRoles.add(new UserRole(user1, role1));
		
		userService.createUser(user1, userRoles);
		
		
		// Member-Client "madryoch"
		User user2=new User();
        user2.setUsername("madryoch");
        user2.setPassword(SecurityUtility.passwordEncoder().encode("madryoch"));
        user2.setEmail("madryoch@gmail.com");
        userRoles = new HashSet<>();
        userRoles.add(new UserRole(user2, role2));
        
        
        userService.createUser(user2, userRoles);
        userService.addRoleToExistingUser(user2,"ROLE_MEMBER");
        
        // Client "padoura"
        User user3=new User();
        user3.setUsername("padoura");
        user3.setPassword(SecurityUtility.passwordEncoder().encode("client"));
        user3.setEmail("padoura21@hotmail.com");
        userRoles = new HashSet<>();
        userRoles.add(new UserRole(user3, role2));
        
        
        userService.createUser(user3, userRoles);
        
	}
	
	@Transactional
	private boolean withdrawFromAdminDepositToMadryoch(BigDecimal amount)
	{
		UserRole userRole=userRoleRepository.findByRoleAndUser(roleRepository.findByName("ROLE_ADMIN"),userRepository.findByUsername("admin"));
		if(accountService.withdraw(userRole.getAccount(), amount))
		{
			UserRole tempUserRole=userRoleRepository.findByRoleAndUser(roleRepository.findByName("ROLE_MEMBER"),userRepository.findByUsername("madryoch"));
			accountService.deposit(tempUserRole.getAccount(), amount);
			return true;
		}
		return false;
	}
	
	//TODO use this example to insert sale data in DB when it's ready and make it private
	public static  List<ClientOrder> getFakeOrderList() 
	{
		List<ClientOrder> clientOrderList = new ArrayList<>();
		for (int i=0; i<35; i++) 
		{
				ClientOrder order = new ClientOrder();
				LocalDate localDate = LocalDate.now().minusDays(120-i);
				order.setId(Long.valueOf(i));
				order.setOrderStatus("delivered");
				order.setSubmittedDate(Date.valueOf(localDate));
				order.setTotal(randomBigDecimal());
				order.setShippingDate(Date.valueOf(localDate.plusDays(5)));
				order.setShippingMethod("Courier");
				clientOrderList.add(order);
			
		}
		
		return clientOrderList;
	}
	
	private static BigDecimal randomBigDecimal() {
		return BigDecimal.valueOf(Math.random()*10 + Math.random());
	}
	
	

	
	//===============================Generate Method=================================
	
	public void generate() throws Exception 
	{
	insertFirstAdmin();
	insertSomeCategories();
	insertExampleProduct();
	updateExampleProduct();
	insertExampleMember();
	withdrawFromAdminDepositToMadryoch(BigDecimal.valueOf(10000));
	insertProductAndAddToCartExample();
	createMemberCartItemForTesting(300, "Madryoch");
	createMemberCartItemForTesting(300, "member");
	makeAllMemberCartItemsVisible();
	
	}

}
