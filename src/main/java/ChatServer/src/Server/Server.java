package ChatServer.src.Server;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;


public class Server {

	private ServerSocket server;
	private Socket socket   = null;
	private DataInputStream in       =  null;
	private ArrayList<Connection> list = new ArrayList<Connection>();
	Connection c;
	String line = "";
	ArrayList<String> broadcastMsgList = new ArrayList<String>();

	String bMsg;
	public Server (int port) {
		try {
			server = new ServerSocket(port);
			System.out.println("Server has been initialised on port " + port);

				while (true){
					socket = server.accept();
					System.out.println("Client Accepted "+ socket);

					try {
						// obtain input and output streams
						DataInputStream dis = new DataInputStream(socket.getInputStream());
						DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
						c = new Connection(socket,this,dis,dos );
						Thread t = new Thread(c);

						t.start();
						list.add(c);
					} catch (IOException e) {

						e.printStackTrace();
					}

				}

		}
		catch (Exception e) {
			System.err.println("error initialising server");

		}


	}


	public ArrayList<String> getUserList() {
		ArrayList<String> userList = new ArrayList<String>();
		for( Connection clientThread: list){
			if(clientThread.getState() == Connection.STATE_REGISTERED) {
				userList.add(clientThread.getUserName());
			}
		}
		return userList;
	}
	
	public boolean doesUserExist(String newUser) {
		//boolean result = false;
		for( Connection clientThread: list){
			if(clientThread.getState() == Connection.STATE_REGISTERED) {
				if(clientThread.getUserName().equals(newUser)){
					return true;
				}
			}
		}
		return false;
	}


	public void broadcastMessage(String theMessage){
		//System.out.println(theMessage);
		bMsg = new String();
		broadcastMsgList.add(theMessage);
		for(String msg : broadcastMsgList){
			bMsg += msg + "\n";

		}
		System.out.println(bMsg);
		for( Connection clientThread: list){
			if(clientThread.getState() == Connection.STATE_REGISTERED){

				clientThread.messageForConnection(bMsg + System.lineSeparator());
			}
		}
	}
	
	public boolean sendPrivateMessage(String message, String user) {
		for( Connection clientThread: list) {
			if(clientThread.getState() == Connection.STATE_REGISTERED) {
				if(clientThread.getUserName().compareTo(user)==0) {
					clientThread.messageForConnection(message + System.lineSeparator());
					return true;
				}
			}
		}
		return false;
	}
	
	public void removeDeadUsers(){
		Iterator<Connection> it = list.iterator();
		while (it.hasNext()) {
			Connection c = it.next();
			if(!c.isRunning())
				it.remove();
		}
	}
	
	public int getNumberOfUsers() {
		int counter = 0;
		for( Connection clientThread: list){
			if(clientThread.getState() == Connection.STATE_REGISTERED){
				counter++;
			}
		}
		return counter;
	}

	public int connectedNumberOfUsers(){
		return list.size();
	}
	protected void finalize() throws IOException{
		server.close();
	}
		
}
