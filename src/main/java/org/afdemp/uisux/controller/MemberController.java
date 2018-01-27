package org.afdemp.uisux.controller;

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
import org.afdemp.uisux.domain.User;
import org.afdemp.uisux.repository.CategoryRepository;
import org.afdemp.uisux.service.ProductService;
import org.afdemp.uisux.utility.ImageUtility;

@Controller
@RequestMapping("/member")
public class MemberController {
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String addMember(Model model) {
		User user = new User();
		model.addAttribute(user);
		return "addMember";
	}
	

//	@Autowired
//	private ProductService productService;
//	
//	@Autowired
//	private CategoryRepository categoryRepository;
//
//	@RequestMapping(value = "/add", method = RequestMethod.GET)
//	public String addProduct(Model model) {
//		Product product = new Product();
//		Category category = new Category();
//		model.addAttribute("product", product);
//		model.addAttribute("category", category);
//		return "addProduct";
//	}
//
//	@RequestMapping(value = "/add", method = RequestMethod.POST)
//	public String addProductPost(@ModelAttribute("product") Product product, BindingResult productResult,
//				@ModelAttribute("type") String type, BindingResult typeResult, Model model) throws Exception {
//		
//		if (productResult.hasErrors() || typeResult.hasErrors()) {
//			model.addAttribute("insertSuccess",false);
//			return "addProduct";
//		}
//
//		boolean success;
//		if (productService.createProduct(product, type) & ImageUtility.trySaveImage(product)) {
//				success = true;
//		}else{
//			success = false;
//		}
//		model.addAttribute("insertSuccess",success);
//		return "addProduct";
//	}
//	
//	@RequestMapping("/productList")
//	public String productList(Model model) {
//		List<Product> productList = productService.findAll();
//		model.addAttribute("productList", productList);		
//		return "productList";
//	}
//	
//	@RequestMapping(value="/remove", method=RequestMethod.POST)
//	public String remove(
//			@ModelAttribute("id") String id, Model model
//			) {
//		productService.removeOne(Long.parseLong(id.substring(8)));
//		List<Product> productList = productService.findAll();
//		model.addAttribute("productList", productList);
//		return "redirect:/product/productList";
//	}
//	
//	
//	@RequestMapping("/productInfo")
//	public String productInfo(@RequestParam("id") Long id, Model model) {
//		Product product = productService.findOne(id);
//		model.addAttribute("product", product);
//		
//		return "productInfo";
//	}
//	
//	@RequestMapping("/updateProduct")
//	public String updateProduct(@RequestParam("id") Long id, Model model) {
//		Product product = productService.findOne(id);
//		model.addAttribute("product", product);
//		
//		return "updateProduct";
//	}
//	
//	@RequestMapping(value="/updateProduct", method=RequestMethod.POST)
//	public String updateProductPost(@ModelAttribute("product") Product product, BindingResult productResult,
//			@ModelAttribute("type") String type, BindingResult typeResult, Model model) {
//		
//		if (productResult.hasErrors() || typeResult.hasErrors()) {
//			model.addAttribute("updateSuccess",false);
//			return "redirect:/product/productInfo?id="+product.getId();
//		}
//
//		boolean success;
//		if (productService.createProduct(product, type) & ImageUtility.trySaveImage(product)) {
//				success = true;
//		}else{
//			success = false;
//		}
//		model.addAttribute("updateSuccess",success);
//		
//		return "redirect:/product/productInfo?id="+product.getId();
//	}
	


}