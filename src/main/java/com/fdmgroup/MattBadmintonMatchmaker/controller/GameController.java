package com.fdmgroup.MattBadmintonMatchmaker.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fdmgroup.MattBadmintonMatchmaker.model.Game;
import com.fdmgroup.MattBadmintonMatchmaker.model.Player;
import com.fdmgroup.MattBadmintonMatchmaker.model.SocialSession;
import com.fdmgroup.MattBadmintonMatchmaker.service.GameService;
import com.fdmgroup.MattBadmintonMatchmaker.service.MatchmakerService;
import com.fdmgroup.MattBadmintonMatchmaker.service.SocialSessionService;

@RestController
public class GameController {
	private GameService gameService;
	private MatchmakerService matchMakerService;
	private SocialSessionService socialSessionService;

	@Autowired
	public GameController(GameService gameService, MatchmakerService matchMakerService,
			SocialSessionService socialSessionService) {
		super();
		this.gameService = gameService;
		this.matchMakerService = matchMakerService;
		this.socialSessionService = socialSessionService;
	}
	
	public GameController(GameService gameService) {
		super();
		this.gameService = gameService;
	}

	@GetMapping("games")
	public List<Game> getGames() {
		return gameService.findAll();
	}
	
	@GetMapping("games/{gameId}")
	public Game findById(@PathVariable int gameId) {
		return gameService.findById(gameId);
	}
	
	@GetMapping("sessions/{sessionId}/games")
	public List<Game> findBySessionId(@PathVariable int sessionId) {
		return gameService.findBySessionId(sessionId);
	}
	
	@PostMapping("games")
	public Game createNew(@RequestBody Game newGame) {
		gameService.save(newGame);
		return gameService.findById(newGame.getId());
	}
	
	@PutMapping("games")
	public Game updateGame(@RequestBody Game newGame) {
		gameService.update(newGame);
		return gameService.findById(newGame.getId());
	}
	
	@DeleteMapping("games/{gameId}")
	public void deleteGame(@PathVariable int gameId) {
		gameService.deleteById(gameId);
	}
	
	// Get players from social session first
	// Generate 2D array through matchmaker service
	// Save games through for-loop with session set at session
	@PostMapping("games/generategames/{sessionId}")
	public void generateGamesForSession(@PathVariable int sessionId) {
		SocialSession session = socialSessionService.findById(sessionId);
		List<Player> players = session.getPlayers();
		
		Player[][] gameList = matchMakerService.generateEvenGames(players);
		for (Player[] playersToMatch : gameList) {
			Game game = new Game(Arrays.asList(playersToMatch), session);
			gameService.save(game);
		}
	}

}
