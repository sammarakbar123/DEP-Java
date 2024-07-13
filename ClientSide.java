package Task2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
import Task2.ClientHandler;
import Task2.ServerSide;
public class ClientSide {
private Socket socket;
private BufferedReader bufferedreader;
private BufferedWriter bufferedWriter;
private String username;
public ClientSide(Socket socket,String username) {
	try {
		this.socket=socket;
		this.bufferedWriter=new BufferedWriter( new OutputStreamWriter(socket.getOutputStream()));
		this.bufferedreader=new BufferedReader(new InputStreamReader(socket.getInputStream()));
		this.username=username;
	}catch(IOException e) {
		closeEverything(socket,bufferedreader,bufferedWriter);
	}
}
public void sendmessage() {
try {
	bufferedWriter.write(username);
	bufferedWriter.newLine();
	bufferedWriter.flush();
	
	Scanner s=new Scanner(System.in);
	while(socket.isConnected()) {
		String messageToSend=s.nextLine();
		bufferedWriter.write(username +":"+messageToSend);
		bufferedWriter.newLine();
		bufferedWriter.flush();
	}
}catch(IOException e) {
	closeEverything(socket,bufferedreader,bufferedWriter);
}
}

public void ListenForMessage() {
	new Thread(new Runnable() {
		public void run() {
			String messfromgroupchat;
			while(socket.isConnected()) {
				try{
					messfromgroupchat=bufferedreader.readLine();
					System.out.println(messfromgroupchat);
				}catch(IOException e) {
					closeEverything(socket,bufferedreader,bufferedWriter);
				}
			}
		}
	}
			),StartServer();
	
}
public void closeEverything(Socket socket,BufferedReader bufferedreader,BufferedWriter bufferedWriter) {
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

public static void main(String args[]) throws UnknownHostException, IOException {
	Scanner s=new Scanner(System.in);
	System.out.println("Enter your username for gc:");
	String username=s.nextLine();
	Socket socket = new Socket(InetAddress.getLocalHost(), 1234);
	ClientSide client=new ClientSide(socket,username);
	client.ListenForMessage();
	client.sendmessage();
	
}
}
