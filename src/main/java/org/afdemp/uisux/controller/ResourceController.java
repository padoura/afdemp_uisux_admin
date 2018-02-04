package org.afdemp.uisux.controller;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.afdemp.uisux.domain.Account;
import org.afdemp.uisux.domain.ClientOrder;
import org.afdemp.uisux.domain.Transaction;
import org.afdemp.uisux.service.ClientOrderService;
import org.afdemp.uisux.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ResourceController {

	@Autowired
	private ProductService productService;
	
	@Autowired
	private ClientOrderService clientOrderService;
	
	@RequestMapping(value="/product/deactivateList", method=RequestMethod.POST)
	public String deactivateProductList(
			@RequestBody ArrayList<String> productIdList, Model model
			){
		
		for (String id : productIdList) {
			String productId =id.substring(8);
			productService.deactivate(Long.parseLong(productId));
		}
		
		return "deactivate success";
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
	
//	@RequestMapping(value="/transaction/sendEarnings", method=RequestMethod.POST)
//	public String sendEarningsPost(
//			@RequestBody ArrayList<String> clientOrderIdList, Model model
//			){
//		
//		for (String id : clientOrderIdList) {
//			String clientOrderId =id.substring(8);
//			clientOrderService.distributeEarningsToAllMembers(Long.parseLong(clientOrderId));
//		}
//		
//		return "distribution success";
//	}
	
	
}