package com.cookingrecipes.cookingrecipeswebmvc.web;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.cookingrecipes.cookingrecipeswebmvc.entity.User;
import com.cookingrecipes.cookingrecipeswebmvc.service.AuthenticationService;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/auth")
@Slf4j
public class AuthenticationController {
	private final AuthenticationService authenticationService;

	@Autowired
	public AuthenticationController(AuthenticationService authenticationService) {
		this.authenticationService = authenticationService;
	}

	@GetMapping("/register")
	public String getOfferForm(Model model) {
		if (!model.containsAttribute("user")) {
			model.addAttribute("user", new User());
		}
		return "register";
	}

	@PostMapping("/register")
	public String registerUser(@ModelAttribute("user") User user) {
		authenticationService.register(user);
		return "redirect:login";
	}

	@GetMapping("/login")
	public String getLoginForm(Model model) {
		if (!model.containsAttribute("username")) {
			model.addAttribute("username", "");
		}
		if (!model.containsAttribute("password")) {
			model.addAttribute("password", "");
		}
		return "login";
	}

	@PostMapping("/login")
	public String login(@RequestParam("username") String username, @RequestParam("password") String password, HttpSession httpSession) {
		User user = authenticationService.login(username, password);

		if (user == null) {
			// nqkuv error
			return "redirect:login";
		}
		httpSession.setAttribute("user", user);
		return "redirect:/";
	}

	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}

	@GetMapping("/")
	public String welcome() {
		return "index";
	}
}
