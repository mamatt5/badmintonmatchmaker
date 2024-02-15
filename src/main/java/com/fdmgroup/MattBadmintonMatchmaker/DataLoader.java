package com.fdmgroup.MattBadmintonMatchmaker;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;

import com.fdmgroup.MattBadmintonMatchmaker.dal.BracketRepository;
import com.fdmgroup.MattBadmintonMatchmaker.dal.GameRepository;
import com.fdmgroup.MattBadmintonMatchmaker.dal.PlaceRepository;
import com.fdmgroup.MattBadmintonMatchmaker.dal.PlayerRepository;
import com.fdmgroup.MattBadmintonMatchmaker.dal.SocialSessionRepository;
import com.fdmgroup.MattBadmintonMatchmaker.service.UserService;
import com.fdmgroup.MattBadmintonMatchmaker.model.Bracket;
import com.fdmgroup.MattBadmintonMatchmaker.model.Game;
import com.fdmgroup.MattBadmintonMatchmaker.model.Place;
import com.fdmgroup.MattBadmintonMatchmaker.model.Player;
import com.fdmgroup.MattBadmintonMatchmaker.model.SocialSession;
import com.fdmgroup.MattBadmintonMatchmaker.model.User;



@Service
public class DataLoader implements ApplicationRunner {
	private BracketRepository bracketRepository;
	private GameRepository gameRepository;
	private PlaceRepository placeRepository;
	private PlayerRepository playerRepository;
	private SocialSessionRepository socialSessionRepository;
	private UserService userService;
	
	

	@Autowired
	public DataLoader(BracketRepository bracketRepository, GameRepository gameRepository,
			PlaceRepository placeRepository, PlayerRepository playerRepository,
			SocialSessionRepository socialSessionRepository, UserService userService) {
		super();
		this.bracketRepository = bracketRepository;
		this.gameRepository = gameRepository;
		this.placeRepository = placeRepository;
		this.playerRepository = playerRepository;
		this.socialSessionRepository = socialSessionRepository;
		this.userService = userService;
	}




	@Override
	public void run(ApplicationArguments args) throws Exception {

		// Bracket entities
		Bracket bracketA = new Bracket(1, 'A');
		Bracket bracketB = new Bracket(2, 'B');
		Bracket bracketC = new Bracket(3, 'C');
		Bracket bracketD = new Bracket(4, 'D');
		Bracket bracketE = new Bracket(5, 'E');

		// Place entities
		Place alphaAuburn = 	new Place("Alpha Badminton Center, Auburn");
		Place alphaEgerton = 	new Place("Alpha Badminton Center, Egerton");
		Place roketto = 		new Place("Roketto Badminton Center");


		// User dummies
		User admin1 = new User("angelR", 	"angelR", true); 		// bracket E
		User admin2 = new User("anjoA", 	"anjoA", true); 		// bracket E
		User admin3 = new User("karenV", 	"karenV", true); 		// bracket E

		User user1 = new User("mattC", 		"mattC", false); 		// bracket D
		User user2 = new User("jonahE", 	"jonahE", false); 		// bracket E
		User user3 = new User("elishaO", 	"elishaO", false); 		// bracket A
		User user4 = new User("kevinS", 	"kevinS", false);		// bracket C
		User user5 = new User("jrrR", 		"jrrR", false);			// bracket B

		// Collect all users
		ArrayList<User> users = new ArrayList<User>();
		users.add(admin1);
		users.add(admin2);
		users.add(admin3);
		users.add(user1);
		users.add(user2);
		users.add(user3);
		users.add(user4);
		users.add(user5);



		// Player dummies

		// players that can login
		Player player1 = new Player("Angel", 	"Ramos", bracketE);
		Player player2 = new Player("Anjo", 	"Alfon", bracketE);
		Player player3 = new Player("Karen", 	"Vega", bracketE);
		Player player4 = new Player("Matt", 	"Chanco", bracketD);
		Player player5 = new Player("Jonah", 	"Ecito", bracketE);
		Player player6 = new Player("Elisha", 	"Ongcuangco", bracketA);
		Player player7 = new Player("Kevin", 	"Samson", bracketC);
		Player player8 = new Player("JRR", 	"Rolle", bracketB);

		// players that cannot login
		Player player9 = new Player("Fernan", 	"Bautista", bracketD);
		Player player10 = new Player("Andrew", 	"Andres", bracketB);
		Player player11 = new Player("Nap", 	"Ocampo", bracketA);
		Player player12 = new Player("Kevin", 	"Xu", bracketA);
		Player player13 = new Player("Jeck", 	"Tolentino", bracketB);
		Player player14 = new Player("Ervin",	"Macrohon", bracketD);
		Player player15 = new Player("Dane", 	"Javier", bracketD);
		Player player16 = new Player("Jenn", 	"Young", bracketE);
		Player player17 = new Player("Reena", 	"Ramos", bracketE);
		Player player18 = new Player("Arjan", 	"De Guzman", bracketE);
		Player player19 = new Player("Christian", "Manga", bracketC);
		Player player20 = new Player("JV", 		"Paraiso", bracketC);
		
		// players for demo
		Player player21 = new Player("Player1A", "test", bracketA);
		Player player22 = new Player("Player2A", "test", bracketA);
		Player player23 = new Player("Player1B", "test", bracketB);
		Player player24 = new Player("Player2B", "test", bracketB);
		Player player25 = new Player("Player1C", "test", bracketC);
		Player player26 = new Player("Player2C", "test", bracketC);
		Player player27 = new Player("Player1D", "test", bracketD);
		Player player28 = new Player("Player2D", "test", bracketD);
		Player player29 = new Player("Player1E", "test", bracketE);
		Player player30 = new Player("Player2E", "test", bracketE);
		Player player31 = new Player("Player3E", "test", bracketE);
		Player player32 = new Player("Player4E", "test", bracketE);

		// set players to users
		admin1.setPlayer(player1);
		admin2.setPlayer(player2);
		admin3.setPlayer(player3);

		user1.setPlayer(player4);
		user2.setPlayer(player5);
		user3.setPlayer(player6);
		user4.setPlayer(player7);
		user5.setPlayer(player8);


		// Collect all players
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(player1);
		players.add(player2);
		players.add(player3);
		players.add(player4);
		players.add(player5);
		players.add(player6);
		players.add(player7);
		players.add(player8);
		players.add(player9);
		players.add(player10);
		players.add(player11);
		players.add(player12);
		players.add(player13);
		players.add(player14);
		players.add(player15);
		players.add(player16);
		players.add(player17);
		players.add(player18);
		players.add(player19);
		players.add(player20);
		players.add(player21);
		players.add(player22);
		players.add(player23);
		players.add(player24);
		players.add(player25);
		players.add(player26);
		players.add(player27);
		players.add(player28);
		players.add(player29);
		players.add(player30);
		players.add(player31);
		players.add(player32);


		// Social Sessions dummies
		SocialSession session1 = new SocialSession(LocalDate.of(2023, 12, 22), 5, alphaAuburn);
		SocialSession session2 = new SocialSession(LocalDate.of(2023, 12, 29), 7, alphaAuburn);
		SocialSession session3 = new SocialSession(LocalDate.of(2024, 1, 6), 7, alphaEgerton);
		SocialSession session4 = new SocialSession(LocalDate.of(2024, 1, 13), 6, alphaEgerton);
		SocialSession session5 = new SocialSession(LocalDate.of(2024, 1, 27), 4, roketto);

		// Collect all sessions
		ArrayList<SocialSession> sessions = new ArrayList<SocialSession>();
		sessions.add(session1);
		sessions.add(session2);
		sessions.add(session3);
		sessions.add(session4);
		sessions.add(session5);

		// add players to each social session (make into method next for social session)
		for (int i = 0 ; i < players.size() ; i++ ) {
			session1.getPlayers().add(players.get(i));
			session2.getPlayers().add(players.get(i));
			session3.getPlayers().add(players.get(i));
			session4.getPlayers().add(players.get(i));
			session5.getPlayers().add(players.get(i));
		}


		// Game dummies
		Game game1 = new Game( Arrays.asList(player1,player2,player3,player4), session1);
		Game game2 = new Game( Arrays.asList(player5,player6,player7,player8), session1);
		Game game3 = new Game( Arrays.asList(player9,player10,player11,player12), session1);
		Game game4 = new Game( Arrays.asList(player13,player14,player15,player16), session1);
		Game game5 = new Game( Arrays.asList(player17,player18,player19,player20), session1);

		Game game6 = new Game( Arrays.asList(player1,player2,player3,player4), session2);
		Game game7 = new Game( Arrays.asList(player5,player6,player7,player8), session2);
		Game game8 = new Game( Arrays.asList(player9,player10,player11,player12), session2);
		Game game9 = new Game( Arrays.asList(player13,player14,player15,player16), session2);
		Game game10 = new Game( Arrays.asList(player17,player18,player19,player20), session2);

		Game game11 = new Game( Arrays.asList(player1,player2,player3,player4), session3);
		Game game12 = new Game( Arrays.asList(player5,player6,player7,player8), session3);
		Game game13 = new Game( Arrays.asList(player9,player10,player11,player12), session3);
		Game game14 = new Game( Arrays.asList(player13,player14,player15,player16), session3);
		Game game15 = new Game( Arrays.asList(player17,player18,player19,player20), session3);

		Game game16 = new Game( Arrays.asList(player1,player2,player3,player4), session4);
		Game game17 = new Game( Arrays.asList(player5,player6,player7,player8), session4);
		Game game18 = new Game( Arrays.asList(player9,player10,player11,player12), session4);
		Game game19 = new Game( Arrays.asList(player13,player14,player15,player16), session4);
		Game game20 = new Game( Arrays.asList(player17,player18,player19,player20), session4);

		Game game21 = new Game( Arrays.asList(player1,player2,player3,player4), session5);
		Game game22 = new Game( Arrays.asList(player5,player6,player7,player8), session5);
		Game game23 = new Game( Arrays.asList(player9,player10,player11,player12), session5);
		Game game24 = new Game( Arrays.asList(player13,player14,player15,player16), session5);
		Game game25 = new Game( Arrays.asList(player17,player18,player19,player20), session5);

		// Collect all game
		ArrayList<Game> games = new ArrayList<Game>();
		games.add(game1);
		games.add(game2);
		games.add(game3);
		games.add(game4);
		games.add(game5);
		games.add(game6);
		games.add(game7);
		games.add(game8);
		games.add(game9);
		games.add(game10);
		games.add(game11);
		games.add(game12);
		games.add(game13);
		games.add(game14);
		games.add(game15);
		games.add(game16);
		games.add(game17);
		games.add(game18);
		games.add(game19);
		games.add(game20);
		games.add(game21);
		games.add(game22);
		games.add(game23);
		games.add(game24);
		games.add(game25);

		// set lose score to all game as 10, and first two players as winning players
		for (Game game : games) {
			game.setLoseScore(10);
			game.setWinners(game.getPlayers().subList(0, 2));
		}
		
		// Load bracket entities
		bracketRepository.save(bracketA);
		bracketRepository.save(bracketB);
		bracketRepository.save(bracketC);
		bracketRepository.save(bracketD);
		bracketRepository.save(bracketE);
		
		// Load player entities
		for (Player player : players) {
			playerRepository.save(player);
		}
		
		// Load user entities
		for (User user : users) {
			userService.register(user);
		}
		
		// Load place entities
		placeRepository.save(alphaAuburn);
		placeRepository.save(alphaEgerton);
		placeRepository.save(roketto);
		
		// Change to userService for dataloading for encrypting
		// Load social session entities
		for (SocialSession session : sessions) {
			socialSessionRepository.save(session);
		}
		
		// Load game entities
		for (Game game : games) {
			gameRepository.save(game);
		}

	}

}
