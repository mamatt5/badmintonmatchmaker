package com.fdmgroup.MattBadmintonMatchmaker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.fdmgroup.MattBadmintonMatchmaker.model.SocialSession;
import com.fdmgroup.MattBadmintonMatchmaker.service.SocialSessionService;


@RestController
public class SocialSessionController {
	private SocialSessionService socialSessionService;

	@Autowired
	public SocialSessionController(SocialSessionService socialSessionService) {
		super();
		this.socialSessionService = socialSessionService;
	}
	
	@GetMapping("sessions")
	public List<SocialSession> getSessions() {
		return socialSessionService.findAllSessions();
	}
	
	@GetMapping("sessions/{sessionId}")
	public SocialSession findById(@PathVariable int sessionId) {
		return socialSessionService.findById(sessionId);
	}

}