package com.example.mysqldemo.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.mysqldemo.entity.User;
import com.example.mysqldemo.service.IUserService;

@RestController
public class UserController {
	@Autowired
	private IUserService userService;

	@PostMapping("user")
	int createUser(@RequestBody @Valid User user,BindingResult bindingResult) {
		validateModel(bindingResult);
		return userService.save(user);
	}

	/**
	 * method to get all users in db
	 * 
	 * @return all users
	 */

	@GetMapping("user")
	List<User> getUsers() {
		return userService.getUsers();
	}

	/**
	 * method to get user by id
	 * 
	 * @param id
	 * @return null or matching id
	 */

	@GetMapping("user/{id}")
	Optional<User> getUser(@PathVariable("id") Integer id) {
		return userService.getUser(id);
	}

	/**
	 * method to update user
	 * 
	 * @param user
	 * @param userId
	 */

	@PutMapping("user/{id}")
	void updateUser(@RequestBody User user,BindingResult bindingResult, @PathVariable("id") Integer userId) {
		validateModel(bindingResult);
		user.setId(userId);
		userService.updateUser(user);
	}

	/**
	 * method to delete user
	 * 
	 * @param userId
	 */

	@DeleteMapping("user/{id}")
	void deleteUser(@PathVariable("id") Integer userId) {
		userService.deleteUser(userId);
	}

	private void validateModel(Errors bindingResult) {
		if (bindingResult.hasErrors()) {
			throw new IllegalArgumentException("Something went wrong");
		}
	}

}
