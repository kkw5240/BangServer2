package bang;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Message implements Serializable{
	/** Member Variable **/
	private int room = 0;
	private Player to;
	private int what;
	private Player from;
	private String str;
	
	
	/** Getter&Setter Methods **/
	public int getRoomNumber() {
		return room;
	}
	public void setRoomNumber(int roomNumber) {
		this.room = roomNumber;
	}
	
	public Player getTo() {
		return to;
	}
	public void setTo(Player to) {
		this.to = to;
	}

	public int getWhat() {
		return what;
	}
	public void setWhat(int what) {
		this.what = what;
	}

	public Player getFrom() {
		return from;
	}
	public void setFrom(Player from) {
		this.from = from;
	}

	public String getStr() {
		return str;
	}
	public void setStr(String str) {
		this.str = str;
	}

	
	/** For Serialization **/
	public void writeObject(ObjectOutputStream stream)throws IOException{
		stream.writeObject(to);
		stream.writeObject(from);
		stream.defaultWriteObject();
	}
	public void readObject(ObjectInputStream stream)throws IOException{
		try {
			Player p = new Player(null, 0);
			p.readObject(stream);
			to = p;
			p = new Player(null, 0);
			p.readObject(stream);
			from = p;
			stream.defaultReadObject();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
