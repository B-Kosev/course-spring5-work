package com.cookingrecipes.cookingrecipeswebmvc.init;

import com.cookingrecipes.cookingrecipeswebmvc.domain.RecipeService;
import com.cookingrecipes.cookingrecipeswebmvc.domain.UserService;
import com.cookingrecipes.cookingrecipeswebmvc.entity.Recipe;
import com.cookingrecipes.cookingrecipeswebmvc.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@Slf4j
public class DataInitializer implements ApplicationRunner {
    private final UserService userService;
    private final RecipeService recipeService;

    @Autowired
    public DataInitializer(UserService userService, RecipeService recipeService) {
        this.userService = userService;
        this.recipeService = recipeService;
    }

    private final List<User> DEFAULT_USERS = List.of(
            new User("Admin", "admin", "Admin1!"),
            new User("User", "user", "User1!")
    );

    private final List<Recipe> DEFAULT_RECIPES = List.of(
            new Recipe("Recipe 1", "Sample description for recipe 1", 10, List.of("Product 1", "Product 2")),
            new Recipe("Recipe 2", "Sample description for recipe 2", 10, List.of("Product 3", "Product 4"))
    );

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if (userService.count() == 0) {
            log.info("Successfully created users: {}",
                    DEFAULT_USERS.stream().map(userService::create).collect(Collectors.toList()));
        }

        if (recipeService.count() == 0) {
            List<User> users = userService.findAll();
            if (users.size() > 0) {
                User author = users.get(0);
                log.info("Successfully created recipes: {}",
                        DEFAULT_RECIPES.stream()
                                .peek(recipe -> recipe.setAuthorId(author.getId()))
                                .map(recipeService::create).collect(Collectors.toList()));
            }
        }
    }
}
