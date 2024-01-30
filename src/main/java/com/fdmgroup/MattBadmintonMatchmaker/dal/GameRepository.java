package com.fdmgroup.MattBadmintonMatchmaker.dal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fdmgroup.MattBadmintonMatchmaker.model.Game;

@Repository
public interface GameRepository extends JpaRepository<Game, Integer> {

}
