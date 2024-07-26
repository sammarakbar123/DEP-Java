package Task4;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server {
    public static void main(String[] args) {
        try {
            Node node = new NodeImpl("Server");
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.bind("Node", node);
            ResourceManager resourceManager = new ResourceManager();
            resourceManager.addNode(node);
            System.out.println("Server started...");
        } catch (Exception e) {
            System.out.println("Server error: " + e.getMessage());
        }
    }
}
