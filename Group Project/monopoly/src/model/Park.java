package model;

import java.awt.Image;

import javax.swing.ImageIcon;

import controller.Control;

/**
 * 
 * park  that is indisputable in the world, no big things will happen to the role here
 * 
 * 
 * @author CSC2018 Group 31
 * 
 */
public class Park extends Building {
	/**
	 * 
	 * event image
	 */
	private Image[] imgageEvents = { EVENT_PARK_1, EVENT_PARK_2, EVENT_PARK_3,
			EVENT_PARK_4 };
	/**
	 * event image
	 */
	public static Image EVENT_PARK_1 = new ImageIcon("images/event/park01.jpg")
			.getImage();
	/**
	 * event image
	 */
	public static Image EVENT_PARK_2 = new ImageIcon("images/event/park02.jpg")
			.getImage();
	/**
	 * event image
	 */
	public static Image EVENT_PARK_3 = new ImageIcon("images/event/park03.jpg")
			.getImage();
	/**
	 * event image
	 */
	public static Image EVENT_PARK_4 = new ImageIcon("images/event/park04.jpg")
			.getImage();


	public Park(int posX, int posY) {
		super(posX, posY);
		this.name = "park";
	}
	
	public Image[] getImgageEvents() {
		return imgageEvents;
	}

	@Override
	public int getEvent() {
		return GameState.PARK_EVENT;
	}

}
