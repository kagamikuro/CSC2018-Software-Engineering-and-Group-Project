package model;

import javax.swing.JOptionPane;

/**
 * 
 * trap card, select an opponent after using the trap card, you can immediately put the opponent in jail for 2 days¡£
 * 
 */
public class TrapCard extends Card {

	public TrapCard(PlayerModel owner) {
		super(owner);
		this.name = "TrapCard";
		this.cName = "xianhaika";
		this.price = 120;
	}

	@Override
	public int useCard() {
		return GameState.CARD_TRAP;
	}

}
