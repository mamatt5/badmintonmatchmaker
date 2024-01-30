package com.fdmgroup.MattBadmintonMatchmaker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.fdmgroup.MattBadmintonMatchmaker.service.BracketService;

@RestController
public class BracketController {
	private BracketService bracketService;

	@Autowired
	public BracketController(BracketService bracketService) {
		super();
		this.bracketService = bracketService;
	}
	
	

}
