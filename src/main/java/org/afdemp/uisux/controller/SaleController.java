package org.afdemp.uisux.controller;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.afdemp.uisux.domain.ClientOrder;
import org.afdemp.uisux.service.ClientOrderService;
import org.afdemp.uisux.utility.DataGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/sale")
public class SaleController {
	
//	@Autowired
//	private ClientOrderService clientOrderService;
	
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public String searchSales(Model model) {
		LocalDate fromDate = LocalDate.now().minusWeeks(1);
		LocalDate toDate = LocalDate.now();
		List<ClientOrder> clientOrderList = new ArrayList<>();
		model.addAttribute("fromDate", fromDate);
		model.addAttribute("toDate", toDate);
		model.addAttribute("clientOrderList", clientOrderList);
		return "searchSales";
	}
	
	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public String searchSalesPost(@ModelAttribute("fromDate") LocalDate fromDate,
			@ModelAttribute("toDate") LocalDate toDate, Model model) {
		
//		List<ClientOrder> clientOrderList = clientOrderService.fetchOrdersByPeriod(Timestamp.valueOf(
//				fromDate.atStartOfDay()), Timestamp.valueOf(
//						toDate.atTime(23, 59, 59)));
		
		List<ClientOrder> clientOrderList = DataGenerator.getFakeOrderList();
		
		model.addAttribute("clientOrderList", clientOrderList);
		return "searchSales";
	}

}
