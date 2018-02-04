package org.afdemp.uisux.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/transaction")
public class TransactionController {
	
	
	
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
