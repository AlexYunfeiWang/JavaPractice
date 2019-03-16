
public class Driver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Counter c = new Counter();
		
		Runnable read = new ReaderRunnable(c);
		Runnable write = new WriterRunnable(c);
		
		Thread readThread = new Thread(read, "readThread");
		Thread writeThread = new Thread(write, "writeThread");
		
		writeThread.start();
		readThread.start();
		
		try {
			writeThread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Current Thread2: " + Thread.currentThread().getName());
		
		try {
			readThread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Finish");
	}

}
