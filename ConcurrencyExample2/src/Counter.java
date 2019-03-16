
public class Counter {
	int count;
	
	public Counter() {
		count = 0;
	}
	
	public void increase() throws InterruptedException{
		synchronized(this) {
			for (int i = 0; i < 10; ++i) {
				System.out.println("increasing counter, incThread wait");
				count++;
				wait();
				
				System.out.println("incThread Resume after wait");
				
				notify();
			}
		}
	}
	
	public void getVal() throws InterruptedException {
		
		
		
		synchronized(this) {
			for (int i = 0; i < 10; ++i) {
				Thread.sleep(1000);
				System.out.println("get current val: " + count + ", getVal Thread wait");
				
				notify();
				
				wait();
				
				//Thread.sleep(1000);
			}
		}
		
	}
}
