package com.cookingrecipes.cookingrecipeswebmvc.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cookingrecipes.cookingrecipeswebmvc.dao.RecipeRepository;
import com.cookingrecipes.cookingrecipeswebmvc.dao.UserRepository;
import com.cookingrecipes.cookingrecipeswebmvc.entity.Recipe;
import com.cookingrecipes.cookingrecipeswebmvc.entity.User;
import com.cookingrecipes.cookingrecipeswebmvc.exception.EntityNotFoundException;
import com.cookingrecipes.cookingrecipeswebmvc.service.RecipeService;

@Service
public class RecipeServiceImpl implements RecipeService {
	private final UserRepository userRepository;
	private final RecipeRepository recipeRepository;

	@Autowired
	public RecipeServiceImpl(UserRepository userRepository, RecipeRepository recipeRepository) {
		this.userRepository = userRepository;
		this.recipeRepository = recipeRepository;
	}

	@Override
	public List<Recipe> findAll() {
		List<Recipe> recipes = recipeRepository.findAll();
		recipes.forEach(recipe -> recipe
				.setAuthorName(userRepository.findById(recipe.getAuthorId()).orElseGet(() -> new User("", "", "")).getName()));
		return recipes;
	}

	@Override
	public Recipe findById(String id) {
		Recipe found = recipeRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException(String.format("Recipe with ID=%s not found.", id)));
		found.setAuthorName(userRepository.findById(found.getAuthorId()).orElseGet(() -> new User("", "", "")).getName());
		return found;
	}

	@Override
	public Recipe findByName(String name) {
		Recipe found = recipeRepository.findByName(name)
				.orElseThrow(() -> new EntityNotFoundException(String.format("Recipe with ID=%s not found.", name)));
		found.setAuthorName(userRepository.findById(found.getAuthorId()).orElseGet(() -> new User("", "", "")).getName());
		return found;
	}

	@Override
	public Recipe create(Recipe recipe) {
		recipe.setId(null);
		recipe.setCreated(LocalDateTime.now());
		recipe.setModified(LocalDateTime.now());
		return recipeRepository.insert(recipe);
	}

	@Override
	public Recipe update(Recipe recipe) {
		Recipe oldRecipe = findById(recipe.getId());
		recipe.setCreated(oldRecipe.getCreated());
		recipe.setModified(LocalDateTime.now());
		return recipeRepository.save(recipe);
	}

	@Override
	public Recipe deleteById(String id) {
		Recipe oldRecipe = findById(id);
		recipeRepository.deleteById(id);
		return oldRecipe;
	}

	@Override
	public long count() {
		return recipeRepository.count();
	}
}
