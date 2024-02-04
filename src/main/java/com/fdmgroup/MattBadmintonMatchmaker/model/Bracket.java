package com.fdmgroup.MattBadmintonMatchmaker.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Bracket {
	
	@Id
	private int bracket_id; // should be final
	private char category; // should be final
	
	public int getId() {
		return bracket_id;
	}

	public void setId(int id) {
		this.bracket_id = id;
	}

	public char getCategory() {
		return category;
	}

	public void setCategory(char category) {
		this.category = category;
	}

	public Bracket(int id, char category) {
		super();
		this.bracket_id = id;
		this.category = category;
	}
	
	public Bracket() {
		super();
		// 
	}

	@Override
	public String toString() {
		return "Bracket [id=" + bracket_id + ", category=" + category + "]";
	}
	
	
}
