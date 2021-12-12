package com.cookingrecipes.cookingrecipeswebmvc.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cookingrecipes.cookingrecipeswebmvc.domain.RecipeService;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/api/recipes")
@Slf4j
public class RecipeController {
	private final RecipeService recipeService;

	@Autowired
	public RecipeController(RecipeService recipeService) {
		this.recipeService = recipeService;
	}

	@GetMapping
	public String getRecipes(Model model) {
		model.addAttribute("recipes", recipeService.findAll());
		return "recipes";
	}
}
