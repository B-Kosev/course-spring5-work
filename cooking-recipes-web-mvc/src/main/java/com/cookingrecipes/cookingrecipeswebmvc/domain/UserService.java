package com.cookingrecipes.cookingrecipeswebmvc.domain;

import com.cookingrecipes.cookingrecipeswebmvc.entity.User;

import java.util.List;

public interface UserService {
    List<User> findAll();
    User findById(String id);
    User findByName(String name);
    User create(User user);
    User update(User user);
    User deleteById(String id);
    long count();
}
