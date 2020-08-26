package model;

import javax.swing.JOptionPane;

/**
 * 
 * 
 * rob card. After using Snatch card, you can snatch one card from an opponent. You can't see the opponent's card and try your luck to snatch one.
 * 
 * 
 */
public class RobCard extends Card {

	public RobCard(PlayerModel owner) {
		super(owner);
		this.name = "RobCard";
		this.cName = "qiangduoka";
		this.price = 50;
	}

	@Override
	public int useCard() {
		return GameState.CARD_ROB;
	}

}
