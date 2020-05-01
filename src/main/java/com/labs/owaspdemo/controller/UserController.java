package com.labs.owaspdemo.controller;

import com.labs.owaspdemo.model.User;
import com.labs.owaspdemo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class UserController {
	
	
	private final UserService userService;
	
	public UserController(final UserService userService) {
		this.userService = userService;
	}
	
	
	@RequestMapping(value= {"/login"}, method=RequestMethod.POST)
	public ModelAndView login(@Valid User user) {
		ModelAndView model = new ModelAndView();

		user = userService.findUserByMailAndPassword(user.getEmail(), user.getPassword());

		if(user != null) {
			model.addObject("user", user);
			model.setViewName("home/home");
			model.addObject("userName", user.getFirstname() + " " + user.getLastname());
			
		} else {
			model.setViewName("errors/access_denied");
		}

		return model;
	}
	
	@RequestMapping(value= { "/"}, method= RequestMethod.GET)
	public ModelAndView login() {
		ModelAndView model = new ModelAndView();
		
		model.setViewName("login");
		return model;
	}
	
	@RequestMapping(value= {"/signup"}, method=RequestMethod.GET)
	public ModelAndView signup() {
		ModelAndView model = new ModelAndView();
		User user = new User();
		model.addObject("user", user);
		model.setViewName("user/signup");
		
		return model;
	}
	
	@RequestMapping(value= {"/signup"}, method=RequestMethod.POST)
	public ModelAndView createUser(@Valid User user, BindingResult bindingResult) {
		ModelAndView model = new ModelAndView();
		
		if(userService.userExist(user.getEmail())) {
			bindingResult.rejectValue("email", "error.user", "Este email ja existe!");
		}
		
		if (!bindingResult.hasErrors()) {
			userService.saveUser(user);
			model.addObject("msg", "Usuário cadastrado com sucesso!");
			model.addObject("user", new User());
		}
		model.setViewName("user/signup");
		
		return model;
	}
	

	@RequestMapping(value= {"/access_denied"}, method=RequestMethod.GET)
	public ModelAndView accessDenied() {
		ModelAndView model = new ModelAndView();
		model.setViewName("errors/access_denied");
		return model;
	}
}
