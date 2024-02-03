package com.fdmgroup.MattBadmintonMatchmaker.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.MattBadmintonMatchmaker.dal.PlayerRepository;
import com.fdmgroup.MattBadmintonMatchmaker.exceptions.DuplicateException;
import com.fdmgroup.MattBadmintonMatchmaker.exceptions.NotFoundException;
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
		return this.playerRepository.findById(playerId).orElseThrow(()-> new NotFoundException("No player with id: " +playerId));
	}

	public void save(Player newPlayer) {
		if (this.playerRepository.existsById(newPlayer.getId())) {
			throw new DuplicateException("Player already exists");
			
		} else {
			this.playerRepository.save(newPlayer);
		}
	}

	public void deleteById(int playerId) {
		if (this.playerRepository.existsById(playerId)) {
			playerRepository.deleteById(playerId);
		} else {
			throw new NotFoundException("Player does not exist");
		}
		
		
	}

	public void update(Player newPlayer) {
		if (this.playerRepository.existsById(newPlayer.getId())) {
			this.playerRepository.save(newPlayer);
			
		} else {
			throw new NotFoundException("Player does not exist");
		}
		
	}



}
