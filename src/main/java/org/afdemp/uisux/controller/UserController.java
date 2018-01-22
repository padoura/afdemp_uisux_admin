//package org.afdemp.uisux.controller;
//
//import java.io.BufferedOutputStream;
//import java.io.File;
//import java.io.FileOutputStream;
//import java.nio.file.Files;
//import java.nio.file.Paths;
//import java.util.List;
//
//import javax.servlet.http.HttpServletRequest;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.multipart.MultipartFile;
//
//import com.adminportal.domain.Book;
//import com.adminportal.domain.User;
//import com.adminportal.service.BookService;
//import com.adminportal.service.UserService;
//
//@Controller
//@RequestMapping("/user")
//public class UserController {
//
//	@Autowired
//	private UserService userService;
//	@Autowired
//	private BookService bookService;
//
//
//	
//	
//	
//	
//	@RequestMapping("/UserInfo")
//	public String UserInfo(@RequestParam("username") String username, Model model) {
//		User user = userService.findOne(username);
//		model.addAttribute("user", user);
//		
//		return "userInfo";
//	}
//	@RequestMapping("/demo")
//	public String demo() {
//		return "memberList";
//	}
//	
//	@RequestMapping("/updateUser")
//	public String updateBook(@RequestParam("username") String username, Model model) {
//		User user = userService.findOne(username);
//		model.addAttribute("user", user);
//		
//		return "updateUser";
//	}
//	
//	@RequestMapping(value="/updateUser", method=RequestMethod.POST)
//	public String updateUserPost(@ModelAttribute("user") User user, HttpServletRequest request) {
//		userService.save(user);
//		
//		
//		
//		return "redirect:/user/UserInfo?username="+user.getUsername();
//	}
//	
//	
//	@RequestMapping("/memberList")
//	public String memberList(Model model) {
//		List<User> memberList = userService.findAll();
//		model.addAttribute("memberList", memberList);		
//
//		return "memberList";
//	
//		
//	}
//	@RequestMapping("/bookList")
//	public String bookList(Model model) {
//		List<Book> bookList = bookService.findAll();
//		model.addAttribute("bookList", bookList);		
//		return "bookList2";
//		
//	}
//	@RequestMapping("/bookList2")
//	public String bookList2(Model model) {
//		List<Book> bookList = bookService.findAll();
//		model.addAttribute("bookList", bookList);		
//		return "productU";
//		
//	}
//	@RequestMapping("/finance")
//	public String finance(Model model) {
//		List<Book> bookList = bookService.findAll();
//		model.addAttribute("bookList", bookList);		
//		return "financial";
//		
//	}
//	@RequestMapping("/bookInfo")
//	public String bookInfo(@RequestParam("id") Long id, Model model) {
//		Book book = bookService.findOne(id);
//		model.addAttribute("book", book);
//		
//		return "bookUInfo";
//	}
//	
//	@RequestMapping("/updateBook")
//	public String updateBook(@RequestParam("id") Long id, Model model) {
//		Book book = bookService.findOne(id);
//		model.addAttribute("book", book);
//		
//		return "updateBookU";
//	}
//	
//	@RequestMapping(value="/remove", method=RequestMethod.POST)
//	public String remove(
//			@ModelAttribute("id") Long id, Model model
//			) {
//		userService.removeOne(id);
//		List<User> userList = userService.findAll();
//		model.addAttribute("userList", userList);
//		
//		return "redirect:/user/userList";
//	}
//
//}
