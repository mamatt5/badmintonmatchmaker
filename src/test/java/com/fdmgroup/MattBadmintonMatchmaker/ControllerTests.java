package com.fdmgroup.MattBadmintonMatchmaker;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.fdmgroup.MattBadmintonMatchmaker.controller.BracketController;
import com.fdmgroup.MattBadmintonMatchmaker.controller.GameController;
import com.fdmgroup.MattBadmintonMatchmaker.controller.PlaceController;
import com.fdmgroup.MattBadmintonMatchmaker.controller.PlayerController;
import com.fdmgroup.MattBadmintonMatchmaker.controller.SocialSessionController;
import com.fdmgroup.MattBadmintonMatchmaker.controller.UserController;
import com.fdmgroup.MattBadmintonMatchmaker.model.Bracket;
import com.fdmgroup.MattBadmintonMatchmaker.model.Game;
import com.fdmgroup.MattBadmintonMatchmaker.model.Place;
import com.fdmgroup.MattBadmintonMatchmaker.model.Player;
import com.fdmgroup.MattBadmintonMatchmaker.model.SocialSession;
import com.fdmgroup.MattBadmintonMatchmaker.model.User;
import com.fdmgroup.MattBadmintonMatchmaker.service.BracketService;
import com.fdmgroup.MattBadmintonMatchmaker.service.GameService;
import com.fdmgroup.MattBadmintonMatchmaker.service.MatchmakerService;
import com.fdmgroup.MattBadmintonMatchmaker.service.PlaceService;
import com.fdmgroup.MattBadmintonMatchmaker.service.PlayerService;
import com.fdmgroup.MattBadmintonMatchmaker.service.SocialSessionService;
import com.fdmgroup.MattBadmintonMatchmaker.service.UserService;

@ExtendWith(MockitoExtension.class)
public class ControllerTests {

	@Mock
	BracketService bracketServiceMock;
	@Mock
	GameService gameServiceMock;
	@Mock
	PlaceService placeServiceMock;
	@Mock
	PlayerService playerServiceMock;
	@Mock
	SocialSessionService socialSessionServiceMock;
	@Mock
	UserService userServiceMock;
	@Mock
	MatchmakerService matchMakerServiceMock;

	BracketController bracketController;
	GameController gameController;
	PlaceController placeController;
	PlayerController playerController;
	SocialSessionController socialSessionController;
	UserController userController;

	@BeforeEach
	void setup() {
		bracketController = new BracketController(bracketServiceMock);
		gameController = new GameController(gameServiceMock);
		placeController = new PlaceController(placeServiceMock);
		playerController = new PlayerController(playerServiceMock);
		socialSessionController = new SocialSessionController(socialSessionServiceMock);
		userController = new UserController(userServiceMock);
	}


	///////////////////////////// Bracket controller tests /////////////////////////////

	@Test
	public void bracket_controller_instantiation() {
		assertNotNull(bracketController);

	}

	@Test
	public void bracket_controller_gets_all_brackets() {
		Bracket bracketA = new Bracket(1, 'A');
		Bracket bracketB = new Bracket(2, 'B');
		Bracket bracketC = new Bracket(3, 'C');
		Bracket bracketD = new Bracket(4, 'D');
		Bracket bracketE = new Bracket(5, 'E');

		List<Bracket> brackets = Arrays.asList(bracketA, bracketB, bracketC, bracketD, bracketE);
		when(bracketServiceMock.findAll()).thenReturn(brackets);

		assertEquals(brackets, bracketController.getBrackets());
		verify(bracketServiceMock).findAll();
	}


	///////////////////////////// Place controller tests /////////////////////////////

	@Test
	public void place_controller_instantiation() {
		assertNotNull(placeController);
	}

	@Test
	public void place_controller_gets_all_places() {
		Place alphaAuburn = 	new Place("Alpha Badminton Center, Auburn");
		Place alphaEgerton = 	new Place("Alpha Badminton Center, Egerton");
		Place roketto = 		new Place("Roketto Badminton Center");

		List<Place> places = Arrays.asList(alphaAuburn, alphaEgerton, roketto);
		when(placeServiceMock.findAll()).thenReturn(places);

		assertEquals(places, placeController.getPlaces());
		verify(placeServiceMock).findAll();
	}

	@Test
	public void place_controller_creates_new_place() {
		Place roketto = new Place("Roketto Badminton Center");

		placeController.createNewPlace(roketto);
		verify(placeServiceMock).save(roketto);
	}

	@Test
	public void place_controller_updates_place() {
		Place roketto = new Place("Roketto Badminton Center");

		placeController.updatePlace(roketto);
		verify(placeServiceMock, times(1)).update(roketto);
	}

	///////////////////////////// Player controller tests /////////////////////////////

	@Test
	public void player_controller_instantiation() {
		assertNotNull(playerController);
	}

	@Test
	public void player_controller_gets_all_players() {
		Bracket bracketE = new Bracket(5, 'E');
		Player player1 = new Player("Angel", 	"Ramos", bracketE);
		Player player2 = new Player("Anjo", 	"Alfon", bracketE);
		Player player3 = new Player("Karen", 	"Vega", bracketE);

		List<Player> players = new ArrayList<Player>();
		players.add(player1);
		players.add(player2);
		players.add(player3);

		when(playerServiceMock.findAllPlayers()).thenReturn(players);

		assertEquals(players, playerController.getPlayers());
		verify(playerServiceMock).findAllPlayers();
	}

	@Test
	public void player_controller_returns_specific_player_on_id() {
		Bracket bracketE = new Bracket(5, 'E');
		Player player1 = new Player("Angel", 	"Ramos", bracketE);
		Player player2 = new Player("Anjo", 	"Alfon", bracketE);
		Player player3 = new Player("Karen", 	"Vega", bracketE);
		player1.setId(1);
		player2.setId(2);
		player3.setId(3);

		when(playerServiceMock.findById(1)).thenReturn(player1);
		when(playerServiceMock.findById(2)).thenReturn(player2);
		when(playerServiceMock.findById(3)).thenReturn(player3);

		assertEquals(player1, playerController.findById(1));
		assertEquals(player2, playerController.findById(2));
		assertEquals(player3, playerController.findById(3));

	}

	@Test 
	public void player_controller_throws_when_getting_player_that_does_not_exist() {
		doThrow(RuntimeException.class).when(playerServiceMock).findById(1);
		assertThrows(RuntimeException.class, () -> playerController.findById(1));
		verify(playerServiceMock, times(1)).findById(1);
	}

	@Test
	public void player_controller_creates_new_player() {
		Bracket bracketE = new Bracket(5, 'E');
		Player player1 = new Player("Angel", 	"Ramos", bracketE);
		player1.setId(1);

		when(playerServiceMock.findById(1)).thenReturn(player1);

		Player newPlayer = playerController.createNewPlayer(player1);

		verify(playerServiceMock, times(1)).save(player1);
		assertEquals(newPlayer, player1);

	}

	@Test
	public void player_controller_updates_player() {
		Bracket bracketE = new Bracket(5, 'E');
		Player player1 = new Player("Angel", 	"Ramos", bracketE);
		player1.setId(1);

		playerController.updatePlayer(player1);
		verify(playerServiceMock, times(1)).update(player1);

	}

	@Test
	public void player_controller_deletes_player_that_exists() {

		playerController.deletePlayer(1);
		verify(playerServiceMock, times(1)).deleteById(1);
	}

	@Test
	public void player_controller_throws_when_delete_player_that_does_not_exist() {
		doThrow(RuntimeException.class).when(playerServiceMock).deleteById(1);
		assertThrows(RuntimeException.class, () -> playerController.deletePlayer(1));
		verify(playerServiceMock, times(1)).deleteById(1);
	}

	///////////////////////////// User controller tests /////////////////////////////

	@Test
	public void user_controller_instantiation() {
		assertNotNull(userController);
	}

	@Test
	public void user_controller_gets_all_users() {
		User admin1 = new User("angelR", 	"angelR", true); 		// bracket E
		User admin2 = new User("anjoA", 	"anjoA", true); 		// bracket E
		User admin3 = new User("karenV", 	"karenV", true);

		ArrayList<User> users = new ArrayList<User>();
		users.add(admin1);
		users.add(admin2);
		users.add(admin3);

		when(userServiceMock.findAllUsers()).thenReturn(users);

		assertEquals(users, userController.getUsers());
		verify(userServiceMock).findAllUsers();
	}

	@Test
	public void user_controller_returns_specific_user_on_username() {
		User admin1 = new User("angelR", 	"angelR", true); 		// bracket E
		User admin2 = new User("anjoA", 	"anjoA", true); 		// bracket E
		User admin3 = new User("karenV", 	"karenV", true);

		when(userServiceMock.findUsername("angelR")).thenReturn(admin1);
		when(userServiceMock.findUsername("anjoA")).thenReturn(admin2);
		when(userServiceMock.findUsername("karenV")).thenReturn(admin3);

		assertEquals(admin1, userController.findUsername("angelR"));
		assertEquals(admin2, userController.findUsername("anjoA"));
		assertEquals(admin3, userController.findUsername("karenV"));
	}

	@Test 
	public void user_controller_throws_when_getting_user_that_does_not_exist() {
		doThrow(RuntimeException.class).when(userServiceMock).findUsername("falseUsername");
		assertThrows(RuntimeException.class, () -> userController.findUsername("falseUsername"));
		verify(userServiceMock, times(1)).findUsername("falseUsername");
	}

	@Test
	public void user_controller_creates_new_user() {
		User user1 = new User("mattC", 		"mattC", false);

		when(userServiceMock.findUsername("mattC")).thenReturn(user1);

		User newUser = userController.createNewUser(user1);

		verify(userServiceMock, times(1)).save(user1);
		assertEquals(newUser, user1);
	}

	@Test
	public void user_controller_updates_user() {
		User user1 = new User("mattC", 		"mattC", false);

		userController.updateUser(user1);	
		verify(userServiceMock, times(1)).update(user1);
	}

	@Test
	public void user_controller_deletes_user_that_exists() {
		userController.deleteByUsername("mattC");
		verify(userServiceMock, times(1)).deleteByUsername("mattC");
	}

	@Test
	public void user_controller_throws_when_delete_user_that_does_not_exist() {
		doThrow(RuntimeException.class).when(userServiceMock).deleteByUsername("mattC");
		assertThrows(RuntimeException.class, () -> userController.deleteByUsername("mattC"));
		verify(userServiceMock, times(1)).deleteByUsername("mattC");
	}



	///////////////////////////// Social session controller tests /////////////////////////////

	@Test
	public void social_session_controller_instantiation() {
		assertNotNull(socialSessionController);
	}

	@Test
	public void social_session_controller_gets_all_sessions() {
		Place alphaAuburn = 	new Place("Alpha Badminton Center, Auburn");
		Place alphaEgerton = 	new Place("Alpha Badminton Center, Egerton");
		Place roketto = 		new Place("Roketto Badminton Center");

		SocialSession session1 = new SocialSession(LocalDate.of(2023, 12, 22), 5, alphaAuburn);
		SocialSession session2 = new SocialSession(LocalDate.of(2023, 12, 29), 7, alphaAuburn);
		SocialSession session3 = new SocialSession(LocalDate.of(2024, 1, 6), 7, alphaEgerton);
		SocialSession session4 = new SocialSession(LocalDate.of(2024, 1, 13), 6, alphaEgerton);
		SocialSession session5 = new SocialSession(LocalDate.of(2024, 1, 27), 4, roketto);

		ArrayList<SocialSession> sessions = new ArrayList<SocialSession>();
		sessions.add(session1);
		sessions.add(session2);
		sessions.add(session3);
		sessions.add(session4);
		sessions.add(session5);

		when(socialSessionServiceMock.findAllSessions()).thenReturn(sessions);

		assertEquals(sessions, socialSessionController.getSessions());
		verify(socialSessionServiceMock).findAllSessions();
	}

	@Test
	public void social_session_controller_returns_specific_session_on_id() {
		Place alphaAuburn = 	new Place("Alpha Badminton Center, Auburn");
		Place alphaEgerton = 	new Place("Alpha Badminton Center, Egerton");

		SocialSession session1 = new SocialSession(LocalDate.of(2023, 12, 22), 5, alphaAuburn);
		SocialSession session2 = new SocialSession(LocalDate.of(2023, 12, 29), 7, alphaAuburn);
		SocialSession session3 = new SocialSession(LocalDate.of(2024, 1, 6), 7, alphaEgerton);

		session1.setId(1);
		session2.setId(2);
		session3.setId(3);

		when(socialSessionServiceMock.findById(1)).thenReturn(session1);
		when(socialSessionServiceMock.findById(2)).thenReturn(session2);
		when(socialSessionServiceMock.findById(3)).thenReturn(session3);

		assertEquals(session1, socialSessionController.findById(1));
		assertEquals(session2, socialSessionController.findById(2));
		assertEquals(session3, socialSessionController.findById(3));
	}

	@Test
	public void social_session_controller_throws_when_getting_session_that_does_not_exist() {
		doThrow(RuntimeException.class).when(socialSessionServiceMock).findById(0);
		assertThrows(RuntimeException.class, () -> socialSessionServiceMock.findById(0));
		verify(socialSessionServiceMock, times(1)).findById(0);
	}

	@Test
	public void social_session_controller_creates_new_session() {
		Place roketto = new Place("Roketto Badminton Center");
		SocialSession session5 = new SocialSession(LocalDate.of(2024, 1, 27), 4, roketto);
		session5.setId(5);

		when(socialSessionServiceMock.findById(5)).thenReturn(session5);

		SocialSession newSession = socialSessionController.createNewSession(session5);

		verify(socialSessionServiceMock, times(1)).save(session5);
		assertEquals(newSession, session5);
	}

	@Test
	public void social_session_controller_updates_session() {
		Place roketto = new Place("Roketto Badminton Center");
		SocialSession session5 = new SocialSession(LocalDate.of(2024, 1, 27), 4, roketto);
		session5.setId(5);

		socialSessionController.updateSession(session5);
		verify(socialSessionServiceMock, times(1)).update(session5);
	}

	@Test
	public void social_session_controller_deletes_session_that_exists() {

		socialSessionController.deleteSession(1);
		verify(socialSessionServiceMock, times(1)).deleteById(1);
	}

	@Test
	public void social_session_controller_throws_when_delete_session_that_does_not_exist() {
		doThrow(RuntimeException.class).when(socialSessionServiceMock).deleteById(0);
		assertThrows(RuntimeException.class, () -> socialSessionController.deleteSession(0));
		verify(socialSessionServiceMock, times(1)).deleteById(0);
	}

	///////////////////////////// Game controller tests /////////////////////////////

	@Test
	public void game_controller_instantiation() {
		assertNotNull(gameController);
	}

	@Test
	public void game_controller_gets_all_games() {
		Bracket bracketE = new Bracket(5, 'E');
		Place alphaAuburn = 	new Place("Alpha Badminton Center, Auburn");

		Player player1 = new Player("Angel", 	"Ramos", bracketE);
		Player player2 = new Player("Anjo", 	"Alfon", bracketE);
		Player player3 = new Player("Karen", 	"Vega", bracketE);
		Player player4 = new Player("Matt", 	"Chanco", bracketE);

		SocialSession session1 = new SocialSession(LocalDate.of(2023, 12, 22), 5, alphaAuburn);

		Game game1 = new Game( Arrays.asList(player1,player2,player3,player4), session1);
		Game game2 = new Game( Arrays.asList(player1,player2,player3,player4), session1);
		Game game3 = new Game( Arrays.asList(player1,player2,player3,player4), session1);
		Game game4 = new Game( Arrays.asList(player1,player2,player3,player4), session1);
		Game game5 = new Game( Arrays.asList(player1,player2,player3,player4), session1);

		game1.setId(1);
		game2.setId(2);
		game3.setId(3);
		game4.setId(4);
		game5.setId(5);

		ArrayList<Game> games = new ArrayList<Game>();
		games.add(game1);
		games.add(game2);
		games.add(game3);
		games.add(game4);
		games.add(game5);

		when(gameServiceMock.findAll()).thenReturn(games);

		assertEquals(games, gameController.getGames());
		verify(gameServiceMock).findAll();

	}

	@Test
	public void game_controller_returns_specific_game_on_id() {
		Bracket bracketE = new Bracket(5, 'E');
		Place alphaAuburn = 	new Place("Alpha Badminton Center, Auburn");

		Player player1 = new Player("Angel", 	"Ramos", bracketE);
		Player player2 = new Player("Anjo", 	"Alfon", bracketE);
		Player player3 = new Player("Karen", 	"Vega", bracketE);
		Player player4 = new Player("Matt", 	"Chanco", bracketE);

		SocialSession session1 = new SocialSession(LocalDate.of(2023, 12, 22), 5, alphaAuburn);

		Game game1 = new Game( Arrays.asList(player1,player2,player3,player4), session1);
		Game game2 = new Game( Arrays.asList(player1,player2,player3,player4), session1);
		Game game3 = new Game( Arrays.asList(player1,player2,player3,player4), session1);
		Game game4 = new Game( Arrays.asList(player1,player2,player3,player4), session1);
		Game game5 = new Game( Arrays.asList(player1,player2,player3,player4), session1);

		game1.setId(1);
		game2.setId(2);
		game3.setId(3);
		game4.setId(4);
		game5.setId(5);

		when(gameServiceMock.findById(1)).thenReturn(game1);
		when(gameServiceMock.findById(2)).thenReturn(game2);
		when(gameServiceMock.findById(3)).thenReturn(game3);
		when(gameServiceMock.findById(4)).thenReturn(game4);
		when(gameServiceMock.findById(5)).thenReturn(game5);

		assertEquals(game1, gameController.findById(1));
		assertEquals(game2, gameController.findById(2));
		assertEquals(game3, gameController.findById(3));
		assertEquals(game4, gameController.findById(4));
		assertEquals(game5, gameController.findById(5));
	}

	@Test
	public void game_controller_creates_new_game() {
		Bracket bracketE = new Bracket(5, 'E');
		Place alphaAuburn = 	new Place("Alpha Badminton Center, Auburn");

		Player player1 = new Player("Angel", 	"Ramos", bracketE);
		Player player2 = new Player("Anjo", 	"Alfon", bracketE);
		Player player3 = new Player("Karen", 	"Vega", bracketE);
		Player player4 = new Player("Matt", 	"Chanco", bracketE);

		SocialSession session1 = new SocialSession(LocalDate.of(2024, 1, 27), 4, alphaAuburn);

		Game game6 = new Game( Arrays.asList(player1,player2,player3,player4), session1);
		game6.setId(6);

		when(gameServiceMock.findById(6)).thenReturn(game6);

		Game newGame = gameController.createNew(game6);

		verify(gameServiceMock, times(1)).save(game6);
		assertEquals(newGame, game6);
	}

	@Test
	public void game_controller_updates_game() {
		Bracket bracketE = new Bracket(5, 'E');
		Place alphaAuburn = 	new Place("Alpha Badminton Center, Auburn");

		Player player1 = new Player("Angel", 	"Ramos", bracketE);
		Player player2 = new Player("Anjo", 	"Alfon", bracketE);
		Player player3 = new Player("Karen", 	"Vega", bracketE);
		Player player4 = new Player("Matt", 	"Chanco", bracketE);

		SocialSession session1 = new SocialSession(LocalDate.of(2024, 1, 27), 4, alphaAuburn);

		Game game6 = new Game( Arrays.asList(player1,player2,player3,player4), session1);
		game6.setId(6);

		gameController.updateGame(game6);
		verify(gameServiceMock, times(1)).update(game6);
	}

	@Test
	public void game_controller_deletes_game_that_exists() {
		gameController.deleteGame(1);
		verify(gameServiceMock, times(1)).deleteById(1);
	}

	@Test
	public void game_controller_throws_when_delete_game_that_does_not_exist() {
		doThrow(RuntimeException.class).when(gameServiceMock).deleteById(0);
		assertThrows(RuntimeException.class, () -> gameController.deleteGame(0));
		verify(gameServiceMock, times(1)).deleteById(0);
	}

	@Test
	public void game_controller_generates_games_for_session() {
		GameController gameControllerForMatch = new GameController(gameServiceMock, matchMakerServiceMock, socialSessionServiceMock);

		Place alphaAuburn = 	new Place("Alpha Badminton Center, Auburn");
		SocialSession session = new SocialSession(LocalDate.of(2023, 12, 22), 5, alphaAuburn);
		session.setId(1);
		
		Player[][] gameList = new Player[5][4];
		
		for (int i = 0; i < gameList.length; i++) {
		    for (int j = 0; j < gameList[i].length; j++) {
		        gameList[i][j] = new Player();
		    }
		}

		when(socialSessionServiceMock.findById(1)).thenReturn(session);
		when(matchMakerServiceMock.generateEvenGames(session.getPlayers())).thenReturn(gameList);
		gameControllerForMatch.generateGamesForSession(1);

		verify(gameServiceMock, times(5)).save(any(Game.class));
	}

}
