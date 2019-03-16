
public class Driver {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		final Counter myCounter = new Counter();
		
		Thread incThread = new Thread(new Runnable() {
			@Override
			public void run() 
            { 
                try
                { 
                	myCounter.increase(); 
                } 
                catch(InterruptedException e) 
                { 
                    e.printStackTrace(); 
                } 
            } 
		});
		
		Thread readThread = new Thread(new Runnable() {
			@Override
            public void run() 
            { 
                try
                { 
                	myCounter.getVal(); 
                } 
                catch(InterruptedException e) 
                { 
                    e.printStackTrace(); 
                } 
            } 
		});
		
		incThread.start(); 
		readThread.start(); 
  
        // t1 finishes before t2 
		incThread.join(); 
		readThread.join(); 
		
		System.out.println("-----------------Finished\n");
		
		Thread lambda = new Thread(() -> {System.out.println("This is an example of functional creating a thread");});
		
		lambda.start();
		lambda.join();
		
		System.out.println("The only remaining thread is main thread");
	}

}
