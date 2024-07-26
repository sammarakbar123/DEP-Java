package Task4;
import java.rmi.Naming;
import java.rmi.RemoteException;

	public class Client {
	    public static void main(String[] args) {
	        try {
	            Node node = (Node) Naming.lookup("rmi://localhost/SharedResource");
	            ResourceManager resourceManager = new ResourceManager();
	            resourceManager.shareResource("Hello from Client!", "Server");
	            System.out.println("Client sent resource...");
	        } catch (Exception e) {
	            System.out.println("Client error: " + e.getMessage());
	        }
	    }
	}


