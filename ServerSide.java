package Task2;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import Task2.ClientHandler;
import Task2.ClientSide;
public class ServerSide {
private ServerSocket serversocket;

public ServerSide(ServerSocket serversocket) {
	this.serversocket=serversocket;
}

public void StartServer(){
	try {
		while(!serversocket.isClosed()) {
			Socket socket=serversocket.accept();
			System.out.print("A new client is connected..");
			ClientHandler clienthandler=new ClientHandler(socket);
			
			Thread thread=new Thread(clienthandler);
			thread.start();
		}
		
	}
	catch(IOException e) {
		e.printStackTrace();
	}
	
}
public void StopServer() {
	try {
		if(serversocket!=null) {
			serversocket.close();
		}
	}
	catch(IOException e) {
		e.printStackTrace();
	}
}

public static void main(String args[] ) throws IOException  {
	ServerSocket serversocket = new ServerSocket( 1234);
	ServerSide server=new ServerSide(serversocket);
	server.StartServer();
	
			
	
}


}
