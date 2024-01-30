package com.fdmgroup.MattBadmintonMatchmaker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.MattBadmintonMatchmaker.dal.PlaceRepository;

@Service
public class PlaceService {
	private PlaceRepository placeRepository;

	@Autowired
	public PlaceService(PlaceRepository placeRepository) {
		super();
		this.placeRepository = placeRepository;
	}
	
	

}
