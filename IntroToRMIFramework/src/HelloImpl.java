import java.rmi.RemoteException;

public class HelloImpl implements Hello{

	@Override
	public void printMsg() throws RemoteException {
		// TODO Auto-generated method stub
		// Implementing the interface method 
	    System.out.println("This is an example RMI program");  
	}

}
