package com.cookingrecipes.cookingrecipeswebmvc.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cookingrecipes.cookingrecipeswebmvc.entity.User;
import com.cookingrecipes.cookingrecipeswebmvc.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/users")
@Slf4j
public class UserController {
	private final UserService userService;

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping
	public String getUsers(Model model) {
		model.addAttribute("users", userService.findAll());
		log.debug("GET: Users: {}", userService.findAll());
		return "users";
	}

	@PostMapping(params = "delete")
	public String deleteUser(@RequestParam("delete") String id) {
		User user = userService.findById(id);
		log.debug("DELETE: Recipe: {}", user);
		userService.deleteById(id);
		return "redirect:/recipes";
	}
}
