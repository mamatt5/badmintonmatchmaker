package com.fdmgroup.MattBadmintonMatchmaker.dal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fdmgroup.MattBadmintonMatchmaker.model.SocialSession;

@Repository
public interface SocialSessionRepository extends JpaRepository<SocialSession, Integer> {

}
