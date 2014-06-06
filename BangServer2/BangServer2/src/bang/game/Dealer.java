/**
 * 
 */
package bang.game;

import java.util.ArrayList;
import java.util.Random;

import bang.card.Card;
import bang.card.attack.*;
import bang.card.util.*;
import bang.card.equipped.*;

/**
 * @author		: PROP
 * @date		: 2014. 5. 29.
 * @explanation	: 
 */
public class Dealer {
	ArrayList<Card> mainDeck = new ArrayList<Card>();
	ArrayList<Card> dump = new ArrayList<Card>();
	
	/*
	public enum Cards {
		BANG(25), MISSED(12), BEER(2), INDIAN(3), GATTLING(4), 
		CATBALOW(5), PANIC(6), MUSTANG(7), SCOPE(8), VOLCANIC(9), 
		SCHOFIELD(10), REMINGTON(11), REVCARABINE(12), WINCHESTER(13), PRISON(14), 
		BARREL(15), DYNAMITE(16), DUEL(17), SALON(18), WELLSFARGO(19),  
		DILIGENCE(20), EMPORIUM(21);
		
		private int value;
		private Cards(int value) {
			this.value = value;
		}
	}
	*/
	
	public void initMainDeck() {
		int cnt_Bang = 25;
		int cnt_Missed = 12;
		
		Random r = new Random();
		/*
		 * 0	Bang			25
		 * 1	Missed			12
		 * 2	Beer			6
		 * 3	Indian			2
		 * 4	Gattling		1
		 * 5	Cat Balow		4
		 * 6	Panic			4
		 * 7	Mustang			2
		 * 8	Scope			1
		 * 9	Volcanic		2
		 * 10	Schofield		3
		 * 11	Remington		1
		 * 12	Rev.Carabine	1
		 * 13	Winchester		1
		 * 14	Prison			3
		 * 15	Barrel			2
		 * 16	Dynamite		1
		 * 17	Duel			3
		 * 18	Salon			1
		 * 19	WellsFargo		1
		 * 20	Diligence		2
		 * 21	Emporium		2
		 * */
		for(int i=0; i<37; i++) {
			int randomCard = r.nextInt(2);
			switch(randomCard) {
			case 0:
				if(cnt_Bang > 0) {
					mainDeck.add(new Bang());
					cnt_Bang--;
					break;
				}
			case 1:
				if(cnt_Missed > 0) {
					mainDeck.add(new Missed());
					cnt_Missed--;
					break;
				}
			}
		}
	}
	public int Draw() {
		
		return 0;
	}
}
