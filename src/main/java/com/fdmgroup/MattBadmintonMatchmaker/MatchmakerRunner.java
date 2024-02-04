package com.fdmgroup.MattBadmintonMatchmaker;

import java.util.ArrayList;

import com.fdmgroup.MattBadmintonMatchmaker.model.Bracket;
import com.fdmgroup.MattBadmintonMatchmaker.model.Player;
import com.fdmgroup.MattBadmintonMatchmaker.service.MatchmakerService;

// For testing matchmaker service
public class MatchmakerRunner {

	public static void main(String[] args) {
		
		MatchmakerService matchmaker = new MatchmakerService();
		
		Bracket bracketA = new Bracket(1, 'A');
		Bracket bracketB = new Bracket(2, 'B');
		Bracket bracketC = new Bracket(3, 'C');
		Bracket bracketD = new Bracket(4, 'D');
		Bracket bracketE = new Bracket(5, 'E');
		
		
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
		
		Player[][] matches = matchmaker.generateEvenGames(players);
		
		for (int i = 0; i < matches.length ; i++) {
			for (int j = 0 ; j < matches[i].length ; j++) {
				System.out.print(matches[i][j].getFirstName() + " ");
			}
			System.out.println();
		}
	}

}
