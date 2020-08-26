package model;

import controller.Control;

/**
 * 
 * lottery
 * When the character arrives here, you can play a betting game, possibly winning or losing bonuses.
 * 
 * 
 * 
 * @author CSC2018 Group 31
 * 
 */
public class Lottery extends Building {

	private PlayerModel player;
	
	public Lottery(int posX, int posY) {
		super(posX, posY);
		this.name = "lottery";
	}
	
	@Override
	public int getEvent() {
		return GameState.LOTTERY_EVENT;
	}
}
