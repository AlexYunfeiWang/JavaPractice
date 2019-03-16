import java.util.Scanner;

public class Driver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		boolean WantToPlay = true;
		
		
		do {
			System.out.println("Welcome to shoots and ladders! How many players? ");
			Scanner scan = new Scanner(System.in);
			int num = scan.nextInt();
			
			if (num < 1 || num > 2) {
				System.out.println("Player number should be 1 or 2");
				return;
			}
			
			Board board = new Board(num);
			System.out.println("Number of squares: " + board.Squares.size());
			while(board.Squares.get(board.Squares.size()-1).Players.size() == 0) {
				for (Player curPlayer : board.Players) {
					int curSquareIndex = findCurSquareIdx(board, curPlayer);
					Square curSquare = board.Squares.get(curSquareIndex);
					int move = curPlayer.Move();
					
					int nextSquareIndex = curSquareIndex + move;
					
					if (nextSquareIndex >= board.Squares.size()) {
						nextSquareIndex = curSquareIndex;
					}
					curSquare.Players.remove(curPlayer);
					System.out.println("Player " + curPlayer.getNumber() + " has moved to Square " + nextSquareIndex);
					
					if (board.Squares.get(nextSquareIndex).LadderTo != null) {
						nextSquareIndex = board.Squares.get(nextSquareIndex).LadderTo;
						System.out.println("You took a ladder to " + nextSquareIndex);
					}
					
					if (board.Squares.get(nextSquareIndex).ShootTo != null) {
						nextSquareIndex = board.Squares.get(nextSquareIndex).ShootTo;
						System.out.println("You took a shoot to " + nextSquareIndex);
					}
					board.Squares.get(nextSquareIndex).Players.add(curPlayer);
				}
				
				if (board.Squares.get(board.Squares.size()-1).Players.size() != 0) {
					int winner = board.Squares.get(board.Squares.size()-1).Players.get(0).getNumber();
					
					System.out.println("Player " + winner + " wins the game!");
					System.out.println("Would you like to play again? Y/N");
					scan = new Scanner(System.in);
					String playAgain = scan.nextLine();
					
					if (playAgain.trim().toUpperCase().equals("N")) {
						WantToPlay = false;
						scan.close();
						System.out.println("Thank you for playing, Goodbye");
					}
				}
				
			}
		} while(WantToPlay);
		
	}
	
	public static int findCurSquareIdx(Board board, Player curPlayer) {
		int targetIdx = -1;
		for (int i = 0; i < board.Squares.size(); ++i) {
			Square sq = board.Squares.get(i);
			if (sq.Players.size() > 0) {
				for (Player p : sq.Players) {
					if (p.getNumber() == curPlayer.getNumber()) {
						targetIdx = i;
						break;
					}
				}
			}
		}
		return targetIdx;
	}

}
