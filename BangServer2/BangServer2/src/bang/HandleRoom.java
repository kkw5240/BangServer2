package bang;

public class HandleRoom implements Runnable {

	Room r;
	Message m;
	
	public HandleRoom(Room r,Message m) {
		this.r = r;
		this.m = m;
	}
	
	@Override
	public void run() {
		
	}

}
