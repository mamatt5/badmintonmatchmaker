package com.fdmgroup.MattBadmintonMatchmaker.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

/**
 * This entity essentially holds the label for the social session entity. It restricts user
 * input to only assign a social session to places/events registered in the database for easier
 * filtering of events in the future.
 */

@Entity
public class Place {
	
	@Id
	@GeneratedValue
	private int place_id;
	private String placeName;
	
	public int getId() {
		return place_id;
	}
	public void setId(int id) {
		this.place_id = id;
	}
	public String getPlaceName() {
		return placeName;
	}
	public void setPlaceName(String placeName) {
		this.placeName = placeName;
	}
	
	public Place(String placeName) {
		super();
		this.placeName = placeName;
	}
	public Place() {
		super();
	}

	
}
