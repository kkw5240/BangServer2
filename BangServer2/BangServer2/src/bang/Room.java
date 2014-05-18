package bang;
import java.io.Serializable;
import java.util.ArrayList;

import ocsf.server.AbstractServer;


public class Room implements Serializable{
	/** Member Variable **/
	ArrayList<Player> players;
	int number;
	AbstractServer server;
	String title;
	
	/** Constructor **/
	public Room(int number, String title, AbstractServer server) {
		this.number = number;
		this.title = title;
		this.server = server;
	}

	/** Getter & Setter Methods **/
	int getNumberOfPlayer(){
		return players.size();
	}
	int getNumber(){
		return number;
	}
	
	/** Methods **/
	protected void handleMessageFromClient(Message m) {	// Msg Handler from Clients
		Thread t = new Thread(new HandleRoom(this,m));
		t.start();
	}
}
