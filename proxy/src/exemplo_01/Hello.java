package exemplo_01;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Hello extends Remote { 
	
	public String Hello() throws RemoteException; 
	
}
