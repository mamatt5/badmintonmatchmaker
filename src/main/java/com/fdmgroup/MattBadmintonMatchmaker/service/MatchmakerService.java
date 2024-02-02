package com.fdmgroup.MattBadmintonMatchmaker.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;

import com.fdmgroup.MattBadmintonMatchmaker.dal.GameRepository;
import com.fdmgroup.MattBadmintonMatchmaker.dal.PlayerRepository;
import com.fdmgroup.MattBadmintonMatchmaker.dal.SocialSessionRepository;
import com.fdmgroup.MattBadmintonMatchmaker.model.Player;

@Service
public class MatchmakerService {
//	private PlayerRepository playerRepository;
//	private GameRepository gameRepository;
//	private SocialSessionRepository socialSessionRepository;
//	
//	
//	public MatchmakerService(PlayerRepository playerRepository, GameRepository gameRepository,
//			SocialSessionRepository socialSessionRepository) {
//		super();
//		this.playerRepository = playerRepository;
//		this.gameRepository = gameRepository;
//		this.socialSessionRepository = socialSessionRepository;
//	}
	
	public MatchmakerService() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	// GENERATE MATCHES HERE
	public Player[][] generateEvenGames(List<Player> players) {
		List<Player> playersNotMatched = players;
		int numMatches = Math.floorDiv(players.size(), 4);
		System.out.println("Matches to be made: " + numMatches);
		Player[][] matches = new Player[numMatches][4];
		
		
		for (int i = 0; i < numMatches ; i++ ) {
			Collections.shuffle(playersNotMatched);
			matches[i][0] = playersNotMatched.get(0);
			playersNotMatched.remove(matches[i][0]);
			
			matches[i][1] = (getEvenPlayer(matches[i][0], playersNotMatched) != null) ? 
					getEvenPlayer(matches[i][0], playersNotMatched) : playersNotMatched.get(0);
			playersNotMatched.remove(matches[i][1]);
			
			matches[i][2] = (getEvenPlayer(matches[i][1], playersNotMatched) != null) ? 
					getEvenPlayer(matches[i][1], playersNotMatched) : playersNotMatched.get(0);
			playersNotMatched.remove(matches[i][2]);
			
			matches[i][3] = (getEvenPlayer(matches[i][2], playersNotMatched) != null) ? 
					getEvenPlayer(matches[i][2], playersNotMatched) : playersNotMatched.get(0);
			playersNotMatched.remove(matches[i][3]);
			System.out.println("Players left: " + playersNotMatched.size());

		}
		
		return matches;
	}
	
	private Player getEvenPlayer(Player player, List<Player> playerList) {
		
		for(Player checkPlayer : playerList) {
			if ( Math.abs(getPlayerSkillLevel(checkPlayer)-getPlayerSkillLevel(player)) <= 1) {
				return checkPlayer;
			}
		} return null;
	}
	
	
	private int getPlayerSkillLevel(Player player) {
		return player.getBracket().getId();
	}
}


