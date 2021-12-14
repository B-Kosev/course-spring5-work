package com.cookingrecipes.cookingrecipeswebmvc.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cookingrecipes.cookingrecipeswebmvc.dao.UserRepository;
import com.cookingrecipes.cookingrecipeswebmvc.entity.User;
import com.cookingrecipes.cookingrecipeswebmvc.exception.EntityNotFoundException;
import com.cookingrecipes.cookingrecipeswebmvc.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	private final UserRepository userRepository;

	@Autowired
	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public List<User> findAll() {
		List<User> users = userRepository.findAll();
		users.forEach(user -> user.setPassword(""));
		return users;
	}

	@Override
	public User findById(String id) {
		User found = userRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException(String.format("User with ID=%s not found", id)));
		found.setPassword("");
		return found;
	}

	@Override
	public User findByName(String name) {
		User found = userRepository.findByName(name)
				.orElseThrow(() -> new EntityNotFoundException(String.format("User '%s' not found.", name)));
		found.setPassword("");
		return found;
	}

	@Override
	public User findByUsername(String username) {
		User found = userRepository.findByUsername(username)
				.orElseThrow(() -> new EntityNotFoundException(String.format("User '%s' not found.", username)));
		return found;
	}

	@Override
	public User create(User user) {
		user.setId(null);
		user.setCreated(LocalDateTime.now());
		user.setModified(LocalDateTime.now());
		PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		user.setPassword(encoder.encode(user.getPassword()));
		user.setActive(true);
		User created = userRepository.insert(user);
		created.setPassword("");
		return created;
	}

	@Override
	public User update(User user) {
		User oldUser = findById(user.getId());
		// prevent name changing
		user.setName(oldUser.getName());
		if (user.getPassword() == null || user.getPassword().length() == 0) {
			user.setPassword(oldUser.getPassword());
		} else {
			PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
			user.setPassword(encoder.encode(user.getPassword()));
		}
		user.setCreated(oldUser.getCreated());
		user.setModified(LocalDateTime.now());
		User updated = userRepository.save(user);
		updated.setPassword("");
		return user;
	}

	@Override
	public User deleteById(String id) {
		User oldUser = findById(id);
		userRepository.deleteById(id);
		oldUser.setPassword("");
		return oldUser;
	}

	@Override
	public long count() {
		return userRepository.count();
	}
}
