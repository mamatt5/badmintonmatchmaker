package com.fdmgroup.MattBadmintonMatchmaker.dal;

import org.springframework.stereotype.Repository;
import com.fdmgroup.MattBadmintonMatchmaker.model.Player;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Integer> {

}
