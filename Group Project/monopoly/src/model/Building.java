package model;

import java.awt.Graphics;

import controller.Control;

/**
 * 
 * building class
 * 
 * @author CSC2018 Group 31
 * 
 */
public class Building{
	/**
	 * 
	 * building owner
	 * 
	 */
	protected PlayerModel owner = null;

	/**
	 * building name
	 */
	protected String name;

	/**
	 * purchase ability
	 */
	protected boolean purchasability = false;

	/**
	 * price
	 */
	protected int price;
	/**
	 * revenue
	 */
	protected int revenue;
	/**
	 * current building level
	 */
	protected int level;

	/**
	 * 
	 * Coordinate
	 * 
	 */
	protected int posX;
	protected int posY;
	/**
	 * max level
	 */
	protected int maxLevel;

	
	public Building(int posX, int posY) {
		this.posX = posX;
		this.posY = posY;
	}

	public boolean isPurchasability() {
		return purchasability;
	}

	public void setPurchasability(boolean purchasability) {
		this.purchasability = purchasability;
	}

	/**
	 * if the building can level up
	 */
	public boolean canUpLevel() {
		return this.level < maxLevel;
	}

	public PlayerModel getOwner() {
		return owner;
	}

	public void setOwner(PlayerModel owner) {
		this.owner = owner;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getName() {
		return name;
	}
	public String getUpName() {
		return name;
	}

	public int getUpLevelPrice() {
		return price;
	}
	/**
	 * 
	 * get all building price
	 * 
	 */
	public int getAllPrice() {
		return 0;
	}
	public int getRevenue() {
		return revenue;
	}

	public int getPosX() {
		return posX;
	}

	public int getPosY() {
		return posY;
	}
	/**
	 * trigger event
	 */
	public int getEvent() { return 0;}
	
	/**
	 * pass event
	 */
	public int passEvent() { return 0;}
	
	public void paint(Graphics g){}

	
}	
