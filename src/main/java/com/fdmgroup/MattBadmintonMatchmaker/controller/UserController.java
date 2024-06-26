package com.fdmgroup.MattBadmintonMatchmaker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fdmgroup.MattBadmintonMatchmaker.model.User;
import com.fdmgroup.MattBadmintonMatchmaker.service.UserService;

@RestController
public class UserController {
	private UserService userService;

	@Autowired
	public UserController(UserService userService) {
		super();
		this.userService = userService;
	}
	
	@GetMapping("users")
	public List<User> getUsers() {
		return userService.findAllUsers();
	}
	
	@GetMapping("users/{username}")
	public User findUsername(@PathVariable String username) {
		return userService.findUsername(username);
	}
	
	@PostMapping("users")
	public User createNewUser(@RequestBody User newUser) {
		userService.register(newUser);
		return userService.findUsername(newUser.getUsername());
	}
	
	@PutMapping("users")
	public User updateUser(@RequestBody User newUser) {
		userService.update(newUser);
		return userService.findUsername(newUser.getUsername());
	}
	
	@DeleteMapping("users/{username}")
	public void deleteByUsername(@PathVariable String username) {
		userService.deleteByUsername(username);
	}

}
