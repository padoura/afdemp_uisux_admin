package org.afdemp.uisux.utility;


import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.afdemp.uisux.domain.Address;
import org.afdemp.uisux.domain.Category;
import org.afdemp.uisux.domain.ClientOrder;
import org.afdemp.uisux.domain.CreditCard;
import org.afdemp.uisux.domain.MemberCartItem;
import org.afdemp.uisux.domain.Product;
import org.afdemp.uisux.domain.User;
import org.afdemp.uisux.domain.security.Role;
import org.afdemp.uisux.domain.security.UserRole;
import org.afdemp.uisux.repository.CategoryRepository;
import org.afdemp.uisux.repository.RoleRepository;
import org.afdemp.uisux.repository.UserRoleRepository;
import org.afdemp.uisux.service.AddressService;
import org.afdemp.uisux.service.CartItemService;
import org.afdemp.uisux.service.CategoryService;
import org.afdemp.uisux.service.ClientOrderService;
import org.afdemp.uisux.service.CreditCardService;
import org.afdemp.uisux.service.MemberCartItemService;
import org.afdemp.uisux.service.ProductService;
import org.afdemp.uisux.service.RoleService;
import org.afdemp.uisux.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DataGenerator 
{
	
	
	//================================ Autowired Repositories ================================
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private UserRoleRepository userRoleRepository;
	
	//=================================  Autowired Services  =================================
	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private MemberCartItemService memberCartItemService;
	
	@Autowired
	private CartItemService cartItemService;
	
	@Autowired
	private AddressService addressService;
	
	@Autowired
	private CreditCardService creditCardService;
	
	@Autowired
	private ClientOrderService clientOrderService;
	
	//=========================== Functions Declarations ============================
	
	private void createRoles()
	{
		Role roleAdmin= new Role();
		roleAdmin.setRoleId(0);
		roleAdmin.setName("ROLE_ADMIN");
		roleService.createRole(roleAdmin);
		
		Role roleMember= new Role();
		roleMember.setRoleId(1);
		roleMember.setName("ROLE_MEMBER");
		roleService.createRole(roleMember);
		
		Role roleClient= new Role();
		roleClient.setRoleId(2);
		roleClient.setName("ROLE_CLIENT");
		roleService.createRole(roleClient);
	}
	
	private void createAdmin() throws Exception
	{
		User admin = new User();
		admin.setUsername("admin");
		admin.setPassword(SecurityUtility.passwordEncoder().encode("admin"));
		admin.setEmail("whatever@xxx.tv");
		Set<UserRole> userRoles = new HashSet<>();
		Role adminRole=roleRepository.findByName("ROLE_ADMIN");
		userRoles.add(new UserRole(admin, adminRole));
		
		userService.createUser(admin, userRoles);
	}
	
	private void createMember(String username, String firstName, String lastName, String phone, String email) throws Exception
	{
		Role memberRole=roleRepository.findByName("ROLE_MEMBER");
		
		User member = new User();
		member.setUsername(username);
		member.setFirstName(firstName);
		member.setLastName(lastName);
		member.setPhone(phone);
		member.setPassword(SecurityUtility.passwordEncoder().encode("member"));
		member.setEmail(email);
		Set<UserRole> userRoles = new HashSet<>();
		userRoles.add(new UserRole(member, memberRole));
		
		userService.createUser(member, userRoles);
		userService.addRoleToExistingUser(member, "ROLE_CLIENT");
	}
	
	private void createClient(String username, String firstName, String lastName, String phone, String email) throws Exception
	{
		Role clientRole=roleRepository.findByName("ROLE_CLIENT");
		
		User client = new User();
		client.setUsername(username);
		client.setFirstName(firstName);
		client.setLastName(lastName);
		client.setPhone(phone);
		client.setPassword(SecurityUtility.passwordEncoder().encode("client"));
		client.setEmail(email);
		Set<UserRole> userRoles = new HashSet<>();
		userRoles.add(new UserRole(client, clientRole));
		
		userService.createUser(client, userRoles);
	}
	
	private void createProductCategory(String type)
	{
		Category category=new Category();
		category.setType(type);
		categoryService.createCategory(category);
	}
	
	private void createXProduct(String name,String categoryType,String madeIn,String description,BigDecimal listPrice,BigDecimal ourPrice,BigDecimal priceBought)
	{
		Product product=new Product();
		product.setName(name);		
		product.setMadeIn(madeIn);
		product.setInStockNumber(1000L);
		product.setListPrice(listPrice);
		product.setOurPrice(ourPrice);
		product.setActive(true);
		product.setPriceBought(priceBought);
		product.setDescription(description);
		productService.createProduct(product, categoryType);
		
	}
	
	private Timestamp getRandomTime(long startingDate,int rangeMult100)
	{
		Timestamp seed = new Timestamp(new Date().getTime());
		Random rand=new Random(seed.getTime());
		long randomLong=Long.valueOf(rand.nextInt(rangeMult100))*100L +startingDate;
		return new Timestamp(randomLong);
	}
	
	private Timestamp getRandomTimeFrom112017()
	{
		return getRandomTime(1483221600000L,342144000 );
	}
	
	private Timestamp get3to7DaysLater(Timestamp datetime)
	{
		Random rand=new Random();
		long datenanos=datetime.getTime()+(rand.nextInt(3)+4)*86400000;
		return new Timestamp(datenanos);
	}
	
	private List<UserRole> getMemberRoles()
	{
		return userRoleRepository.findByRole(roleRepository.findByName("ROLE_MEMBER"));
	}
	
	private List<UserRole> getClientRoles()
	{
		return userRoleRepository.findByRole(roleRepository.findByName("ROLE_CLIENT"));
	}
	
	private List<Category> getAllCategories()
	{
		return categoryService.findAll();
	}
	
	private List<Product> getAllProducts()
	{
		return productService.findAllActive();
	}
	
	private boolean createMemberProductsForMember(UserRole userRole)
	{
		List<Category> categories=getAllCategories();
		if(!categories.isEmpty())
		{
			int categoryCount=categories.size();
			Random rand=new Random();
			int numberOfCategoriesServed=rand.nextInt(1)+2;
			Category cat[]=categories.toArray(new Category[categories.size()]);
			for(int i=0;i<numberOfCategoriesServed;i++)
			{
				int categoryRandomIterator=rand.nextInt(categoryCount-1);
				List<Product> categoryProducts=productService.findByCategoryAndActiveTrue(cat[categoryRandomIterator]);
				for(Product p:categoryProducts)
				{
					int randomQ=rand.nextInt(500)+500;
					MemberCartItem mci=memberCartItemService.putUpForSale(p, randomQ, userRole.getShoppingCart());
					memberCartItemService.activate(mci.getId());
				}
			}
			return true;
		}
				
		return false;
	}
	
	private void generateSellers()
	{
		for(UserRole ur:getMemberRoles())
		{
			createMemberProductsForMember(ur);
		}
	}
	
	private void generateClientCartItemsAndSale(UserRole userRole,CreditCard creditCard,Address address)
	{
		Random rand=new Random();
		List<Product> productsList=productService.findAllActive();
		int productCount=productsList.size();
		
		Product products[]=productsList.toArray(new Product[productsList.size()]);
		int noOfProducts=rand.nextInt(4)+1;
		
		for(int i=0;i<noOfProducts;i++)
		{
			int productArrayIndex=rand.nextInt(products.length-1);
			int qty=rand.nextInt(4)+1;
			cartItemService.addToCart(userRole.getShoppingCart(), products[productArrayIndex], qty);
		}
		
		
		//Timestamp.valueOf(LocalDateTime.now()).getTime();
		
		
		ClientOrder clientOrder=cartItemService.commitPastSale(userRole.getShoppingCart(), creditCard, address, address, "High like myself atm", getRandomTimeFrom112017());
		clientOrderService.distributePastEarningsToAllMembers(clientOrder.getId());
	}
	
	private void generateRecentClientCartItemsAndSale(UserRole userRole,CreditCard creditCard,Address address)
	{
		Random rand=new Random();
		List<Product> productsList=productService.findAllActive();
		int productCount=productsList.size();
		
		Product products[]=productsList.toArray(new Product[productsList.size()]);
		int noOfProducts=rand.nextInt(4)+1;
		
		for(int i=0;i<noOfProducts;i++)
		{
			int productArrayIndex=rand.nextInt(products.length-1);
			int qty=rand.nextInt(4)+1;
			cartItemService.addToCart(userRole.getShoppingCart(), products[productArrayIndex], qty);
		}
		
		
		//Timestamp.valueOf(LocalDateTime.now()).getTime();
		
		
		ClientOrder clientOrder=cartItemService.commitPastSale(userRole.getShoppingCart(), creditCard, address, address, "High like myself atm", new Timestamp(1517436000000L));
		
	}
	
	
	
	
	private void generatePastSales(int salesNumber)
	{
		List<UserRole> clientsList=getClientRoles();
		
		Address address=new Address();
		address.setCity("7th Heaven");
		address.setCountry("Neverland");
		address.setReceiverName("Test Subject 417");
		address.setState("Above The Rainbow");
		address.setStreet1("5th Cloud from the left");
		address.setZipcode("777");
		address.setUserRole(userRoleRepository.findFirstByRole(roleRepository.findByName("ROLE_ADMIN")));
		address=addressService.createAddress(address);
		
		CreditCard creditCard=new CreditCard();
		creditCard.setBillingAddress(address);
		creditCard.setCardNumber("4000400040004000");
		creditCard.setCvc(444);
		creditCard.setExpiryMonth(13);
		creditCard.setExpiryYear(2222);
		creditCard.setHolderName("IsThisGod?");
		creditCard.setType("OneOfAKind");
		creditCard.setUserRole(address.getUserRole());
		creditCard=creditCardService.createCreditCard(creditCard);
		
		Random rand=new Random();
		UserRole clients[]=clientsList.toArray(new UserRole[clientsList.size()]);
		for(int i=0;i<salesNumber;i++)
		{
			int clientsRandomIndex=rand.nextInt(clients.length-1);
			generateClientCartItemsAndSale(clients[clientsRandomIndex],creditCard, address);
		}
	}
	
	private void generateRecentSales(int salesNumber)
	{
		List<UserRole> clientsList=getClientRoles();
		
		Address address=new Address();
		address.setCity("7th Heaven");
		address.setCountry("Neverland");
		address.setReceiverName("Test Subject 417");
		address.setState("Above The Rainbow");
		address.setStreet1("5th Cloud from the left");
		address.setZipcode("777");
		address.setUserRole(userRoleRepository.findFirstByRole(roleRepository.findByName("ROLE_ADMIN")));
		address=addressService.createAddress(address);
		
		CreditCard creditCard=new CreditCard();
		creditCard.setBillingAddress(address);
		creditCard.setCardNumber("4000400040004000");
		creditCard.setCvc(444);
		creditCard.setExpiryMonth(13);
		creditCard.setExpiryYear(2222);
		creditCard.setHolderName("IsThisGod?");
		creditCard.setType("OneOfAKind");
		creditCard.setUserRole(address.getUserRole());
		creditCard=creditCardService.createCreditCard(creditCard);
		
		Random rand=new Random();
		UserRole clients[]=clientsList.toArray(new UserRole[clientsList.size()]);
		for(int i=0;i<salesNumber;i++)
		{
			int clientsRandomIndex=rand.nextInt(clients.length-1);
			generateRecentClientCartItemsAndSale(clients[clientsRandomIndex],creditCard, address);
		}
	}
	
	
	//TestFunction
	private void printAllMemberUserRoles()
	{
		for(UserRole ur:getMemberRoles())
		{
			System.out.println(ur.getUserRoleId()+"\t"+ur.getUser()+"\t"+ur.getRole());
		}
	}
	
	//===========================  Hardcoded Generators  ============================
	
	
	
	
	
	
	private void createInitialUsers() throws Exception
	{
		//----Admin----
		createAdmin();
		
		//----Members----
		createMember("padoura","Μιχάλης","Παντουράκης","2119122391","padoura21@hotmail.com");
		createMember("lefteris","Λευτέρης","Μαλινδρέτος","2107348421","emalindretos@coopmail.gr");
		createMember("madryoch","Γιώργος","Αθανασόπουλος","2109246187","madryoch@gmail.com");
		createMember("panos123","Παναγιώτης","Οικονόμου","2108761121","panos123@coopmail.gr");
		createMember("kirmanolisopsaras","Δημήτρης","Τσιγουρής","2109361725","kirmanolisopsaras@coopmail.gr");
		createMember("litsa87","Ευαγγελία","Ραντή","2108416912","litsara@coopmail.gr");
		createMember("maria","Μαρία","Τσολακίδη","2105521791","maria@coopmail.gr");
		
		//----Clients----
		createClient("alexandros","Αλέξανδρος","Κούρτης","2108733490","akourtis@riggedmail.gr");
		createClient("alekarios","Αλέξανδρος","Θεοφίλης","2107543281","alekarios@riggedmail.gr");
		createClient("theofylaktos","Γιώργος","Θεοφύλακτος","2139522735","theofylaktos@riggedmail.gr");
		createClient("kostas13","Κωνσταντίνος","Σταθόπουλος","2109822761","telemax@riggedmail.gr");
		createClient("agios90","Άγγελος","Αγιωργήτης","2105765665","agios90@riggedmail.gr");
		createClient("chrisdal","Χρήστος","Δαλαμήτρας","2119311479","chrisdal@riggedmail.gr");
		createClient("dimko","Δημήτρης","Κορμαζόγλου","2109717662","dimko@riggedmail.gr");
		createClient("rozisvasilis","Βασίλης","Ροζής","2103436772","rozisv@riggedmail.gr");
		createClient("tsaki67","Γιώργος","Τσακίρης","2104241499","tsakichan@riggedmail.gr");
		createClient("vaso_ll","Βάσω","Λιλιτάκη","2108623482","vaso_ll@riggedmail.gr");
		createClient("aspmin","Άσπα","Μιναϊδου","2108523541","aspmin@riggedmail.gr");
		createClient("fountoukidou","Ιωάννα","Φουντουκίδου","","foudoukidou@riggedmail.gr");
		createClient("AlxT","Αλέξανδρος","Τσότος","2107211987","alxt@riggedmail.gr");
		createClient("agisilaos90","Αγησίλαος","Γεωργούλιας","2133221957","agisilaos90@riggedmail.gr");
		createClient("kon.prodromou","Κωνσταντίνος","Προδρόμου","2139982770","kon.prodromou@riggedmail.gr");
		createClient("dim.orest04","Ορέστης","Δημητριάδης","2108761231","dim.orest04@riggedmail.gr");
		createClient("gregoryP","Γρηγόρης","Παρασκευάκος","2108123115","paraskevakos@riggedmail.gr");
		createClient("phaedonkouts","Φαίδων","Κουτσογιαννόπουλος","2108781231","phaedonkouts@riggedmail.gr");
	}
	
	
	private void createInitialProducts()
	{
		createXProduct("Αγελαδινό Γάλα 1L","Γάλα","Κρήτη","Πλήρες φρέσκο αγελαδινό γάλα, παστεριωμένο και επιμελώς συσκευασμένο σε συσκευασία του 1L για να διατηρεί την φρεσκάδα του και τα θρεπτικά συστατικά που χρειάζεται καθένας μας για ενα πλήρες πρωινό. Από αγελάδες που τρέφονται με τριφύλλι,καλαμπόκι,κριθάρι και άλλες φυτικές ίνες. Διάρκεια Ζωής 7 ημέρες.",BigDecimal.valueOf(1.98),BigDecimal.valueOf(1.83),BigDecimal.valueOf(0.61));
		createXProduct("Άπαχο Αγελαδινό Γάλα 1L","Γάλα","Κρήτη","Φρέσκο άπαχο αγελαδινό γάλα, παστεριωμένο ιδανικό για αυτούς και αυτές που προσέχουν την σιλουέτα τους. Διάρκεια Ζωής 7 ημέρες.",BigDecimal.valueOf(2.07),BigDecimal.valueOf(1.89),BigDecimal.valueOf(0.63));
		createXProduct("Σοκολατούχο Αγελαδινό Γάλα 0.5L","Γάλα","Κρήτη","Πλήρες σοκολατούχο αγελαδινό γάλα, υψηλής παστερίωσης με χρήση εκχυλισμάτων από το φυτό στέβια για γευστική απόλαυση χωρίς ενοχές.",BigDecimal.valueOf(1.25),BigDecimal.valueOf(1.14),BigDecimal.valueOf(0.38));
		createXProduct("Κατσικίσιο Γάλα 1L","Γάλα","Κρήτη","Αγνό πλήρες κατσικίσιο γάλα, παστεριωμένο και συσκευασμένο σε συσκευασία του 1L. Απευθείας από τους παραγωγούς μας με στόχο πάντα την ποιότητα. Διάρκεια Ζωής 14 ημέρες.",BigDecimal.valueOf(2.35),BigDecimal.valueOf(2.19),BigDecimal.valueOf(0.73));
		
		createXProduct("Φέτα ΠΟΠ 1kg","Τυρί","Κρήτη","Τυρί φέτα απευθείας από τους παραγωγούς της περιοχής μας. Εξαιρετική γεύση και ποιότητα.",BigDecimal.valueOf(10.50),BigDecimal.valueOf(8.97),BigDecimal.valueOf(2.72));
		createXProduct("Κατσικίσια Φέτα ΠΟΠ 0.5kg","Τυρί","Κρήτη","Τυρί φέτα από 100% αγνό κατσικίσιο γάλα που μόνο οι παραγωγοί της περιοχής μας ξέρουν να φτιάχνουν με μεράκι περισσό.",BigDecimal.valueOf(11.47),BigDecimal.valueOf(9.21),BigDecimal.valueOf(3.05));
		createXProduct("Ανθότυρο 0.5kg","Τυρί","Κρήτη","Ανθοτυρό που λιώνει στο στόμα κατάλληλο να συνοδέψει πολλά φαγητά ή να δώσει έξτρα γεύση στις σπιτικές σας σπανακό/τυρόπιτες ",BigDecimal.valueOf(1.94),BigDecimal.valueOf(1.53),BigDecimal.valueOf(0.51));
		createXProduct("Γλυκιά Γραβιέρα Κρήτης ΠΟΠ 1kg","Τυρί","Κρήτη","Η γλυκιά γραβιέρα μας που έχει γνωρίσει επιτυχία και εκτός συνόρων τώρα στο πιάτο σας σε τιμή πειρασμό.",BigDecimal.valueOf(9.64),BigDecimal.valueOf(9.01),BigDecimal.valueOf(2.95));
		
		createXProduct("Ελαιόλαδο ΑΑΑ 5L","Λάδι","Κρήτη","Αγνό παρθένο ελαιόλαδο που προέρχεται από την ποικιλία Κορωνείκη. Χαμηλότατη οξύτητα που καθιστά το λάδι μας την πρώτη επιλογή των περισσότερων νοικοκυριών τόσο και για την υγεία όσο και για την απαλή γεύση του.",BigDecimal.valueOf(41.22),BigDecimal.valueOf(37.66),BigDecimal.valueOf(11.5));
		createXProduct("Ελαιόλαδο 5L","Λάδι","Κρήτη"," Ελαιόλαδο θερμής επεξεργασίας με απολαυστική γεύση κατάλληλο για όλα τα γευματα και τις σαλάτεσ σας.",BigDecimal.valueOf(36.11),BigDecimal.valueOf(31.17),BigDecimal.valueOf(10.03));
		createXProduct("Πυρηνέλαιο 750ml","Λάδι","Κρήτη","Πυρήνελαιο για το καντήλι.",BigDecimal.valueOf(1.14),BigDecimal.valueOf(1.02),BigDecimal.valueOf(0.34));
		
		createXProduct("Αλατισμένες Ελιές σε νερό 0.5kg","Ελιές","Κρήτη","Ποικιλία Θρούμπα μεγάλη σαρκώδης ελιά ιδανική για μεζέδες και κατάλληλο συνοδευτικό για το καθημερινό σας γεύμα. Σε νερό ώστε να μπορέσετε να τις διατηρήσετε για εκτεταμένα χρονικά διαστήματα.",BigDecimal.valueOf(8.11),BigDecimal.valueOf(7.92),BigDecimal.valueOf(2.56));
		createXProduct("Ξυδάτες Ελιές σε λάδι 0.5kg","Ελιές","Κρητη","Ποικιλία Θρούμπα μεγάλη σαρκώδης ελιά ιδανική για μεζέδες και κατάλληλο συνοδευτικό για το καθημερινό σας γεύμα. Σε ελαιόλαδο Α κατηγορίας.",BigDecimal.valueOf(10.11),BigDecimal.valueOf(9.72),BigDecimal.valueOf(3.22));
		
		createXProduct("Θυμάρι 1kg","Μέλι","Κρήτη","Αρωματικό θυμαρίσιο μέλι από τον τόπο μας εξαιρετικής ποιότητας και γεύσης.",BigDecimal.valueOf(14.57),BigDecimal.valueOf(13.11),BigDecimal.valueOf(4.31));
		createXProduct("Έλατο 1kg","Μέλι","Κρήτη","Μέλι από τα έλατα της Κρήτης. Ιδιαίτερη γεμάτη γεύση που θα ενθουσιάσει τους πάντες.",BigDecimal.valueOf(15.61),BigDecimal.valueOf(14.33),BigDecimal.valueOf(4.7));
		createXProduct("Ανθόμελο 1kg","Μέλι","Κρήτη","Κλασσικό ανθόμελο από τον τόπο μας.",BigDecimal.valueOf(11.19),BigDecimal.valueOf(10.75),BigDecimal.valueOf(3.53));
		createXProduct("Ανάμεικτο 1kg","Μέλι","Κρήτη","Ανάμεικτο μέλι ΑΑ ποιότητας.",BigDecimal.valueOf(10.02),BigDecimal.valueOf(9.76),BigDecimal.valueOf(3.25));
		
		createXProduct("Πατάτες 1kg","Λαχανικά","Κρήτη","Πατάτες κατάλληλες τόσο για τηγάνι όσο και για το ταψί.",BigDecimal.valueOf(0.60),BigDecimal.valueOf(0.52),BigDecimal.valueOf(0.16));
		createXProduct("Κρεμύδια(Κόκκινα) 1kg","Λαχανικά","Κρήτη","Κρεμύδια κόκκινα γλυκά κατάλληλα για την χωριάτικη σαλάτα σας με τάκο.",BigDecimal.valueOf(0.35),BigDecimal.valueOf(0.31),BigDecimal.valueOf(0.1));
		createXProduct("Κρεμύδια(Άσπρα) 1kg","Λαχανικά","Κρήτη","Κρεμύδια για μαγείρεμα. ΑΑ ποιότητα. Δεν συστήνονται για ωμή κατανάλωση.",BigDecimal.valueOf(0.35),BigDecimal.valueOf(0.28),BigDecimal.valueOf(0.08));
		createXProduct("Τομάτες 1kg","Λαχανικά","Κρήτη","Οι ξεχωριστές τομάτες μας που είναι αδιανόητο να λείπουν από την καθημερινή σαλάτα μας.",BigDecimal.valueOf(1.34),BigDecimal.valueOf(1.22),BigDecimal.valueOf(0.41));
		
		createXProduct("Πορτοκάλια 1kg","Φρούτα","Κρήτη"," Πορτοκάλια Valencia ζουμερά ιδανικά για φρέσκους υγιεινούς χυμούς και όχι μόνο.",BigDecimal.valueOf(0.80),BigDecimal.valueOf(0.76),BigDecimal.valueOf(0.25));
		createXProduct("Μπανάνες 1kg","Φρούτα","Κρήτη","Baby ποικιλία της πατρίδας μας.",BigDecimal.valueOf(1.43),BigDecimal.valueOf(1.27),BigDecimal.valueOf(0.41));
		createXProduct("Σταφύλι(Φράουλα) 1kg","Φρούτα","Κρήτη","Γλυκό σταφύλι με την χαρακτηριστική κόκκινη ρόγα.",BigDecimal.valueOf(1.45),BigDecimal.valueOf(1.31),BigDecimal.valueOf(0.43));
		createXProduct("Σταφύλι(Μοσχάτο)","Φρούτα","Κρήτη","Έξτρα αρωματικό και απολαυστικό σταφύλι της γνωστής εκλεκτής ποικιλίας Μοσχάτο.",BigDecimal.valueOf(2.03),BigDecimal.valueOf(1.87),BigDecimal.valueOf(0.92));
		createXProduct("Σταφίδα 1kg","Φρούτα","Κρήτη","Σταφίδες.",BigDecimal.valueOf(0.61),BigDecimal.valueOf(0.46),BigDecimal.valueOf(0.15));
		
		
		createXProduct("Κόκκινος Γλυκός Οίνος 750ml","Κρασί","Κρήτη","Ερυθρός οίνος 12%Vol σοδιάς 2016 του συνεταιρισμού μας κατάλληλος για κρεατικά.",BigDecimal.valueOf(9.20),BigDecimal.valueOf(8.90),BigDecimal.valueOf(2.67));
		createXProduct("Λευκός Ξερός Οίνος 750ml","Κρασί","Κρήτη","Λευκός οίνος ξηρός 12%Vol σοδιάς 2016 του συνεταιρισμού μας για την συνοδεία των θαλασσινών γευμάτων σας.",BigDecimal.valueOf(8.87),BigDecimal.valueOf(8.35),BigDecimal.valueOf(2.41));
		createXProduct("CABERNET ΑΑΑ 750ml","Κρασί","Κρήτη","Βαθύς κόκκινος οίνος 14%Vol με την χαρακτηριστικά γεμάτη γεύση του και το άρωμα του. Για αυτούς που έχουν εκλεκτά γούστα στο κρασί. Σοδειάς 2011 ωριμάζει στα κελάρια μας περιμένοντας σας να τον απολαύσετε στις ξεχωριστές και όχι μόνο στιγμές σας.",BigDecimal.valueOf(13.72),BigDecimal.valueOf(13.07),BigDecimal.valueOf(4.09));
		createXProduct("Μαυροδάφνη 250ml","Κρασί","Κρήτη","Για αυτούς που προτιμάνε το πιο γλυκό κρασί. Ευκολόπιωτο , ίσως περισσότερο από ότι θα έπρεπε. Συνιστάμε την προσοχή σας αν πρόκειται να οδηγήσετε καθώς είναι 17% Vol",BigDecimal.valueOf(3.15),BigDecimal.valueOf(3.01),BigDecimal.valueOf(1.0));
		createXProduct("Ρακή 1L","Κρασί","Κρήτη","Για τους εξτρά τολμηρούς. Αν το αντέχεις αποτελεί πολύ καλή συντροφιά. Μερικοί συμπατριώτες μας το χρησιμοποιούν το χειμώνα αντί για πετρέλαιο θέρμανσης...hic!",BigDecimal.valueOf(2.25),BigDecimal.valueOf(2.01),BigDecimal.valueOf(0.67));
		
		createXProduct("Λευκό Μαλακό 1kg","Αλεύρι","Κρήτη","Λευκό αλεύρι μαλακό για τα κουλουράκια και γλυκά σας.",BigDecimal.valueOf(1.35),BigDecimal.valueOf(1.22),BigDecimal.valueOf(0.4));
		createXProduct("Λευκό Σκληρό 1kg","Αλεύρι","Κρήτη","Λευκό αλεύρι σκλήρο για το ψωμί.",BigDecimal.valueOf(1.45),BigDecimal.valueOf(1.31),BigDecimal.valueOf(0.42));
		createXProduct("Μαύρο 1kg","Αλεύρι","Κρήτη","Ολικής αλέσεως ιδανικό για το χωριάτικο ψωμί της γιαγιάς.",BigDecimal.valueOf(1.27),BigDecimal.valueOf(1.13),BigDecimal.valueOf(0.35));
		createXProduct("Αραβοσίτου 1kg","Αλεύρι","Κρήτη"," Γλυκό αλεύρι αραβοσίτου απαραίτητο για την περιστασιακή μπομπότα ή για γλυκό ψωμί.",BigDecimal.valueOf(1.39),BigDecimal.valueOf(1.21),BigDecimal.valueOf(0.4));
		
	}
	//===============================Generation of Data==============================
	
	public void generate() throws Exception 
	{
		createRoles();
		createInitialUsers();
		createInitialProducts();
		generateSellers();
		generatePastSales(1000);
		generateRecentSales(35);
	}
	
}