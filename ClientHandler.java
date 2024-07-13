package Task2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;
import Task2.ServerSide;
import Task2.ClientSide;
public class ClientHandler implements Runnable {

	public static ArrayList<ClientHandler> clienthandler=new ArrayList<>();
	private Socket socket;
	private BufferedReader bufferedreader;
	private BufferedWriter bufferedWriter;
	private String clientusername;
	public ClientHandler(Socket socket)  {
		try {
		this.socket=socket;
		this.bufferedWriter=new BufferedWriter( new OutputStreamWriter(socket.getOutputStream()));
		this.bufferedreader=new BufferedReader(new InputStreamReader(socket.getInputStream()));
		this.clientusername=bufferedreader.readLine();
		clienthandler.add(this);
		broadcastMessage("Server:"+clientusername+"has entered in chat");
		
	}catch(IOException e) {
		closeEverything(socket,bufferedreader,bufferedWriter);
		
	}
		}

	@Override
	public void run() {
		String messagefromClient;
		while(socket.isConnected()) {
			try {
				messagefromClient=bufferedreader.readLine();
				broadcastMessage(messagefromClient);
			}catch(IOException e) {
				closeEverything(socket,bufferedreader,bufferedWriter);
				break;
			}
		}
		
		// TODO Auto-generated method stub
		
	}
	public void broadcastMessage(String messageToSend) {
		for(ClientHandler clienthandler:clienthandler) {
			try {
				if(!clienthandler.clientusername.equals(clientusername)) {
					clienthandler.bufferedWriter.write(messageToSend);
					clienthandler.bufferedWriter.newLine();
					clienthandler.bufferedWriter.flush();
				}
			}catch(IOException e) {
				closeEverything(socket,bufferedreader,bufferedWriter);
			}
		}
	}
	
public void removeclientmessage() {
	clienthandler.remove(this);
	broadcastMessage("Server:" + clientusername +"has left the chat");
	
}
private void closeEverything(Socket socket, BufferedReader bufferedreader, BufferedWriter bufferedWriter) {
 removeclientmessage();
 try {
	 if(bufferedreader!=null) {
		 bufferedreader.close();
	 }
	 if(bufferedWriter!=null) {
		 bufferedWriter.close();
	 }
	 if(socket!=null) {
		 socket.close();
	 }
 }catch(IOException e) {
	 e.printStackTrace();
 }
	
}

}
