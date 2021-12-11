package com.cookingrecipes.cookingrecipeswebmvc.domain;

import com.cookingrecipes.cookingrecipeswebmvc.entity.Recipe;

import java.util.List;

public interface RecipeService {
    List<Recipe> findAll();
    Recipe findById(String id);
    Recipe findByName(String name);
    Recipe create(Recipe recipe);
    Recipe update(Recipe recipe);
    Recipe deleteById(String id);
    long count();
}
