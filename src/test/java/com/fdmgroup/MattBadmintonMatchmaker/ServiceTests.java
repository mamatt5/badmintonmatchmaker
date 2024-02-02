package com.fdmgroup.MattBadmintonMatchmaker;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.fdmgroup.MattBadmintonMatchmaker.dal.BracketRepository;
import com.fdmgroup.MattBadmintonMatchmaker.dal.GameRepository;
import com.fdmgroup.MattBadmintonMatchmaker.dal.PlaceRepository;
import com.fdmgroup.MattBadmintonMatchmaker.dal.PlayerRepository;
import com.fdmgroup.MattBadmintonMatchmaker.dal.SocialSessionRepository;
import com.fdmgroup.MattBadmintonMatchmaker.dal.UserRepository;
import com.fdmgroup.MattBadmintonMatchmaker.model.Bracket;
import com.fdmgroup.MattBadmintonMatchmaker.model.Game;
import com.fdmgroup.MattBadmintonMatchmaker.model.Place;
import com.fdmgroup.MattBadmintonMatchmaker.model.Player;
import com.fdmgroup.MattBadmintonMatchmaker.model.SocialSession;
import com.fdmgroup.MattBadmintonMatchmaker.model.User;
import com.fdmgroup.MattBadmintonMatchmaker.service.BracketService;
import com.fdmgroup.MattBadmintonMatchmaker.service.GameService;
import com.fdmgroup.MattBadmintonMatchmaker.service.PlaceService;
import com.fdmgroup.MattBadmintonMatchmaker.service.PlayerService;
import com.fdmgroup.MattBadmintonMatchmaker.service.SocialSessionService;
import com.fdmgroup.MattBadmintonMatchmaker.service.UserService;

@ExtendWith(MockitoExtension.class)
public class ServiceTests {

	@Mock
	BracketRepository bracketRepositoryMock;
	@Mock
	GameRepository gameRepositoryMock;
	@Mock
	PlaceRepository placeRepositoryMock;
	@Mock
	PlayerRepository playerRepositoryMock;
	@Mock
	SocialSessionRepository socialSessionRepositoryMock;
	@Mock
	UserRepository userRepositoryMock;

	BracketService bracketService;
	GameService gameService;
	PlaceService placeService;
	PlayerService playerService;
	SocialSessionService socialSessionService;
	UserService userService;

	@BeforeEach
	public void setup() {
		bracketService = new BracketService(bracketRepositoryMock);
		gameService = new GameService(gameRepositoryMock);
		placeService = new PlaceService(placeRepositoryMock);
		playerService = new PlayerService(playerRepositoryMock);
		socialSessionService = new SocialSessionService(socialSessionRepositoryMock);
		userService = new UserService(userRepositoryMock);
	}
	/////////////// Bracket service tests ///////////////

	@Test
	public void bracket_service_instantiation() {
		assertNotNull(bracketService);
	}

	@Test
	public void bracket_service_gets_all_brackets() {
		Bracket bracketA = new Bracket(1, 'A');
		Bracket bracketB = new Bracket(2, 'B');
		Bracket bracketC = new Bracket(3, 'C');
		Bracket bracketD = new Bracket(4, 'D');
		Bracket bracketE = new Bracket(5, 'E');

		List<Bracket> brackets = Arrays.asList(bracketA, bracketB, bracketC, bracketD, bracketE);
		when(bracketRepositoryMock.findAll()).thenReturn(brackets);

		assertEquals(brackets, bracketService.findAll());
		verify(bracketRepositoryMock).findAll();
	}

	///////////////////////////// Place service tests /////////////////////////////

	@Test
	public void place_service_instantiation() {
		assertNotNull(placeService);
	}

	@Test
	public void place_service_gets_all_places() {
		Place alphaAuburn = 	new Place("Alpha Badminton Center, Auburn");
		Place alphaEgerton = 	new Place("Alpha Badminton Center, Egerton");
		Place roketto = 		new Place("Roketto Badminton Center");

		List<Place> places = Arrays.asList(alphaAuburn, alphaEgerton, roketto);
		when(placeRepositoryMock.findAll()).thenReturn(places);

		assertEquals(places, placeService.findAll());
		verify(placeRepositoryMock).findAll();
	}

	@Test
	public void place_service_creates_new_place() {
		Place roketto = new Place("Roketto Badminton Center");

		placeService.save(roketto);
		verify(placeRepositoryMock).save(roketto);
	}
	
	@Test
	public void place_service_throws_when_creates_new_place_that_exists() {
		Place roketto = new Place("Roketto Badminton Center");
		roketto.setId(1);

		when(placeRepositoryMock.existsById(1)).thenReturn(true);
		assertThrows(RuntimeException.class, () -> placeService.save(roketto));
		verify(placeRepositoryMock, times(1)).existsById(1);
		verify(placeRepositoryMock, times(0)).save(roketto);
	}

	///////////////////////////// Player service tests /////////////////////////////

	@Test
	public void player_service_instantiation() {
		assertNotNull(playerService);
	}

	@Test
	public void player_service_gets_all_players() {
		Bracket bracketE = new Bracket(5, 'E');
		Player player1 = new Player("Angel", 	"Ramos", bracketE);
		Player player2 = new Player("Anjo", 	"Alfon", bracketE);
		Player player3 = new Player("Karen", 	"Vega", bracketE);

		List<Player> players = new ArrayList<Player>();
		players.add(player1);
		players.add(player2);
		players.add(player3);

		when(playerRepositoryMock.findAll()).thenReturn(players);

		assertEquals(players, playerService.findAllPlayers());
		verify(playerRepositoryMock).findAll();
	}

	@Test
	public void player_service_returns_specific_player_on_id() {
		Bracket bracketE = new Bracket(5, 'E');
		Player player1 = new Player("Angel", 	"Ramos", bracketE);
		Player player2 = new Player("Anjo", 	"Alfon", bracketE);
		Player player3 = new Player("Karen", 	"Vega", bracketE);
		player1.setId(1);
		player2.setId(2);
		player3.setId(3);

		when(playerRepositoryMock.findById(1)).thenReturn(Optional.of(player1));
		when(playerRepositoryMock.findById(2)).thenReturn(Optional.of(player2));
		when(playerRepositoryMock.findById(3)).thenReturn(Optional.of(player3));

		assertEquals(player1, playerService.findById(1));
		assertEquals(player2, playerService.findById(2));
		assertEquals(player3, playerService.findById(3));

	}

	@Test 
	public void player_service_throws_when_getting_player_that_does_not_exist() {
		doThrow(RuntimeException.class).when(playerRepositoryMock).findById(1);
		assertThrows(RuntimeException.class, () -> playerService.findById(1));
		verify(playerRepositoryMock, times(1)).findById(1);
	}

	@Test
	public void player_service_creates_new_player() {
		Bracket bracketE = new Bracket(5, 'E');
		Player player1 = new Player("Angel", 	"Ramos", bracketE);
		player1.setId(1);

		playerService.save(player1);
		verify(playerRepositoryMock, times(1)).save(player1);

	}
	
	@Test
	public void player_service_throws_when_creates_new_player_that_exists() {
		Bracket bracketE = new Bracket(5, 'E');
		Player player1 = new Player("Angel", 	"Ramos", bracketE);
		player1.setId(1);

		when(playerRepositoryMock.existsById(1)).thenReturn(true);
		assertThrows(RuntimeException.class, () -> playerService.save(player1));
		verify(playerRepositoryMock, times(1)).existsById(1);
		verify(playerRepositoryMock, times(0)).save(player1);

	}

	@Test
	public void player_service_deletes_player_that_exists() {
		when(playerRepositoryMock.existsById(1)).thenReturn(true);
		playerService.deleteById(1);
		verify(playerRepositoryMock, times(1)).existsById(1);
		verify(playerRepositoryMock, times(1)).deleteById(1);
	}

	@Test
	public void player_service_throws_when_delete_player_that_does_not_exist() {
		when(playerRepositoryMock.existsById(1)).thenReturn(false);
		assertThrows(RuntimeException.class, () -> playerService.deleteById(1));
		verify(playerRepositoryMock, times(0)).deleteById(1);
	}

	///////////////////////////// User service tests /////////////////////////////

	@Test
	public void user_service_instantiation() {
		assertNotNull(userService);
	}

	@Test
	public void user_service_gets_all_users() {
		User admin1 = new User("angelR", 	"angelR", true); 		// bracket E
		User admin2 = new User("anjoA", 	"anjoA", true); 		// bracket E
		User admin3 = new User("karenV", 	"karenV", true);

		ArrayList<User> users = new ArrayList<User>();
		users.add(admin1);
		users.add(admin2);
		users.add(admin3);

		when(userRepositoryMock.findAll()).thenReturn(users);

		assertEquals(users, userService.findAllUsers());
		verify(userRepositoryMock).findAll();
	}

	@Test
	public void user_service_returns_specific_user_on_username() {
		User admin1 = new User("angelR", 	"angelR", true); 		// bracket E
		User admin2 = new User("anjoA", 	"anjoA", true); 		// bracket E
		User admin3 = new User("karenV", 	"karenV", true);

		when(userRepositoryMock.findById("angelR")).thenReturn(Optional.of(admin1));
		when(userRepositoryMock.findById("anjoA")).thenReturn(Optional.of(admin2));
		when(userRepositoryMock.findById("karenV")).thenReturn(Optional.of(admin3));

		assertEquals(admin1, userService.findUsername("angelR"));
		assertEquals(admin2, userService.findUsername("anjoA"));
		assertEquals(admin3, userService.findUsername("karenV"));
	}

	@Test 
	public void user_service_throws_when_getting_user_that_does_not_exist() {
		doThrow(RuntimeException.class).when(userRepositoryMock).findById("falseUsername");
		assertThrows(RuntimeException.class, () -> userService.findUsername("falseUsername"));
		verify(userRepositoryMock, times(1)).findById("falseUsername");
	}

	@Test
	public void user_service_creates_new_user() {
		User user1 = new User("mattC", 		"mattC", false);

		userService.save(user1);

		verify(userRepositoryMock, times(1)).save(user1);
	}
	
	@Test
	public void user_service_throws_when_creates_new_user_that_exists() {
		User user1 = new User("mattC", 		"mattC", false);

		when(userRepositoryMock.existsById("mattC")).thenReturn(true);
		assertThrows(RuntimeException.class, () -> userService.save(user1));
		verify(userRepositoryMock, times(1)).existsById("mattC");
		verify(userRepositoryMock, times(0)).save(user1);
	}

	@Test
	public void user_service_deletes_user_that_exists() {
		when(userRepositoryMock.existsById("mattC")).thenReturn(true);
		userService.deleteByUsername("mattC");
		verify(userRepositoryMock, times(1)).existsById("mattC");
		verify(userRepositoryMock, times(1)).deleteById("mattC");
	}

	@Test
	public void user_service_throws_when_delete_user_that_does_not_exist() {
		when(userRepositoryMock.existsById("falseUsername")).thenReturn(false);
		assertThrows(RuntimeException.class, () -> userService.deleteByUsername("falseUsername"));
		verify(userRepositoryMock, times(0)).deleteById("falseUsername");
	}

	///////////////////////////// Social session service tests /////////////////////////////

	@Test
	public void social_session_service_instantiation() {
		assertNotNull(socialSessionService);
	}

	@Test
	public void social_session_service_gets_all_sessions() {
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

		when(socialSessionRepositoryMock.findAll()).thenReturn(sessions);

		assertEquals(sessions, socialSessionService.findAllSessions());
		verify(socialSessionRepositoryMock).findAll();
	}

	@Test
	public void social_session_service_returns_specific_session_on_id() {
		Place alphaAuburn = 	new Place("Alpha Badminton Center, Auburn");
		Place alphaEgerton = 	new Place("Alpha Badminton Center, Egerton");

		SocialSession session1 = new SocialSession(LocalDate.of(2023, 12, 22), 5, alphaAuburn);
		SocialSession session2 = new SocialSession(LocalDate.of(2023, 12, 29), 7, alphaAuburn);
		SocialSession session3 = new SocialSession(LocalDate.of(2024, 1, 6), 7, alphaEgerton);

		session1.setId(1);
		session2.setId(2);
		session3.setId(3);

		when(socialSessionRepositoryMock.findById(1)).thenReturn(Optional.of(session1));
		when(socialSessionRepositoryMock.findById(2)).thenReturn(Optional.of(session2));
		when(socialSessionRepositoryMock.findById(3)).thenReturn(Optional.of(session3));

		assertEquals(session1, socialSessionService.findById(1));
		assertEquals(session2, socialSessionService.findById(2));
		assertEquals(session3, socialSessionService.findById(3));
	}

	@Test
	public void social_session_service_throws_when_getting_session_that_does_not_exist() {
		doThrow(RuntimeException.class).when(socialSessionRepositoryMock).findById(0);
		assertThrows(RuntimeException.class, () -> socialSessionRepositoryMock.findById(0));
		verify(socialSessionRepositoryMock, times(1)).findById(0);
	}

	@Test
	public void social_session_service_creates_new_session() {
		Place roketto = new Place("Roketto Badminton Center");
		SocialSession session5 = new SocialSession(LocalDate.of(2024, 1, 27), 4, roketto);
		session5.setId(5);

		socialSessionService.save(session5);
		verify(socialSessionRepositoryMock, times(1)).save(session5);

	}
	
	@Test
	public void social_session_service_throws_when_creates_new_session_that_exists() {
		Place roketto = new Place("Roketto Badminton Center");
		SocialSession session5 = new SocialSession(LocalDate.of(2024, 1, 27), 4, roketto);
		session5.setId(5);

		when(socialSessionRepositoryMock.existsById(5)).thenReturn(true);
		assertThrows(RuntimeException.class, () -> socialSessionService.save(session5));
		verify(socialSessionRepositoryMock, times(1)).existsById(5);
		verify(socialSessionRepositoryMock, times(0)).save(session5);

	}

	@Test
	public void social_session_service_deletes_session_that_exists() {
		when(socialSessionRepositoryMock.existsById(1)).thenReturn(true);
		socialSessionService.deleteById(1);
		verify(socialSessionRepositoryMock, times(1)).existsById(1);
		verify(socialSessionRepositoryMock, times(1)).deleteById(1);
	}

	@Test
	public void social_session_service_throws_when_delete_session_that_does_not_exist() {
		when(socialSessionRepositoryMock.existsById(1)).thenReturn(false);
		assertThrows(RuntimeException.class, () -> socialSessionService.deleteById(1));
		verify(socialSessionRepositoryMock, times(0)).deleteById(1);
	}

	///////////////////////////// Game service tests /////////////////////////////

	@Test
	public void game_service_instantiation() {
		assertNotNull(gameService);
	}

	@Test
	public void game_service_gets_all_games() {
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

		when(gameRepositoryMock.findAll()).thenReturn(games);

		assertEquals(games, gameService.findAll());
		verify(gameRepositoryMock).findAll();

	}

	@Test
	public void game_service_returns_specific_game_on_id() {
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

		when(gameRepositoryMock.findById(1)).thenReturn(Optional.of(game1));
		when(gameRepositoryMock.findById(2)).thenReturn(Optional.of(game2));
		when(gameRepositoryMock.findById(3)).thenReturn(Optional.of(game3));
		when(gameRepositoryMock.findById(4)).thenReturn(Optional.of(game4));
		when(gameRepositoryMock.findById(5)).thenReturn(Optional.of(game5));

		assertEquals(game1, gameService.findById(1));
		assertEquals(game2, gameService.findById(2));
		assertEquals(game3, gameService.findById(3));
		assertEquals(game4, gameService.findById(4));
		assertEquals(game5, gameService.findById(5));
	}

	@Test
	public void game_service_creates_new_game() {
		Bracket bracketE = new Bracket(5, 'E');
		Place alphaAuburn = 	new Place("Alpha Badminton Center, Auburn");

		Player player1 = new Player("Angel", 	"Ramos", bracketE);
		Player player2 = new Player("Anjo", 	"Alfon", bracketE);
		Player player3 = new Player("Karen", 	"Vega", bracketE);
		Player player4 = new Player("Matt", 	"Chanco", bracketE);

		SocialSession session1 = new SocialSession(LocalDate.of(2024, 1, 27), 4, alphaAuburn);

		Game game6 = new Game( Arrays.asList(player1,player2,player3,player4), session1);
		game6.setId(6);

		gameService.save(game6);
		verify(gameRepositoryMock, times(1)).save(game6);
	}
	
	@Test
	public void game_service_throws_when_creates_new_game_that_exists() {
		Bracket bracketE = new Bracket(5, 'E');
		Place alphaAuburn = 	new Place("Alpha Badminton Center, Auburn");

		Player player1 = new Player("Angel", 	"Ramos", bracketE);
		Player player2 = new Player("Anjo", 	"Alfon", bracketE);
		Player player3 = new Player("Karen", 	"Vega", bracketE);
		Player player4 = new Player("Matt", 	"Chanco", bracketE);

		SocialSession session1 = new SocialSession(LocalDate.of(2024, 1, 27), 4, alphaAuburn);

		Game game6 = new Game( Arrays.asList(player1,player2,player3,player4), session1);
		game6.setId(6);

		when(gameRepositoryMock.existsById(6)).thenReturn(true);
		assertThrows(RuntimeException.class, () -> gameService.save(game6));
		verify(gameRepositoryMock, times(1)).existsById(6);
		verify(gameRepositoryMock, times(0)).save(game6);
	}

	@Test
	public void game_service_deletes_game_that_exists() {
		when(gameRepositoryMock.existsById(1)).thenReturn(true);
		gameService.deleteById(1);
		verify(gameRepositoryMock, times(1)).existsById(1);
		verify(gameRepositoryMock, times(1)).deleteById(1);
	}

	@Test
	public void game_service_throws_when_delete_game_that_does_not_exist() {
		when(gameRepositoryMock.existsById(1)).thenReturn(false);
		assertThrows(RuntimeException.class, () -> gameService.deleteById(1));
		verify(gameRepositoryMock, times(0)).deleteById(1);
	}
}
