package com.fdmgroup.MattBadmintonMatchmaker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fdmgroup.MattBadmintonMatchmaker.model.Place;
import com.fdmgroup.MattBadmintonMatchmaker.service.PlaceService;

@RestController
public class PlaceController {
	private PlaceService placeService;

	@Autowired
	public PlaceController(PlaceService placeService) {
		super();
		this.placeService = placeService;
	}
	
	@GetMapping("places")
	public List<Place> getPlaces() {
		return placeService.findAll();
	}
	
	@PostMapping("places")
	public List<Place> createNewPlace(@RequestBody Place newPlace) {
		placeService.save(newPlace);
		return placeService.findAll();
	}
	
	@PutMapping("places")
	public List<Place> updatePlace(@RequestBody Place newPlace) {
		placeService.update(newPlace);
		return placeService.findAll();
	}

}
