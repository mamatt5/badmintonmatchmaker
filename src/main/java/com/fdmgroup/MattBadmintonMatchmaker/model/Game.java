package com.fdmgroup.MattBadmintonMatchmaker.model;


import java.util.List;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

@Entity
public class Game {
	
	@Id
	@GeneratedValue
	private int game_id;
	
	@ManyToMany
	@JoinTable(name = "game_player",
	joinColumns = 
	@JoinColumn(name = "fk_game_id"),
	inverseJoinColumns = 
	@JoinColumn(name = "fk_player_id"))
	private List<Player> players;
	
	@ManyToMany
	@JoinTable(name = "game_winner",
	joinColumns = 
	@JoinColumn(name = "fk_game_id"),
	inverseJoinColumns = 
	@JoinColumn(name = "fk_winner_id"))
	private List<Player> winners;
	
	private int loseScore;
	
	
	@ManyToOne
	@JoinColumn(name = "social_session_id")
	private SocialSession socialSession;

	public int getId() {
		return game_id;
	}

	public void setId(int id) {
		this.game_id = id;
	}

	public List<Player> getPlayers() {
		return players;
	}

	public void setPlayers(List<Player> players) {
		this.players = players;
	}

	public List<Player> getWinners() {
		return winners;
	}

	public void setWinners(List<Player> winners) {
		this.winners = winners;
	}

	public int getLoseScore() {
		return loseScore;
	}

	public void setLoseScore(int loseScore) {
		this.loseScore = loseScore;
	}

	public SocialSession getSocialSession() {
		return socialSession;
	}

	public void setSocialSession(SocialSession socialSession) {
		this.socialSession = socialSession;
	}

	public Game(List<Player> list, SocialSession socialSession) {
		super();
		this.players = list;
		this.socialSession = socialSession;
	}

	public Game() {
		super();
		// 
	}

	@Override
	public String toString() {
		return "Game [id=" + game_id + ", players=" + players + ", winners=" + winners + ", loseScore=" + loseScore
				+ ", socialSession=" + socialSession + "]";
	}

	
}
