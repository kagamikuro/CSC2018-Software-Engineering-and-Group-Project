package model;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import controller.Control;
import util.FileUtil;
import util.MyThread;

/**
 * 
 * Residential Buildings Level 1-5 Bungalows Shops Shopping Malls Commercial Buildings Skyscrapers
 * 
 * @author CSC2018 Group 31
 * 
 */
public class House extends Building {

	private int upPrice;
	private String[] nameString = { "Empty", "Bungalow", "Shop", "Mall", "Commercial Building", "Skyscraper" };

	private PlayerModel player;

	public House(int posX, int posY) {
		super(posX, posY);
		this.maxLevel = 5;
	}

	public int getUpLevelPrice() {
		if (this.level == 0) {
			this.upPrice = 500;
		} else {
			this.upPrice = 1000 * this.level;
		}
		return upPrice;
	}
	
	/**
	 * 
	 * get building all price
	 * 
	 * @return
	 */
	public int getAllPrice() {
		int price = 0;
		for (int i = 0; i <= level; i++) {
			if (this.level == 0) {
				price +=500;
			} else {
				price += 1000 * i;
			}
		}
		return price;
	}

	/**
	 * revenue
	 * 
	 * @return
	 */
	public int getRevenue() {
		/**
		 * the method to calculate 
		 */
		this.revenue = this.level * (int) (Math.random() * 1000)
				+ (this.level * 300);
		return revenue;
	}

	public String getName() {
		return this.nameString[this.level];
	}

	/**
	 * get up name
	 * 
	 * @return
	 */
	public String getUpName() {
		if (this.level >= this.nameString.length - 1) {
			return "null";
		}
		return this.nameString[this.level + 1];
	}

	@Override
	public int getEvent() {
		return GameState.HUOSE_EVENT;
	}
}
