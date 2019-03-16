import java.util.Random;

public class Player {
	private int Number;
	
	private Random Rand;
	
	public int getNumber() {
		return Number;
	}
	public void setNumber(int number) {
		Number = number;
	}
	
	public int Move() {
		if (Rand == null) {
			Rand = new Random(Number);
		}
		int spaces = Rand.nextInt(6) + 1;
		System.out.println("Player " + getNumber() + " spun a " + spaces);
		return spaces;
	}
}
