package com.fdmgroup.MattBadmintonMatchmaker.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Player {
	@Id
	@GeneratedValue
	private int player_id;
	
	private String firstName;
	private String lastName;
	
	@ManyToOne
	@JoinColumn(name = "bracket_id")
	private Bracket bracket;
	
	private String beemIt;

	public int getId() {
		return player_id;
	}

	public void setId(int id) {
		this.player_id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Bracket getBracket() {
		return bracket;
	}

	public void setBracket(Bracket bracket) {
		this.bracket = bracket;
	}

	public String getBeemIt() {
		return beemIt;
	}

	public void setBeemIt(String beemIt) {
		this.beemIt = beemIt;
	}

	public Player(String firstName, String lastName, Bracket bracket) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.bracket = bracket;
	}

	public Player() {
		super();
		// 
	}

	@Override
	public String toString() {
		return "Player [id=" + player_id + ", firstName=" + firstName + ", lastName=" + lastName + ", bracket=" + bracket
				+ ", beemIt=" + beemIt + "]";
	}

	
	
}
