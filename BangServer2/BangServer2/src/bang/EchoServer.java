package bang;// This file contains material supporting section 3.7 of the textbook:// "Object Oriented Software Engineering" and is issued under the open-source// license found at www.lloseng.com import java.io.*;import java.util.ArrayList;import ocsf.server.*;/** * This class overrides some of the methods in the abstract superclass in order * to give more functionality to the server. *  * @author Dr Timothy C. Lethbridge * @author Dr Robert Lagani&egrave;re * @author Fran&ccedil;ois B&eacute;langer * @author Paul Holden * @version July 2000 */public class EchoServer extends AbstractServer {	// Class variables *************************************************	/**	 * The default port to listen on.	 */	final public static int DEFAULT_PORT = 8087;	private ArrayList<Room> rooms;	private ArrayList<Integer> numbers;	private ArrayList<ConnectionToClient> clients;	// Constructors ****************************************************	/**	 * Constructs an instance of the echo server.	 * 	 * @param port	 *            The port number to connect on.	 */	public EchoServer(int port) {		super(port);		rooms = new ArrayList<Room>();		numbers = new ArrayList<Integer>();		clients = new ArrayList<ConnectionToClient>();	}	// Instance methods ************************************************	/**	 * This method handles any messages received from the client.	 * 	 * @param msg	 *            The message received from the client.	 * @param client	 *            The connection from which the message originated.	 */	public void handleMessageFromClient(Object obj, ConnectionToClient cli) {				//System.out.println("Message Received.");				MessageAdapter msgAdapter = new MessageAdapter(this);		Message msg = msgAdapter.toMsgAlbe((String)obj);		System.out.println("?");		System.out.println(msg.getRoomNumber());		System.out.println("??");				//if(msg.getRoomNumber() == 0){	// For Including Chatting 		if (msg.getRoomNumber() == 0) {	// Outside of the room			Message send = new Message();			switch (msg.getWhat()) {			case 1:// ����				System.out.println("RoomList");				try {					// send.setO(rooms);					cli.sendToClient(send);				} catch (IOException e) {					e.printStackTrace();				}				break;			case 2:// �游���				System.out.println("RoomCreated");				int roomNumber = allocNumber();				Player p = new Player(cli, roomNumber);				p.setId(p.toString());				Room r = new Room(roomNumber, msg.getStr(), this);				r.players.add(p);				rooms.add(r);				break;			case 3:// �� ��				System.out.println("Join the Room");				Player p1 = new Player(cli, Integer.parseInt(msg.getStr()));				p1.setId(p1.toString());				Room r1 = null;				if ((r1 = getRoom(p1.getRoomNumber())).players.size() < 7)					r1.players.add(p1);				break;			}		} else {			getRoom(msg.getRoomNumber()).handleMessageFromClient(msg);		}		/*}else{		// Chatting			getRoom(msg.getRoomNumber()).handleMessageFromClient(msg);		}*/				//System.out.println("Message received: " + obj + " from " + cli);		//this.sendToAllClients(obj);	}	/**	 * This method overrides the one in the superclass. Called when the server	 * starts listening for connections.	 */	protected void serverStarted() {		System.out.println("Server listening for connections on port "				+ getPort());	}	/**	 * This method overrides the one in the superclass. Called when the server	 * stops listening for connections.	 */	protected void serverStopped() {		System.out.println("Server has stopped listening for connections.");	}	protected void clientConnected(ConnectionToClient client) {		System.out.println(client +client.getName() + " Connected.");		clients.add(client);		super.clientConnected(client);	}	protected synchronized void clientDisconnected(ConnectionToClient client) {		System.out.println(client +client.getName() + " Disconnected.");		clients.remove(client);		for (Room r : rooms)			for (Player p : r.players)				if (p.getConn2Cli().equals(client)) {					r.players.remove(p);					try {						p.getConn2Cli().close();					} catch (IOException e) {						e.printStackTrace();					}					if (r.players.size() == 0)						rooms.remove(r);				}		super.clientDisconnected(client);	}	// Class methods ***************************************************	/**	 * This method is responsible for the creation of the server instance (there	 * is no UI in this phase).	 * 	 * @param args	 *            [0] The port number to listen on. Defaults to 5555 if no	 *            argument is entered.	 */	protected Room getRoom(int number) {		for (Room r : rooms)			if (r.getNumber() == number)				return r;		return null;	}	private int allocNumber() {		int i;		int check = -1;		for (i = 1; true; i++) {			for (check = 0; check < numbers.size(); check++) {				if (numbers.get(check) == i)					break;			}			if (check == numbers.size())				break;		}		numbers.add(i);		return i;	}	public static void main(String[] args) {		int port = 0; // Port to listen on		try {			port = Integer.parseInt(args[0]); // Get port from command line		} catch (Throwable t) {			port = DEFAULT_PORT; // Set port to 5555		}		EchoServer server = new EchoServer(port);		try {			server.listen(); // Start listening for connections		} catch (Exception ex) {			System.out.println("ERROR - Could not listen for clients!");		}	}}// End of EchoServer class