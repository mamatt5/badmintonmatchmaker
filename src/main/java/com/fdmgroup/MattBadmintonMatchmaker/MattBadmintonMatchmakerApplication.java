package com.fdmgroup.MattBadmintonMatchmaker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.fdmgroup.MattBadmintonMatchmaker.security.RsaKeyProperties;

/**
 *  <p>
 *  This application aims to record badminton matches and also allows users to generate matches based on their skill level.
 *	Using this application would help in organizing badminton tournaments as it automates levelling of the players and generates
 *	matches accordingly. It also provides a tangible reference on how good the players are in the sport instead of relying on mere
 *	word of mouth. This application should be runnable on any browser. All user input validations are done on the front end to 
 *	concentrate data processing on the client side.This app is also designed with simplicity in mind. Components and pages are
 *	rendered in the most simplistic way possible using only basic css elements. It was also aimed to be as intuitive as possible,
 *	minimizing key strokes by utilizing just button clicks to perform various actions.
 *	</p>
 *
 *	<p>
 *	This application was built on the following infrastructure:
 *	<ul>
 *		<li>Frontend: React Vite</li>
 *		<li>Backend: Java, Spring framework</li>
 *		<li>Database: MySQL</li>
 *		<li>Code editors: Eclipse, VS Code</li>
 *		<li>Other tools: Postman, Git, PNPM</li>
 *		<li>Operating system: Windows</li>
 *		<li>Ports: Server 8088 , MySQL database 3306/badmintonmatchmaker , React Vite 5173</li>
 *	</ul>
 *	</p>
 *
 *	<p>
 *	This app can only be used if the user is logged in. A bearer token is stored locally once successfully logged in. This bearer token
 *	would be removed once the user decides to log out.
 *	</p>
 *
 *	<p>
 *	In the future:
 *	<ul>
 *		<li>Integrate a player entity into a user.</li>
 *		<li>Assign admin/user role for all users and restrict some functionalities accordingly</li>
 *		<li>Display onHover additional player details (ie. beemIt, player bracket)</li>
 *		<li>Pagination</li>
 *		<li>Integrate into a mobile application</li>
 *		<li>Implement doubles matching where pairs are matched with other pairs (restriction where two players must always be together in a game)</li>
 *		<li>Improve the overall appearance of the application by using MaterialUI</li>
 *	</ul>
 *	</p>
 *
 *	<p>
 *	Note that in the backend, most models/controllers/services are of generic code structure with the exception of Game controller,
 *	Game service, and Matchmaker service. Security functionalities are integrated from a sample project shared in our class. Reference
 *	is from:</p>
 *	
 *	<p>
 *		<a href="https://www.danvega.dev/blog/spring-security-jwt">
 *		How to Secure your REST APIs with Spring Security & JSON Web Tokens (JWTs) by Dan Vega</a>
 *		
 *	</p>
 *
 *	<p>
 *	It is recommended to do read the comments on some of the codes to help understand how the application works, especially the
 *	aforementioned components above. JavaDoc is generated to check which components have important comments.
 *	</p>
 *
 *	@author Matthew Chanco
 *	@version 17/02/2024
 */

@SpringBootApplication
@EnableConfigurationProperties(RsaKeyProperties.class)
public class MattBadmintonMatchmakerApplication {

	public static void main(String[] args) {
		SpringApplication.run(MattBadmintonMatchmakerApplication.class, args);
		System.out.println("Prookies matchmaker has started");
	}

}
