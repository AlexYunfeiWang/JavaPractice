
public class Driver {

	public static void main(String[] args) throws InterruptedException{
		// TODO Auto-generated method stub
		final PC pc = new PC();
		
		Thread produceThread = new Thread(new Runnable() {
			@Override
			public void run() 
            { 
                try
                { 
                    pc.produce(); 
                } 
                catch(InterruptedException e) 
                { 
                    e.printStackTrace(); 
                } 
            } 
		}, "produceThead");
		
		Thread consumeThread = new Thread(new Runnable() {
			@Override
            public void run() 
            { 
                try
                { 
                    pc.consume(); 
                } 
                catch(InterruptedException e) 
                { 
                    e.printStackTrace(); 
                } 
            } 
		});
		
		produceThread.start(); 
		consumeThread.start(); 
  
        // t1 finishes before t2 
		produceThread.join(); 
        consumeThread.join(); 
	}

}
