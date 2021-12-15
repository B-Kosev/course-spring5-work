package com.cookingrecipes.cookingrecipeswebmvc.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cookingrecipes.cookingrecipeswebmvc.entity.Recipe;
import com.cookingrecipes.cookingrecipeswebmvc.service.RecipeService;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/recipes")
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
		log.debug("GET: Recipes: {}", recipeService.findAll());
		return "recipes";
	}

	@PostMapping(params = "delete")
	public String deleteRecipe(@RequestParam("delete") String id) {
		Recipe recipe = recipeService.findById(id);
		log.debug("DELETE: Recipe: {}", recipe);
		recipeService.deleteById(id);
		return "redirect:/recipes";
	}
}
