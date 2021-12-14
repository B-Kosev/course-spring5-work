package com.cookingrecipes.cookingrecipeswebmvc.service;

import com.cookingrecipes.cookingrecipeswebmvc.entity.User;

public interface AuthenticationService {
	User register(User user);

	User login(String username, String password);
}
