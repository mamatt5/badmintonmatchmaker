package com.fdmgroup.MattBadmintonMatchmaker.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

@Entity
public class SocialSession {
	
	@Id
	@GeneratedValue
	private int session_id;
	
	private LocalDate date;
	private int numberCourts;
	
	@ManyToOne
	private Place place;
	
	@ManyToMany
	@JoinTable(name = "player_session",
	joinColumns = 
	@JoinColumn(name = "fk_session_id"),
	inverseJoinColumns = 
	@JoinColumn(name = "fk_player_id1"))
	private List<Player> players;

	public int getId() {
		return session_id;
	}

	public void setId(int id) {
		this.session_id = id;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public int getNumberCourts() {
		return numberCourts;
	}

	public void setNumberCourts(int numberCourts) {
		this.numberCourts = numberCourts;
	}

	public Place getPlace() {
		return place;
	}

	public void setPlace(Place place) {
		this.place = place;
	}

	public List<Player> getPlayers() {
		return players;
	}

	public void setPlayers(ArrayList<Player> players) {
		this.players = players;
	}

	public SocialSession(LocalDate date, int numberCourts, Place place) {
		super();
		this.date = date;
		this.numberCourts = numberCourts;
		this.place = place;
		this.players = new ArrayList<Player>();
	}

	public SocialSession() {
		super();
		// 
	}

	@Override
	public String toString() {
		return "SocialSession [id=" + session_id + ", date=" + date + ", numberCourts=" + numberCourts + ", place=" + place
				+ ", players=" + players + "]";
	}
	
	
}
