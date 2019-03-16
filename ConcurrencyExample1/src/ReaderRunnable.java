
public class ReaderRunnable implements Runnable {
	
	private Counter myCounter;
	
	public ReaderRunnable(Counter c) {
		myCounter = c;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		for (int i = 0; i < 10; ++i) {
			try {
				Thread.sleep(1100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("Current Thread: " + Thread.currentThread().getName() 
					+ ", Count = " + myCounter.getVal());
		}
	}

}
