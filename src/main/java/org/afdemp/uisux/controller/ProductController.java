package org.afdemp.uisux.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.afdemp.uisux.domain.Category;
import org.afdemp.uisux.domain.Product;
import org.afdemp.uisux.repository.CategoryRepository;
import org.afdemp.uisux.repository.ProductRepository;
import org.afdemp.uisux.service.ProductService;

@Controller
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@Autowired
	private CategoryRepository categoryRepository;

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String addProduct(Model model) {
		Product product = new Product();
		Category category = new Category();
		model.addAttribute("product", product);
		model.addAttribute("category", category);
		return "addProduct";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addProductPost(@ModelAttribute("product") Product product, BindingResult productResult,
				@ModelAttribute("type") String type, BindingResult typeResult) throws Exception {
		
		if (productResult.hasErrors() || typeResult.hasErrors())
			return "addProduct";
		
		productService.createProduct(product, type);

		MultipartFile productImage = product.getProductImage();

		try {
			byte[] bytes = productImage.getBytes();
			String name = product.getId() + ".png";
			BufferedOutputStream stream = new BufferedOutputStream(
					new FileOutputStream(new File("src/main/resources/static/image/product/" + name)));
			stream.write(bytes);
			stream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "redirect:/";
	}
	
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
//	public String updateProductPost(@ModelAttribute("product") Product product, HttpServletRequest request) {
//		productService.save(product);
//		
//		MultipartFile productImage = product.getProductImage();
//		
//		if(!productImage.isEmpty()) {
//			try {
//				byte[] bytes = productImage.getBytes();
//				String name = product.getId() + ".png";
//				
//				Files.delete(Paths.get("src/main/resources/static/image/product/"+name));
//				
//				BufferedOutputStream stream = new BufferedOutputStream(
//						new FileOutputStream(new File("src/main/resources/static/image/product/" + name)));
//				stream.write(bytes);
//				stream.close();
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
//		
//		return "redirect:/product/productInfo?id="+product.getId();
//	}
//	
//	@RequestMapping("/productList")
//	public String productList(Model model) {
//		List<Product> productList = productService.findAll();
//		model.addAttribute("productList", productList);		
//		return "productList";
//		
//	}
//	@RequestMapping("/productList2")
//	public String productList2(Model model) {
//		List<Product> productList = productService.findAll();
//		model.addAttribute("productList", productList);		
//		return "productList2";	
//	}
//	
//	@RequestMapping(value="/remove1", method=RequestMethod.POST)
//	public String remove(
//			@ModelAttribute("id") String id, Model model
//			) {
//		productService.removeOne(Long.parseLong(id.substring(8)));
//		List<Product> productList = productService.findAll();
//		model.addAttribute("productList", productList);
//		
//		return "redirect:/product/productList";
//	}

}