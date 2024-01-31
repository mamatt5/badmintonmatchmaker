package com.fdmgroup.MattBadmintonMatchmaker.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.MattBadmintonMatchmaker.dal.GameRepository;
import com.fdmgroup.MattBadmintonMatchmaker.model.Game;

@Service
public class GameService {
	private GameRepository gameRepository;

	@Autowired
	public GameService(GameRepository gameRepository) {
		super();
		this.gameRepository = gameRepository;
	}

	public List<Game> findAll() {
		return this.gameRepository.findAll();
	}

	public Game findById(int gameId) {
		return this.gameRepository.findById(gameId).orElseThrow(()-> new RuntimeException("No game with id: " +gameId));
	}

	public void save(Game newGame) {
		if (this.gameRepository.existsById(newGame.getId())) {
			throw new RuntimeException("Game already exists");

		} else {
			this.gameRepository.save(newGame);
		}
	}

	public void deleteById(int gameId) {
		if (this.gameRepository.existsById(gameId)) {
			gameRepository.deleteById(gameId);

		} else {
			throw new RuntimeException("Game does not exist");
		}
	}



}
