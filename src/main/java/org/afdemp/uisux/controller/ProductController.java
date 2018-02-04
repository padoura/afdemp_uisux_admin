package org.afdemp.uisux.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.afdemp.uisux.domain.Category;
import org.afdemp.uisux.domain.Product;
import org.afdemp.uisux.domain.ShoppingCart;
import org.afdemp.uisux.domain.MemberCartItem;
import org.afdemp.uisux.domain.User;
import org.afdemp.uisux.service.MemberCartItemService;
import org.afdemp.uisux.service.CategoryService;
import org.afdemp.uisux.service.ProductService;
import org.afdemp.uisux.utility.ImageUtility;

@Controller
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@Autowired
	private CategoryService categoryService;

	@Autowired
	private  MemberCartItemService memberCartItemService;

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String addProduct(Model model) {
		Product product = new Product();
		Category category = new Category();
		List<Category> categoryList = categoryService.findAll();
		model.addAttribute("product", product);
		model.addAttribute("category", category);
		model.addAttribute("categoryList", categoryList);
		return "addProduct";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addProductPost(@ModelAttribute("product") Product product, BindingResult productResult,
				@ModelAttribute("type") String type, BindingResult typeResult, Model model) {

		if (productResult.hasErrors() || typeResult.hasErrors()) {
			model.addAttribute("insertSuccess",false);
			return "addProduct";
		}

		boolean success;
		if ((productService.createProduct(product, type)!=null) & ImageUtility.trySaveImage(product)) {
				success = true;
		}else{
			success = false;
		}
		
		product = new Product();
		List<Category> categoryList = categoryService.findAll();
		model.addAttribute("categoryList",categoryList);
		model.addAttribute("insertSuccess",success);
		return "addProduct";
	}
	
	@RequestMapping("/productList")
	public String productList(Model model) {
		List<Product> productList = productService.findAll();
		model.addAttribute("productList", productList);		
		return "productList";
	}
	
	@RequestMapping(value="/remove", method=RequestMethod.POST)
	public String remove(
			@ModelAttribute("id") String id, Model model
			) {
		productService.removeOne(Long.parseLong(id.substring(8)));
		List<Product> productList = productService.findAll();
		model.addAttribute("productList", productList);
		return "redirect:/product/productList";
	}
	
	
	@RequestMapping("/productInfo")
	public String productInfo(@RequestParam("id") Long id, Model model) {
		Product product = productService.findOne(id);
		model.addAttribute("product", product);
		
		return "productInfo";
	}
	
	@RequestMapping("/updateProduct")
	public String updateProduct(@RequestParam("id") Long id, Model model) {
		Product product = productService.findOne(id);
		List<Category> categoryList = categoryService.findAll();
		model.addAttribute("product", product);
		model.addAttribute("categoryList", categoryList);
		return "updateProduct";
	}
	
	@RequestMapping(value="/updateProduct", method=RequestMethod.POST)
	public String updateProductPost(@ModelAttribute("product") Product product, BindingResult productResult,
			@ModelAttribute("type") String type, BindingResult typeResult, Model model) {
		
//		if (productResult.hasErrors()){
//			System.out.println("productResult problem");
//		}
//		
//		if (typeResult.hasErrors()){
//			System.out.println("typeResult problem");
//		}
		
		if (productResult.hasErrors() || typeResult.hasErrors()) {
			return "redirect:/product/productInfo?id="+product.getId();
		}

		boolean success;
		if ((productService.createProduct(product, type)!=null) & ImageUtility.trySaveImage(product)) {
				success = true;
		}else{
			success = false;
		}
		model.addAttribute("updateSuccess",success);
		
		return "redirect:/product/productInfo?id="+product.getId();
	}
	
	
	@RequestMapping("/toggleProductActive")
	public String toggleProductActive(@RequestParam("id") String id, Model model) {
		String productId =id.substring(8);
		Product product = productService.findOne(Long.parseLong(productId));
		productService.toggleActive(product);
		model.addAttribute("product", product);
		return "productList";
	}
	
	@RequestMapping(value = "/stockUp", method = RequestMethod.GET)
	public String stockUp(@RequestParam("id") Long id, Model model) {
		Product product = productService.findOne(id);
		List<MemberCartItem> memberCartItemList = memberCartItemService.findAllAvailableItems(id);
		List<User> memberList = new ArrayList<>();
		
		for (MemberCartItem memberCartItem : memberCartItemList) {
			memberList.add(memberCartItem.getShoppingCart().getUserRole().getUser()); 
		}
		
		model.addAttribute("product", product);
		model.addAttribute("userList", memberList);
		model.addAttribute("cartItemList", memberCartItemList);
		return "stockUp";
	}
	
	@RequestMapping(value = "/stockUp", method = RequestMethod.POST)
	public String stockUpPost(@ModelAttribute("memberCartItemId") Long memberCartItemId,
			@ModelAttribute("userId") Long userId,
			@ModelAttribute("qty") Integer qty,
			Model model) {
		
		MemberCartItem memberCartItem = memberCartItemService.findById(memberCartItemId);
		
		if (qty > memberCartItem.getQty()) {
			model.addAttribute("requestExceedsAvailability", true);
			return "productList";
		}else if (qty == memberCartItem.getQty()) {
			memberCartItemService.fullPurchaseFromMember(memberCartItem);
		}else {
			memberCartItemService.partialPurchaseFromMember(memberCartItem, qty);
		}
		
		model.addAttribute("stockUpSuccess", true);
		return "productList";
	}


}