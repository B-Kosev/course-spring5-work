package com.cookingrecipes.cookingrecipeswebmvc.dao;

import com.cookingrecipes.cookingrecipeswebmvc.entity.Recipe;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface RecipeRepository extends MongoRepository<Recipe, String> {
    Optional<Recipe> findByName(String name);
}
