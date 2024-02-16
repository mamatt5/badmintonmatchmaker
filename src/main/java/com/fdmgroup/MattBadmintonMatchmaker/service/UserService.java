package com.fdmgroup.MattBadmintonMatchmaker.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.fdmgroup.MattBadmintonMatchmaker.dal.UserRepository;
import com.fdmgroup.MattBadmintonMatchmaker.exceptions.DuplicateException;
import com.fdmgroup.MattBadmintonMatchmaker.exceptions.NotFoundException;
import com.fdmgroup.MattBadmintonMatchmaker.model.User;

@Service
public class UserService {
	private UserRepository userRepository;
	private PasswordEncoder encoder;
	
	@Autowired
	public UserService(UserRepository userRepository, PasswordEncoder encoder) {
		super();
		this.userRepository = userRepository;
		this.encoder = encoder;
	}
	

	public List<User> findAllUsers() {
		return this.userRepository.findAll();
	}

	public User findUsername(String username) {
		return this.userRepository.findById(username).orElseThrow(()-> new NotFoundException("Username not found"));
	}
	
	public void register(User newUser) {
		if (this.userRepository.existsById(newUser.getUsername())) {
			throw new DuplicateException("Username already exists");
			
		} else {
			newUser.setPassword(encoder.encode(newUser.getPassword()));
			this.userRepository.save(newUser);
		}
	}

	public void deleteByUsername(String username) {
		
		if (this.userRepository.existsById(username)) {
			userRepository.deleteById(username);
			
		} else {
			throw new NotFoundException("Username does not exist");
		}
		
	}

	public void update(User newUser) {
		if (this.userRepository.existsById(newUser.getUsername())) {
			this.userRepository.save(newUser);
			
		} else {
			throw new NotFoundException("Username does not exist");
			
		}
	}
	
	

}
