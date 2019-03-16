import java.rmi.Remote;
import java.rmi.RemoteException;

/*
 * This program is developed under the tutorial of: 
 * https://www.tutorialspoint.com/java_rmi/java_rmi_application.htm
 */

public interface Hello extends Remote {
	// Creating Remote interface for our application 
	public void printMsg() throws RemoteException;
}
