package model;

import javax.swing.JOptionPane;

/**
 * 
 * Turtle card, using a turtle card against your opponent or yourself will make your opponent or yourself only take one step three times in a row.
 * 
 * 
 * 
 */
public class TortoiseCard extends Card {

	private int life = 3;

	public TortoiseCard(PlayerModel owner) {
		super(owner);
		this.name = "TortoiseCard";
		this.cName = "wuguika";
		this.price = 50;
	}

	@Override
	public int useCard() {
		return GameState.CARD_TORTOISE;
	}

	public int getLife() {
		return life;
	}

	public void setLife(int life) {
		this.life = life;
	}

	/**
	 * 
	 * card buff last
	 * 
	 */
	@Override
	public int cardBuff() {
		return GameState.CARD_BUFF_TORTOISE;
	}
}
