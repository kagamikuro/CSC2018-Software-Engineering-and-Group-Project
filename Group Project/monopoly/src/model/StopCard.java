package model;

import javax.swing.JOptionPane;

/**
 * 
 * 
 * stop card, use the stop card against the opponent or yourself, you can make the target stay in place for one round.
 * 
 * 
 *
 */
public class StopCard extends Card{

	public StopCard(PlayerModel owner) {
		super(owner);
		this.name = "StopCard";
		this.cName ="tingliuka";
		this.price = 50;
	}

	@Override
	public int useCard() {
		return GameState.CARD_STOP;
	}
	/**
	 * 
	 *  card buff last
	 * 
	 */
	@Override
	public int cardBuff(){
		return GameState.CARD_BUFF_STOP;
	}
}
