package model;

import java.awt.Image;


public abstract class Card {

	/**
	 * 
	 * card name
	 * 
	 */
	protected String name;
	/**
	 * 
	 * card name
	 * 
	 */
	protected String cName;
	
	/**
	 * 
	 * card image
	 * 
	 */
	protected Image img;
	
	/**
	 * 
	 * card owner
	 * 
	 * 
	 */
	protected PlayerModel owner;
	
	/**
	 * 
	 * effect object
	 * 
	 */
	protected PlayerModel eOwner;
	
	/**
	 * 
	 * card price
	 * 
	 */
	protected int price = 100;
	
	protected Card (PlayerModel owner) {
		this.owner =owner;
	}
	
	/**
	 * 
	 * card use effect
	 * 
	 * 
	 */
	public abstract int useCard ();
	/**
	 * 
	 *  card buff last
	 * 
	 */
	public int cardBuff(){ return 0;}


	public String getName() {
		return name;
	}

	public Image getImg() {
		return img;
	}

	public PlayerModel getOwner() {
		return owner;
	}

	public void setOwner(PlayerModel owner) {
		this.owner = owner;
	}

	public int getPrice() {
		return price;
	}

	public String getcName() {
		return cName;
	}
	

	public PlayerModel geteOwner() {
		return eOwner;
	}

	public void seteOwner(PlayerModel eOwner) {
		this.eOwner = eOwner;
	}

	public void setcName(String cName) {
		this.cName = cName;
	}
	
	
}
