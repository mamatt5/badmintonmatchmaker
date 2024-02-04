package com.fdmgroup.MattBadmintonMatchmaker.service;

import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;

import com.fdmgroup.MattBadmintonMatchmaker.model.Player;

@Service
public class MatchmakerService {

	public MatchmakerService() {
		super();
	}
	
	public Player[][] generateEvenGames(List<Player> players) {
		List<Player> playersNotMatched = players;
		int numMatches = Math.floorDiv(players.size(), 4);
//		System.out.println("Matches to be made: " + numMatches);
		Player[][] matches = new Player[numMatches][4];
		
		
		for (int i = 0; i < numMatches ; i++ ) {
			Collections.shuffle(playersNotMatched);
			if (playersNotMatched.size() >= 4) {
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
//				System.out.println("Players left: " + playersNotMatched.size());
			}
		}
		
		return matches;
	}
	
	public Player getEvenPlayer(Player player, List<Player> playerList) {
		
		for(Player checkPlayer : playerList) {
			if ( Math.abs(getPlayerSkillLevel(checkPlayer)-getPlayerSkillLevel(player)) <= 1) {
				return checkPlayer;
			}
		} return null;
	}
	
	
	public int getPlayerSkillLevel(Player player) {
		return player.getBracket().getId();
	}
}


