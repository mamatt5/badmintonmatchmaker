package com.fdmgroup.MattBadmintonMatchmaker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.fdmgroup.MattBadmintonMatchmaker.model.Player;
import com.fdmgroup.MattBadmintonMatchmaker.service.PlayerService;

@RestController
public class PlayerController {
	private PlayerService playerService;

	@Autowired
	public PlayerController(PlayerService playerService) {
		super();
		this.playerService = playerService;
	}

	@GetMapping("players")
	public List<Player> getPlayers() {
		return playerService.findAllPlayers();
	}
	
	@GetMapping("players/{playerId}")
	public Player findById(@PathVariable int playerId) {
		return playerService.findById(playerId);
	}

}
