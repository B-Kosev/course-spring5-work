package com.cookingrecipes.cookingrecipeswebmvc.service;

import java.util.List;

import com.cookingrecipes.cookingrecipeswebmvc.entity.User;

public interface UserService {
	List<User> findAll();

	User findById(String id);

	User findByName(String name);

	User findByUsername(String username);

	User create(User user);

	User update(User user);

	User deleteById(String id);

	long count();
}
