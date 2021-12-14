package com.cookingrecipes.cookingrecipeswebmvc.service;

import java.util.List;

import com.cookingrecipes.cookingrecipeswebmvc.entity.Recipe;

public interface RecipeService {
	List<Recipe> findAll();

	Recipe findById(String id);

	Recipe findByName(String name);

	Recipe create(Recipe recipe);

	Recipe update(Recipe recipe);

	Recipe deleteById(String id);

	long count();
}
