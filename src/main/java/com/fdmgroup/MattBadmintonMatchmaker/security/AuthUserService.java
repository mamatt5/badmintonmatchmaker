package com.fdmgroup.MattBadmintonMatchmaker.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.fdmgroup.MattBadmintonMatchmaker.dal.UserRepository;
import com.fdmgroup.MattBadmintonMatchmaker.model.User;

@Service
public class AuthUserService implements org.springframework.security.core.userdetails.UserDetailsService{
	private UserRepository userRepo;

	@Autowired
	public AuthUserService(UserRepository userRepo) {
		super();
		this.userRepo = userRepo;
	}

	@Override
	public org.springframework.security.core.userdetails.UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = this.userRepo.findById(username).orElseThrow(
				()-> new UsernameNotFoundException(username));
		return new AuthUser(user);
	}
	
	

}
