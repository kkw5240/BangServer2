/**
 * 
 */
package bang.card;

/**
 * @author		: PROP
 * @date		: 2014. 5. 25.
 * @explanation	: 
 */
public abstract class Card {
	public int far;		// When other user see me
	public int distance;	// When I see other user
	
	public abstract String does();
}
