package com.fdmgroup.MattBadmintonMatchmaker.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.MattBadmintonMatchmaker.dal.UserRepository;
import com.fdmgroup.MattBadmintonMatchmaker.model.User;

@Service
public class UserService {
	private UserRepository userRepository;

	@Autowired
	public UserService(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}

	public List<User> findAllUsers() {
		return this.userRepository.findAll();
	}

	public User findUsername(String username) {
		return this.userRepository.findById(username).orElseThrow(()-> new RuntimeException("Username not found"));
	}

	public void save(User newUser) {
		if (this.userRepository.existsById(newUser.getUsername())) {
			throw new RuntimeException("Username already exists");
			
		} else {
			this.userRepository.save(newUser);
		}
	}

	public void deleteByUsername(String username) {
		
		if (this.userRepository.existsById(username)) {
			userRepository.deleteById(username);
			
		} else {
			throw new RuntimeException("Username does not exist");
		}
		
	}

	public void update(User newUser) {
		if (this.userRepository.existsById(newUser.getUsername())) {
			this.userRepository.save(newUser);
			
		} else {
			throw new RuntimeException("Invalid Username");
			
		}
	}
	
	

}
