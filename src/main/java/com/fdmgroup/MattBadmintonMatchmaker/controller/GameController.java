package com.fdmgroup.MattBadmintonMatchmaker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fdmgroup.MattBadmintonMatchmaker.model.Game;
import com.fdmgroup.MattBadmintonMatchmaker.service.GameService;

@RestController
public class GameController {
	private GameService gameService;

	@Autowired
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
	
	@PostMapping("games")
	public Game createNew(@RequestBody Game newGame) {
		gameService.save(newGame);
		return gameService.findById(newGame.getId());
	}

}
