package model;

import controller.Control;

/**
 * 
 * origin player got money when pass
 * 
 * 
 * @author CSC2018 Group 31
 * 
 */
public class Origin extends Building {
	/**
	 * the reward when pass
	 */
	private int passReward;
	/**
	 * the reward when stay
	 */
	private int reward;

	private PlayerModel player;

	public Origin(int posX, int posY) {
		super(posX, posY);
		this.name = "origin";
		this.reward = 500;
		this.passReward = 200;
	}
	@Override
	public int getEvent() {
		return GameState.ORIGIN_EVENT;
	}
	
	public int getPassReward() {
		return passReward;
	}
	public int getReward() {
		return reward;
	}
	@Override
	public int passEvent() {
		return GameState.ORIGIN_PASS_EVENT;
	}
}
