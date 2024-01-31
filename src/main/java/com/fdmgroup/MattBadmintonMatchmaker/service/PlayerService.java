package com.fdmgroup.MattBadmintonMatchmaker.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.MattBadmintonMatchmaker.dal.PlayerRepository;
import com.fdmgroup.MattBadmintonMatchmaker.model.Player;

@Service
public class PlayerService {
	private PlayerRepository playerRepository;

	@Autowired
	public PlayerService(PlayerRepository playerRepository) {
		super();
		this.playerRepository = playerRepository;
	}

	public List<Player> findAllPlayers() {
		return this.playerRepository.findAll();
	}

	public Player findById(int playerId) {
		return this.playerRepository.findById(playerId).orElseThrow(()-> new RuntimeException("No player with id: " +playerId));
	}

	public void save(Player newPlayer) {
		this.playerRepository.save(newPlayer);
		
	}



}
