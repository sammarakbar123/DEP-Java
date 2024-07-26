package Task4;
import java.rmi.Remote;
import java.rmi.RemoteException;


public interface Node extends Remote {
    String getNodeName() throws RemoteException;
    void shareResource(String resource) throws RemoteException;
}

