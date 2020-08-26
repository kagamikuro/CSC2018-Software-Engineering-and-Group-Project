package model;

import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import controller.GameRunning;

/**
 * 
 * 
 * shop model
 * 
 * @author CSC2018 Group 31
 * 
 */
public class Shop_ extends Building {

	/**
	 * 
	 * max item number
	 * 
	 */
	public static int MAXITEMSIZE = 9;
	/**
	 * 
	 * products shelf
	 * <p>
	 * max size 9
	 * 
	 */
	private List<Card> cards = new ArrayList<Card>(MAXITEMSIZE);

	private GameRunning running = null;

	private Shop_ shopUI;

	public Shop_(int posX, int posY) {
		super(posX, posY);
		this.name = "shop";
	}

	@Override
	public int getEvent() {
		return GameState.SHOP_EVENT;
	}

	/**
	 * 
	 * Regenerate products for store shelves
	 * 
	 */
	public void createCards() {
		// empty shelf
		this.cards = new ArrayList<Card>(MAXITEMSIZE);
		// add new card
		for (int i = 0; i < MAXITEMSIZE; i++) {
			int random = (int) (Math.random() * 12);
			switch (random) {	
			case 0:
				cards.add(new CrossingCard(null));
				break;
			case 1:
				cards.add(new ReduceLevelCard(null));
				break;
			case 2:
				cards.add(new RobCard(null));
				break;
			case 3:
				cards.add(new StopCard(null));
				break;
			case 4:
				cards.add(new TallageCard(null));
				break;
			case 5:
				cards.add(new TortoiseCard(null));
				break;
			case 6:
				cards.add(new TrapCard(null));
				break;
			}
		}
	}
	public List<Card> getCards() {
		return cards;
	}
}
