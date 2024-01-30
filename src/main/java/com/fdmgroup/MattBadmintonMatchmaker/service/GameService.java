package com.fdmgroup.MattBadmintonMatchmaker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.MattBadmintonMatchmaker.dal.GameRepository;

@Service
public class GameService {
	private GameRepository gameRepository;

	@Autowired
	public GameService(GameRepository gameRepository) {
		super();
		this.gameRepository = gameRepository;
	}
	
	

}
