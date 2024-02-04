package com.fdmgroup.MattBadmintonMatchmaker.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

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
		// 
	}

	
}
