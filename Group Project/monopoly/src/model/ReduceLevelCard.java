package model;

import javax.swing.JOptionPane;

/**
 * 
 * ReduceLevelCard, the current house is down one level (opposing)
 * OK
 *
 */
public class ReduceLevelCard extends Card{

	public ReduceLevelCard(PlayerModel owner) {
		super(owner);
		this.name = "ReduceLevelCard";
		this.cName = "jiangjika";
		this.price = 30;
	}

	@Override
	public int useCard() {
		return GameState.CARD_REDUCELEVEL;
		}

}
