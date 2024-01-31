package com.fdmgroup.MattBadmintonMatchmaker.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.MattBadmintonMatchmaker.dal.BracketRepository;
import com.fdmgroup.MattBadmintonMatchmaker.model.Bracket;

@Service
public class BracketService {
	private BracketRepository bracketRepository;

	@Autowired
	public BracketService(BracketRepository bracketRepository) {
		super();
		this.bracketRepository = bracketRepository;
	}

	public List<Bracket> findAll() {
		return this.bracketRepository.findAll();
	}
	
	

}
