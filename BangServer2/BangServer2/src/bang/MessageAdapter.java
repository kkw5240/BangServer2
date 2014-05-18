/**
 * 
 */
package bang;

import java.util.StringTokenizer;

/**
 * @author		: PROP
 * @date		: 2014. 5. 19.
 * @explanation	: 
 */
public class MessageAdapter {
	EchoServer s = null;
	/**
	 * 
	 */
	public MessageAdapter(EchoServer s) {
		this.s = s;
	}
	
	public String toOcsfAble(Message msg) {
		String ocsfAble = msg.getRoomNumber()+"";
		ocsfAble += " ";
		if(msg.getRoomNumber() != -1){
			ocsfAble += msg.getTo().getId();
			ocsfAble += " ";
			ocsfAble += msg.getWhat();
			ocsfAble += " ";
			ocsfAble += msg.getFrom().getId();
			ocsfAble += " ";
		}
		ocsfAble += msg.getStr();
		
		return ocsfAble;
	}
	
	public Message toMsgAlbe(String ocsfMsg) {
		Message msg = new Message();
		StringTokenizer st = new StringTokenizer(ocsfMsg);
		msg.setRoomNumber(Integer.parseInt(st.nextToken()));
		Room r = s.getRoom(msg.getRoomNumber());
		String id = st.nextToken();
		msg.setWhat(Integer.parseInt((msg.getRoomNumber()==0 || msg.getWhat()==1)?id:st.nextToken()));
		if(!(msg.getRoomNumber()==0) && !(msg.getWhat()==1)){
			for(Player p : r.players){
				if(p.getId().equals(id)){
					msg.setTo(p);
					break;
				}
			}
			msg.setWhat(Integer.parseInt(st.nextToken()));
		}
		if(msg.getRoomNumber()>0){
			id = st.nextToken();
			for(Player p : r.players){
				if(p.getId().equals(id)){
					msg.setFrom(p);
					break;
				}
			}
		}
		msg.setStr(st.nextToken());
		
		return msg;
	}
}
