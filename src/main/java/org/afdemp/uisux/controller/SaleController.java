package org.afdemp.uisux.controller;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.afdemp.uisux.domain.ClientOrder;
import org.afdemp.uisux.service.ClientOrderService;
import org.afdemp.uisux.utility.DataGenerator;
import org.afdemp.uisux.utility.SaleUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/sale")
public class SaleController {
	
	@Autowired
	private ClientOrderService clientOrderService;
	
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public String searchSales(Model model) {
		
		List<ClientOrder> clientOrderList = new ArrayList<>();
		String fromDate=LocalDate.now().minusWeeks(1).toString();
		String toDate=LocalDate.now().toString();
		
		model.addAttribute("fromDate", fromDate);
		model.addAttribute("toDate", toDate);
		model.addAttribute("clientOrderList", clientOrderList);
		return "searchSales";
	}
	
	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public String searchSalesPost(@ModelAttribute("fromDate") String fromDate, BindingResult fromResult,
			@ModelAttribute("toDate") String toDate, BindingResult toResult, Model model) {
		
		List<ClientOrder> clientOrderList2 = clientOrderService.fetchOrdersByPeriod(Timestamp.valueOf(
				LocalDate.parse(fromDate).atStartOfDay()), Timestamp.valueOf(LocalDate.parse(toDate).atTime(23, 59, 59)));
		List<ClientOrder> clientOrderList = new ArrayList<>();
		for (ClientOrder co : clientOrderList2) {
			clientOrderList.add(SaleUtility.copyValuesToNewObject(co));
		}
		
//		List<ClientOrder> clientOrderList = DataGenerator.getFakeOrderList();
		
		model.addAttribute("clientOrderList", clientOrderList);
		return "searchSales";
	}

}
