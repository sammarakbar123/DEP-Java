package Task4;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Map;

public class ResourceManager {
    private Map<String, Node> nodes = new HashMap<>();

    public void addNode(Node node) throws RemoteException {
        nodes.put(node.getNodeName(), node);
    }

    public void shareResource(String resourceName, String targetNodeName) throws RemoteException {
        Node targetNode = nodes.get(targetNodeName);
        if (targetNode != null) {
            targetNode.shareResource(resourceName);
        }
    }
}