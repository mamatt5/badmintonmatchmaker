package com.fdmgroup.MattBadmintonMatchmaker.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.MattBadmintonMatchmaker.dal.GameRepository;
import com.fdmgroup.MattBadmintonMatchmaker.dal.SocialSessionRepository;
import com.fdmgroup.MattBadmintonMatchmaker.exceptions.DuplicateException;
import com.fdmgroup.MattBadmintonMatchmaker.exceptions.NotFoundException;
import com.fdmgroup.MattBadmintonMatchmaker.model.Game;
import com.fdmgroup.MattBadmintonMatchmaker.model.SocialSession;


// update UML, add socialsession repo

@Service
public class GameService {
	private GameRepository gameRepository;
	private SocialSessionRepository socialSessionRepository;

	@Autowired
	public GameService(GameRepository gameRepository, SocialSessionRepository socialSessionRepository) {
		super();
		this.gameRepository = gameRepository;
		this.socialSessionRepository = socialSessionRepository;
	}

	public List<Game> findAll() {
		return this.gameRepository.findAll();
	}

	public Game findById(int gameId) {
		return this.gameRepository.findById(gameId).orElseThrow(()-> new NotFoundException("No game with id: " +gameId));
	}
	
	public List<Game> findBySessionId(int sessionId) {
		SocialSession session = this.socialSessionRepository.findById(sessionId)
				.orElseThrow(()-> new NotFoundException("Social Session not found"));
		
		List<Game> allGames = this.gameRepository.findAll();
		List<Game> sessionGames = new ArrayList<Game>();
		
		for (Game game : allGames) {
			if (game.getSocialSession() == session) {
				sessionGames.add(game);
			}
		}
		
		return sessionGames;
	}

	public void save(Game newGame) {
		if (this.gameRepository.existsById(newGame.getId())) {
			throw new DuplicateException("Game already exists");

		} else {
			this.gameRepository.save(newGame);
		}
	}

	public void deleteById(int gameId) {
		if (this.gameRepository.existsById(gameId)) {
			gameRepository.deleteById(gameId);

		} else {
			throw new NotFoundException("Game does not exist");
		}
	}

	public void update(Game newGame) {
		if (this.gameRepository.existsById(newGame.getId())) {
			this.gameRepository.save(newGame);
			

		} else {
			throw new NotFoundException("Game does not exist");
		}
	}

}
