package com.fdmgroup.MattBadmintonMatchmaker.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.MattBadmintonMatchmaker.dal.PlaceRepository;
import com.fdmgroup.MattBadmintonMatchmaker.exceptions.DuplicateException;
import com.fdmgroup.MattBadmintonMatchmaker.exceptions.NotFoundException;
import com.fdmgroup.MattBadmintonMatchmaker.model.Place;

@Service
public class PlaceService {
	private PlaceRepository placeRepository;

	@Autowired
	public PlaceService(PlaceRepository placeRepository) {
		super();
		this.placeRepository = placeRepository;
	}

	public List<Place> findAll() {
		return this.placeRepository.findAll();
	}

	public void save(Place newPlace) {
		if (this.placeRepository.existsById(newPlace.getId())) {
			throw new DuplicateException("Place already exists");
			
		} else {
			this.placeRepository.save(newPlace);
		}
		
		
	}

	public void update(Place newPlace) {
		if (this.placeRepository.existsById(newPlace.getId())) {
			this.placeRepository.save(newPlace);
			
		} else {
			
			throw new NotFoundException("Place does not exist");
		}
		
		
	}
	
	

}
