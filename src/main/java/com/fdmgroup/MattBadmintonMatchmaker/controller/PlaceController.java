package com.fdmgroup.MattBadmintonMatchmaker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.fdmgroup.MattBadmintonMatchmaker.service.PlaceService;

@RestController
public class PlaceController {
	private PlaceService placeService;

	@Autowired
	public PlaceController(PlaceService placeService) {
		super();
		this.placeService = placeService;
	}
	
	

}
