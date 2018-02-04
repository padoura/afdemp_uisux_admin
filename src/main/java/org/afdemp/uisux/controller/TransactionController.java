package org.afdemp.uisux.controller;

import java.util.List;

import org.afdemp.uisux.domain.Product;
import org.afdemp.uisux.domain.Account;
import org.afdemp.uisux.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/transaction")
public class TransactionController {
	
	
	@Autowired
	private AccountService accountService;
	
	@RequestMapping("/accountList")
	public String accountList(Model model) {
		List<Account> accountList = accountService.findAll();
		model.addAttribute("accountList", accountList);		
		return "accountList";
	}
	
	
	
	
//	@Autowired
//	private ClientOrderService clientOrderService;
//	
//	@RequestMapping(value = "/search", method = RequestMethod.GET)
//	public String searchSales(Model model) {
//		
//		List<ClientOrder> clientOrderList = new ArrayList<>();
//		String fromDate=LocalDate.now().minusWeeks(1).toString();
//		String toDate=LocalDate.now().toString();
//		
//		model.addAttribute("fromDate", fromDate);
//		model.addAttribute("toDate", toDate);
//		model.addAttribute("clientOrderList", clientOrderList);
//		return "searchSales";
//	}
//	
//	@RequestMapping(value = "/search", method = RequestMethod.POST)
//	public String searchSalesPost(@ModelAttribute("fromDate") String fromDate, BindingResult fromResult,
//			@ModelAttribute("toDate") String toDate, BindingResult toResult, Model model) {
//		
//		List<ClientOrder> clientOrderList2 = clientOrderService.fetchOrdersByPeriod(Timestamp.valueOf(
//				LocalDate.parse(fromDate).atStartOfDay()), Timestamp.valueOf(LocalDate.parse(toDate).atTime(23, 59, 59)));
//		List<ClientOrder> clientOrderList = new ArrayList<>();
//		for (ClientOrder co : clientOrderList2) {
//			clientOrderList.add(SaleUtility.copyValuesToNewObject(co));
//		}
//		
////		List<ClientOrder> clientOrderList = DataGenerator.getFakeOrderList();
//		
//		model.addAttribute("clientOrderList", clientOrderList);
//		return "searchSales";
//	}

}
