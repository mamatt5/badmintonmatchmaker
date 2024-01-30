package com.fdmgroup.MattBadmintonMatchmaker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.MattBadmintonMatchmaker.dal.BracketRepository;

@Service
public class BracketService {
	private BracketRepository bracketRepository;

	@Autowired
	public BracketService(BracketRepository bracketRepository) {
		super();
		this.bracketRepository = bracketRepository;
	}
	
	

}
