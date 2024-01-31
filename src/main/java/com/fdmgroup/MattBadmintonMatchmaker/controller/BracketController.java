package com.fdmgroup.MattBadmintonMatchmaker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fdmgroup.MattBadmintonMatchmaker.model.Bracket;
import com.fdmgroup.MattBadmintonMatchmaker.service.BracketService;

@RestController
public class BracketController {
	private BracketService bracketService;

	@Autowired
	public BracketController(BracketService bracketService) {
		super();
		this.bracketService = bracketService;
	}
	
	@GetMapping("brackets")
	public List<Bracket> getBrackets() {
		return bracketService.findAll();
	}

}
