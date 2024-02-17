package com.fdmgroup.MattBadmintonMatchmaker.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

/**
 * This entity holds the user information. This is what would be used for logging in and getting access to the
 * whole application. Not yet implemented in the application is having a player entity integrated into the user
 * so that in the future, users can only edit their own player information. This also means that admin and normal
 * user privileges must be implemented. In this version, all registered users are allowed to do everything but
 * in future deployments should be restricted.
 */

@Entity
public class User {
	@Id
	private String username;
	private String password;
	private boolean adminAccess;
	
	@OneToOne
	private Player player;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isAdminAccess() {
		return adminAccess;
	}

	public void setAdminAccess(boolean adminAccess) {
		this.adminAccess = adminAccess;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public User(String username, String password, boolean adminAccess) {
		super();
		this.username = username;
		this.password = password;
		this.adminAccess = adminAccess;
	}

	public User() {
		super();
	}

	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + ", adminAccess=" + adminAccess + ", player="
				+ player + "]";
	}

	public String getRole() {
		if (this.adminAccess) {
			return "ADMIN";
			
		}else {
			return "USER";
		}
		
	}
	
	

}
