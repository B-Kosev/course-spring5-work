package com.cookingrecipes.cookingrecipeswebmvc.dao;

import com.cookingrecipes.cookingrecipeswebmvc.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByName(String name);
    Optional<User> findByUsername(String username);
}
