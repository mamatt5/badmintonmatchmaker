package com.fdmgroup.MattBadmintonMatchmaker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.MattBadmintonMatchmaker.dal.SocialSessionRepository;

@Service
public class SocialSessionService {
	private SocialSessionRepository socialSessionRepository;

	@Autowired
	public SocialSessionService(SocialSessionRepository socialSessionRepository) {
		super();
		this.socialSessionRepository = socialSessionRepository;
	}
	
	
}
