import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Board {
	public List<Square> Squares;
	public List<Player> Players;
	
	Map<Integer, Integer> ladderMap;
	Map<Integer, Integer> shootMap;
	
	public Board(int NumberOfPlayers) {
		Squares = new ArrayList<>();
		Players = new ArrayList<>();
		for (int i = 1; i < 3; ++i) {
			Player nextPlayer = new Player();
			nextPlayer.setNumber(i);
			Players.add(nextPlayer);
		}
		
		ladderMap = new HashMap<>();
		shootMap  = new HashMap<>();
		
		ladderMap.put(10, 18);
		ladderMap.put(24, 35);
		ladderMap.put(30, 40);
		ladderMap.put(41, 57);
		ladderMap.put(45, 55);
		ladderMap.put(48, 60);
		ladderMap.put(63, 70);
		ladderMap.put(80, 100);
		
		shootMap.put(20, 14);
		shootMap.put(32, 15);
		shootMap.put(50, 25);
		shootMap.put(51, 64);
		shootMap.put(61, 43);
		shootMap.put(78, 65);
		
		
		
		for (int j = 0; j <= 100; ++j) {
			Square newSquare = new Square();
			Squares.add(newSquare);
			
			if (ladderMap.containsKey(j)) {
				newSquare.LadderTo = ladderMap.get(j);
			}
			else if (shootMap.containsKey(j)) {
				newSquare.ShootTo = shootMap.get(j);
			}
		}
		
		Squares.get(0).Players.addAll(Players);
	}
}
