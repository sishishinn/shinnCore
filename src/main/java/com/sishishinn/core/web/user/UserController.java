package com.sishishinn.core.web.user;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sishishinn.core.service.user.UserManager;

@Controller
@RequestMapping(value = "/user")
public class UserController {
	
	@Autowired
	private UserManager userManager;
	
	@RequestMapping(method = RequestMethod.GET)
	public String list(HttpServletRequest request){
		return "core/user/userList";
	}
	
	@RequestMapping(value = "form", method = RequestMethod.GET)
	public String input(HttpServletRequest request){
		return "core/user/userForm";
	}
	
	@RequestMapping(value = "add", method = RequestMethod.POST)
	public String add(HttpServletRequest request){
		return "redirect:/core/user/";
	}
	
	@RequestMapping(value = "delete", method = RequestMethod.GET)
	public String delete(HttpServletRequest request){
		return "redirect:/core/user/";
	}
	
}

