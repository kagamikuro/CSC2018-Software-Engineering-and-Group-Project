package model;

import javax.swing.JOptionPane;

/**
 * 
 * The tallage card is used directly on the opponent and collects 10% of the cash tax from the opponent¡£
 * 
 *
 */
public class TallageCard extends Card{

	public TallageCard(PlayerModel owner) {
		super(owner);
		this.name = "TallageCard";
		this.cName = "cahshuika";
		this.price = 100;
	}

	@Override
	public int useCard() {
		return GameState.CARD_TALLAGE;
	}

}
