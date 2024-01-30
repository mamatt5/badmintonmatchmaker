package com.fdmgroup.MattBadmintonMatchmaker.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fdmgroup.MattBadmintonMatchmaker.model.Player;

@RestController
public class MainController {
	
	@GetMapping("players")
	public List<Player> getPlayers() {
		return null;
	}
	
	

}
