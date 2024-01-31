package com.fdmgroup.MattBadmintonMatchmaker.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.MattBadmintonMatchmaker.dal.SocialSessionRepository;
import com.fdmgroup.MattBadmintonMatchmaker.model.SocialSession;

@Service
public class SocialSessionService {
	private SocialSessionRepository socialSessionRepository;

	@Autowired
	public SocialSessionService(SocialSessionRepository socialSessionRepository) {
		super();
		this.socialSessionRepository = socialSessionRepository;
	}

	public List<SocialSession> findAllSessions() {
		return this.socialSessionRepository.findAll();
	}

	public SocialSession findById(int sessionId) {
		return this.socialSessionRepository.findById(sessionId).orElseThrow(()-> new RuntimeException("No session with id: " +sessionId));
	}

	public void save(SocialSession newSession) {
		this.socialSessionRepository.save(newSession);
		
	}
	
	
}
