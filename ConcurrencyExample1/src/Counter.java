
public class Counter {
	private int count;
	
	public Counter() {
		count = 0;
	}
	
	public synchronized void increase() {
		count++;
	}
	
	public synchronized int getVal() {
		return count;
	}
}
