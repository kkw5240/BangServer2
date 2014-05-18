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
		// Inside of the Room
		switch(m.getWhat()) {
		case 1://select
			break;
		case 2:
			break;
		}
	}

}
