package org.afdemp.uisux.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

	@RequestMapping("/")
	public String index(){
		return "redirect:/home";
	}
	
	@RequestMapping("/home")
	public String home(){
		return "home";
	}
	
	@RequestMapping("/login")
	public String login(){
		return "login";
	}
	
//	@RequestMapping("/bookList2")
//	public String bookList2(){
//		return "bookList2";
//	}
//	
//	@RequestMapping("/financial")
//	public String financial(){
//		return "financial";
//	}
}
