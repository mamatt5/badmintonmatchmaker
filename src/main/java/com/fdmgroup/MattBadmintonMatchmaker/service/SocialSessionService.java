package com.fdmgroup.MattBadmintonMatchmaker.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.MattBadmintonMatchmaker.dal.SocialSessionRepository;
import com.fdmgroup.MattBadmintonMatchmaker.exceptions.DuplicateException;
import com.fdmgroup.MattBadmintonMatchmaker.exceptions.NotFoundException;
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
		return this.socialSessionRepository.findById(sessionId).orElseThrow(()-> new NotFoundException("No session with id: " +sessionId));
	}

	public void save(SocialSession newSession) {
		if (this.socialSessionRepository.existsById(newSession.getId())) {
			throw new DuplicateException("Session already exists");
			
		} else {
			this.socialSessionRepository.save(newSession);
		}
	}

	public void deleteById(int sessionId) {
		if (this.socialSessionRepository.existsById(sessionId)) {
			socialSessionRepository.deleteById(sessionId);
		} else {
			throw new NotFoundException("Session does not exist");
		}
		
		
	}

	public void update(SocialSession newSession) {
		if (this.socialSessionRepository.existsById(newSession.getId())) {
			this.socialSessionRepository.save(newSession);
			
			
		} else {
			throw new NotFoundException("Session does not exist");
		}
	}
	
	
}
