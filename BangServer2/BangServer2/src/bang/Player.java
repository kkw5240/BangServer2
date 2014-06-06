package bang;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import bang.card.Card;
import bang.card.equipped.Volcanic;
import ocsf.server.ConnectionToClient;

public class Player implements Serializable{
	/** Member Variable **/
	private ConnectionToClient c = null;
	private int room;
	private String id;
	private int hp;
	private ArrayList<Card> cards = new ArrayList<>();
	
	
	/** Constructor **/
	public Player(ConnectionToClient c, int roomNumber) {
		this.c = c;
		this.room = roomNumber;
		
		Message msg = new Message();
		msg.setRoomNumber(room);
		msg.setWhat(0);
		msg.setStr(this.toString());
		try {
			c.sendToClient(new MessageAdapter(null).toOcsfAble(msg));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	/** Getter&Setter Methods **/
	public ConnectionToClient getConn2Cli() {
		return c;
	}
	public void setConn2Cli(ConnectionToClient c) {
		this.c = c;
	}
	
	public int getRoomNumber() {
		return room;
	}
	public void setRoomNumber(int roomNumber) {
		this.room = roomNumber;
	}
	
	public String getId(){
		return id;
	}
	public void setId(String id){
		this.id = id;
	}
	
	public int getHp(){
		return hp;
	}
	public void setHp(int hp){
		this.hp = hp;
	}
	
	public void hp_up(){
		setHp(hp++);
	}
	public void hp_down(){
		setHp(hp--);
	}

	
	/** For Serialization **/
	/*
	public void writeObject(ObjectOutputStream stream)throws IOException{
		stream.defaultWriteObject();
	}
	public void readObject(ObjectInputStream stream)throws IOException{
			try {
				stream.defaultReadObject();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
	}
	*/
}
