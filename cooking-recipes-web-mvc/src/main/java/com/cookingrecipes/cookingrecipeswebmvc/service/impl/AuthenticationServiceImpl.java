package com.cookingrecipes.cookingrecipeswebmvc.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cookingrecipes.cookingrecipeswebmvc.entity.Role;
import com.cookingrecipes.cookingrecipeswebmvc.entity.User;
import com.cookingrecipes.cookingrecipeswebmvc.exception.InvalidEntityException;
import com.cookingrecipes.cookingrecipeswebmvc.service.AuthenticationService;
import com.cookingrecipes.cookingrecipeswebmvc.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AuthenticationServiceImpl implements AuthenticationService {
	private final UserService userService;

	@Autowired
	public AuthenticationServiceImpl(UserService userService) {
		this.userService = userService;
	}

	@Override
	public User register(User user) {
		if (user.getRoles().contains(Role.ADMIN)) {
			throw new InvalidEntityException("Admins cannot be registered.");
		}
		return userService.create(user);
	}

	@Override
	public User login(String username, String password) {
		User user = userService.findByUsername(username);
		if (user.getPassword().equals(password)) {
			return user;
		}
		return null;
	}

}
