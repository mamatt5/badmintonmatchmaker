package com.fdmgroup.MattBadmintonMatchmaker.dal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fdmgroup.MattBadmintonMatchmaker.model.Place;

@Repository
public interface PlaceRepository extends JpaRepository<Place, Integer> {

}
