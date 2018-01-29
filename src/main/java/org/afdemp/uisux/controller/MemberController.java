package org.afdemp.uisux.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.afdemp.uisux.domain.security.Role;
import org.afdemp.uisux.domain.security.UserRole;
import org.afdemp.uisux.utility.SecurityUtility;

import org.afdemp.uisux.domain.User;
import org.afdemp.uisux.service.UserRoleService;
import org.afdemp.uisux.service.UserService;
import org.afdemp.uisux.utility.ΜailConstructor;

@Controller
@RequestMapping("/member")
public class MemberController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRoleService userRoleService;
	
	@Autowired
	private ΜailConstructor mailConstructor;
	
	@Autowired
	private JavaMailSender mailSender;
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String addMember(Model model) {
		User user = new User();
		model.addAttribute(user);
		return "addMember";
	}
	
	@RequestMapping(value="/add", method = RequestMethod.POST)
	public String newMemberPost(
			HttpServletRequest request,
			@ModelAttribute("user") User user,
			BindingResult userResult, 
			Model model
			) throws Exception{
		
		if (userResult.hasErrors()) {
			model.addAttribute("insertFailure",true);
			return "addMember";
		}
		
		model.addAttribute("classActiveNewAccount", true);
		model.addAttribute("user", user);
		
		User member = userService.findByUsername(user.getUsername());
		if (member != null) {
			if (!userRoleService.hasThisRole("ROLE_MEMBER", member)) {
				Role role = new Role();
				role.setRoleId(1);
				role.setName("ROLE_MEMBER");
				UserRole memberRole = new UserRole(member, role);
				member.getUserRoles().add(memberRole);
				userService.save(member);
				model.addAttribute("addedRoleToExistingUser", true); //no other changes to existing user takes place
			}else {
				model.addAttribute("memberAlreadyExistsFailure", true);
			}
			return "addMember";
		}else if (userService.findByEmail(user.getEmail()) != null) {
			model.addAttribute("emailAlreadyExistsFailure", true);
			return "addMember";
		}
		
		String password = SecurityUtility.randomPassword();
		
		String encryptedPassword = SecurityUtility.passwordEncoder().encode(password);
		user.setPassword(encryptedPassword);
		
		Role role = new Role();
		role.setRoleId(1);
		role.setName("ROLE_MEMBER");
		Set<UserRole> userRoles = new HashSet<>();
		userRoles.add(new UserRole(user, role));
		userService.createUser(user, userRoles);
		
		String token = UUID.randomUUID().toString();
		userService.createPasswordResetTokenForUser(user, token);
		
		String appUrl = "http://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();
		
		SimpleMailMessage email = mailConstructor.constructResetTokenEmail(appUrl, request.getLocale(), token, user, password);
		
		mailSender.send(email);
		user.setPassword(""); //to return empty password to model
		model.addAttribute("emailSent", "true");
		
		return "addMember";
	}
	
	@RequestMapping("/memberList")
	public String memberList(Model model) {
		List<User> memberList = userRoleService.fetchUsersOfRole("ROLE_MEMBER");
		
		model.addAttribute("memberList", memberList);

		return "memberList";
	}
	
	@RequestMapping("/memberInfo")
	public String memberInfo(@RequestParam("id") Long id, Model model) {
		User user = userService.findOne(id);
		user.setPassword(""); //password not sent to view
		model.addAttribute("user", user);
		
		return "memberInfo";
	}
	@RequestMapping("/updateMember")
	public String updateMember(@RequestParam("id") Long id, Model model) {
		User user = userService.findOne(id);
		user.setPassword(""); //password not sent to view
		model.addAttribute("user", user);
		return "updateMember";
	}
	
	@RequestMapping(value="/updateMember", method=RequestMethod.POST)
	public String updateMemberPost(@ModelAttribute("user") User user, BindingResult userResult,
			Model model) {
		
		if (userResult.hasErrors()) {
			model.addAttribute("insertFailure",true);
			return "redirect:/member/memberInfo?id=" + user.getId();
		}
		
		model.addAttribute("classActiveNewAccount", true);
		model.addAttribute("user", user);
		
		User existingUser = userService.findByEmail(user.getEmail());
		
		if (existingUser != null && !user.getId().equals(existingUser.getId())) {
			model.addAttribute("emailAlreadyExistsFailure", true);
			return "redirect:/member/memberInfo?id=" + user.getId();
		}else {
			existingUser = userService.findByUsername(user.getUsername());
			if (existingUser != null && !user.getId().equals(existingUser.getId())) {
				model.addAttribute("usernameAlreadyExistsFailure", true);
				return "redirect:/member/memberInfo?id=" + user.getId();
			}
		}
		
		existingUser = userService.findOne(user.getId());
		
		existingUser.updateUser(user);
		userService.save(existingUser);
		model.addAttribute("updateSuccess",true);
		return "redirect:/member/memberInfo?id=" + user.getId();
	}
}