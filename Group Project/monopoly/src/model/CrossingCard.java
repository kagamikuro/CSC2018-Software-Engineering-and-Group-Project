package model;

/**
 * 
 * 
 * crossing card will take effect automatically when encountering danger, and can accuse others of framed or large rent (over 3,000).
 * 
 *
 */
public class CrossingCard extends Card{

	public CrossingCard(PlayerModel owner) {
		super(owner);
		this.name = "CrossingCard";
		this.cName = "jiahuoka";
		this.price = 120;
	}

	@Override
	public int useCard() {
		return GameState.CARD_CROSSING;
	}

}
