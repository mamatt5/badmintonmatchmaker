package com.fdmgroup.MattBadmintonMatchmaker.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;

import com.fdmgroup.MattBadmintonMatchmaker.model.Player;

/**
 * This service handles the generation of games with players of near equal skill levels. It works by returning
 * a 2D array of a list of player lists which would then be grabbed by the game controller and consequently
 * save the games. The algorithm works so that it constantly shuffles the player list to ensure randomization.
 * It would then get the skill level of the first player in the list then searches through the list for the first
 * player that is of equal skill level. If none, checks for players with one level down/above. If none, just gets
 * the next player in the list.
 */

@Service
public class MatchmakerService {

	public MatchmakerService() {
		super();
	}
	
	public Player[][] generateEvenGames(List<Player> players) {
		
		// Initializes an array containing the list of players
		List<Player> playersNotMatched = new ArrayList<Player>(players);
		
		// Presets how many games would be generated based on the number of players
		int numMatches = Math.floorDiv(players.size(), 4);
		Player[][] matches = new Player[numMatches][4];
		
		
		for (int i = 0; i < numMatches ; i++ ) {
			
			Collections.shuffle(playersNotMatched);
			
			// For every player being matched, it gets removed from the playersNotMatched list until the list
			// has less than four players left
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
			}
		}
		
		return matches;
	}
	
	/**
	 * First gets player of equal level, then one level difference. Otherwise returns null
	 * @param player to be matched
	 * @param playerList
	 * @return player matched
	 */
	public Player getEvenPlayer(Player player, List<Player> playerList) {
		
		for(Player checkPlayer : playerList) {
			if ( Math.abs(getPlayerSkillLevel(checkPlayer)-getPlayerSkillLevel(player)) == 0) {
				return checkPlayer;
			}
		}
		
		for(Player checkPlayer : playerList) {
			if ( Math.abs(getPlayerSkillLevel(checkPlayer)-getPlayerSkillLevel(player)) == 1) {
				return checkPlayer;
			}
		}
		
		return null;
	}
	
	/**
	 * Gets the skill bracket of the player
	 * @param player
	 * @return skill level of the player
	 */
	public int getPlayerSkillLevel(Player player) {
		return player.getBracket().getId();
	}
}


