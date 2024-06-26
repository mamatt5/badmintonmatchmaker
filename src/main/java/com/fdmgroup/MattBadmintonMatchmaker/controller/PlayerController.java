package com.fdmgroup.MattBadmintonMatchmaker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

	@PostMapping("players")
	public Player createNewPlayer(@RequestBody Player newPlayer) {
		playerService.save(newPlayer);
		return playerService.findById(newPlayer.getId());
	}
	
	@PutMapping("players")
	public Player updatePlayer(@RequestBody Player newPlayer) {
		playerService.update(newPlayer);
		return playerService.findById(newPlayer.getId());
	}
	
	@DeleteMapping("players/{playerId}")
	public void deletePlayer(@PathVariable int playerId) {
		playerService.deleteById(playerId);
	}
}
