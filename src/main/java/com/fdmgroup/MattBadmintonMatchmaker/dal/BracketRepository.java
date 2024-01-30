package com.fdmgroup.MattBadmintonMatchmaker.dal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fdmgroup.MattBadmintonMatchmaker.model.Bracket;

@Repository
public interface BracketRepository extends JpaRepository<Bracket, Integer> {

}
