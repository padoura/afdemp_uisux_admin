package org.afdemp.uisux.controller;

import java.util.ArrayList;

import org.afdemp.uisux.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ResourceController {

	@Autowired
	private ProductService productService;
	
	@RequestMapping(value="/product/deactivateList", method=RequestMethod.POST)
	public String deactivateProductList(
			@RequestBody ArrayList<String> productIdList, Model model
			){
	String	productId2="keno";
		for (String id : productIdList) {
			String productId =id.substring(8);
			productService.deactivate(Long.parseLong(productId));
			productId2=productId2+productId;
		}
		
		return productId2;
	}
	@RequestMapping(value="/deactivateList", method=RequestMethod.POST)
	public String deactivateList(
			
			//out.println("ascadedwed");
			
			@RequestBody ArrayList<String> productIdList, Model model
			){
		String productId="keno";
		for (String id : productIdList) {
			 productId =id.substring(8);
			productService.deactivate(Long.parseLong(productId));
				}
		
		return productId;
	}
	
	@RequestMapping(value="/product/activateList", method=RequestMethod.POST)
	public String activateProductList(
			@RequestBody ArrayList<String> productIdList, Model model
			){
		
		for (String id : productIdList) {
			String productId =id.substring(8);
			productService.activate(Long.parseLong(productId));
		}
		
		return "activate success";
	}
}