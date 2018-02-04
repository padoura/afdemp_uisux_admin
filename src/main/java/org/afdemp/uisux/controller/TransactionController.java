package org.afdemp.uisux.controller;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;

import org.afdemp.uisux.domain.Product;
import org.afdemp.uisux.domain.Transaction;
import org.afdemp.uisux.domain.Account;
import org.afdemp.uisux.domain.ClientOrder;
import org.afdemp.uisux.service.AccountService;
import org.afdemp.uisux.service.ClientOrderService;
import org.afdemp.uisux.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/transaction")
public class TransactionController {
	
	
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private ClientOrderService clientOrderService;
	
	@Autowired
	private TransactionService transactionService;
	
//	@RequestMapping("/accountList")
//	public String accountList(Model model) {
//		List<Account> accountList = accountService.findAll();
//		model.addAttribute("accountList", accountList);		
//		return "accountList";
//	}
//	
//	
//	@RequestMapping(value = "/accountInfo", method = RequestMethod.GET)
//	public String accountInfo(@RequestParam("id") Long id, Model model) {
//		Account account = accountService.findOne(id);
//		
//		List<Transaction> withdrawList = transactionService.fetchAccountWithdrawsByPeriod(account, Timestamp.valueOf(
//		LocalDate.now().minusDays(6).atStartOfDay()), Timestamp.valueOf(LocalDate.now().atTime(23, 59, 59)));
//		
//		List<Transaction> depositList = transactionService.fetchAccountDepositsByPeriod(account, Timestamp.valueOf(
//		LocalDate.now().minusDays(6).atStartOfDay()), Timestamp.valueOf(LocalDate.now().atTime(23, 59, 59)));
//		
//		model.addAttribute("account", account);
//		model.addAttribute("account", withdrawList);
//		model.addAttribute("account", depositList);
//		return "accountInfo";
//	}
//	
//	@RequestMapping(value = "/accountInfo", method = RequestMethod.POST)
//	public String searchAccountInfoByPeriod(@ModelAttribute("id") Long id,
//			@ModelAttribute("fromDate") String fromDate,
//			@ModelAttribute("toDate") String toDate, Model model) {
//		Account account = accountService.findOne(id);
//		
//		List<Transaction> withdrawList = transactionService.fetchAccountWithdrawsByPeriod(account, Timestamp.valueOf(
//				LocalDate.parse(fromDate).atStartOfDay()), Timestamp.valueOf(LocalDate.parse(toDate).atTime(23, 59, 59)));
//		
//		List<Transaction> depositList = transactionService.fetchAccountDepositsByPeriod(account, Timestamp.valueOf(
//				LocalDate.parse(fromDate).atStartOfDay()), Timestamp.valueOf(LocalDate.parse(toDate).atTime(23, 59, 59)));
//		
//		model.addAttribute("account", account);
//		model.addAttribute("account", withdrawList);
//		model.addAttribute("account", depositList);
//		return "redirect:/transaction/accountInfo?id="+account.getId();
//	}
//	
//	@RequestMapping(value = "/sendEarnings", method = RequestMethod.GET)
//	public String sendEarnings(Model model) {
//		List<ClientOrder> clientOrderList = clientOrderService.findAllUndistributedEarnings();
//		model.addAttribute("clientOrderList", clientOrderList);
//		return "sendEarnings";
//	}

}
