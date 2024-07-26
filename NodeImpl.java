package Task4;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class NodeImpl extends UnicastRemoteObject implements Node {
    private String nodeName;

    public NodeImpl(String nodeName) throws RemoteException {
        this.nodeName = nodeName;
    }

    @Override
    public String getNodeName() throws RemoteException {
        return nodeName;
    }

    @Override
    public void shareResource(String resource) throws RemoteException {
        System.out.println(nodeName + " received resource: " + resource);
    }
}

