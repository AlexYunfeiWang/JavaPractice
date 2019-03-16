import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Server extends HelloImpl{
	public Server() {
		
	}
	
	public static void main(String args[]) {
		try {
			//Instantiating the implementation class
			HelloImpl obj = new HelloImpl();
			
			//Exporting the object of the implementation class
			//(here we are exporting the remote object to the stub)
			Hello stub = (Hello)UnicastRemoteObject.exportObject(obj, 0);
			
			//Binding the remote object (stub) in the registry
			Registry registry = LocateRegistry.getRegistry();
			
			registry.bind("Hello", stub);
			System.out.println("Server ready");
		}
		catch (Exception e) {
			System.out.println("Server Exception: " + e.toString());
			e.printStackTrace();
		}
	}
}
