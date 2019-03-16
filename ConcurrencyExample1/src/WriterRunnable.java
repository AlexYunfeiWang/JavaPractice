
public class WriterRunnable implements Runnable {

	private Counter myCount;
	
	public WriterRunnable (Counter c) {
		this.myCount = c;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		for (int i = 0; i < 10; ++i) {
			try {
				Thread.sleep(1000);
			}
			catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("Current Thread: " + Thread.currentThread().getName());
			myCount.increase();
		}
	}

}
