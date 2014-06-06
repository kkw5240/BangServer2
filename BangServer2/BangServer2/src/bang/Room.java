package bang;
import java.io.Serializable;
import java.util.ArrayList;

import ocsf.server.AbstractServer;
import ocsf.server.ConnectionToClient;


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
		this.players = new ArrayList<>();
	}

	/** Getter & Setter Methods **/
	public int getNumberOfPlayer(){
		return players.size();
	}
	public int getNumber(){
		return number;
	}
	public Player getThisPlayer(ConnectionToClient cli) {
		Player p = null;
		
		int index = 0;
		for(Player tmp : players) {
			if(tmp.getConn2Cli().equals(cli)) {
				p = players.get(index);
				break;
			}
			index++;
		}
		
		return p;
	}
	
	/** Methods **/
	protected void handleMessageFromClient(Message m) {	// Msg Handler from Clients
		Thread t = new Thread(new HandleRoom(this,m));
		t.start();
	}
}
