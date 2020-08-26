package model;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

import controller.Control;
import util.FileUtil;

/**
 * 
 * news  testing players¡¯ luck, random events will bring players a variety of unexpected situations
 * 
 * 
 * @author CSC2018 Group 31
 * 
 */
public class News extends Building {
	/*
	 * 
	 * event image
	 */
	private Image[] imgageEvents = { EVENT_BAD_HOSPITAL_3_01,
			EVENT_BAD_HOSPITAL_3_02, EVENT_BAD_LOSE_1000_01,
			EVENT_BAD_LOSE_1000_02, EVENT_BAD_LOSE_1500_01,
			EVENT_BAD_LOSE_2000_01, EVENT_BAD_LOSE_300_01,
			EVENT_BAD_LOSE_300_02, EVENT_BAD_LOSE_400_01,
			EVENT_BAD_LOSE_40S_01, EVENT_BAD_LOSE_500_01,
			EVENT_LUCK_GAIN_1000_01, EVENT_LUCK_GAIN_2000_01,
			EVENT_LUCK_GAIN_2000_02, EVENT_LUCK_GAIN_3999_100S,
			EVENT_LUCK_GAIN_400S_01,EVENT_LUCK_LOSE_3000 };
	/**
	 * event image
	 */
	public static Image EVENT_LUCK_LOSE_3000 = new ImageIcon(
			"images/event/bad_lose3000.jpg").getImage();
	/**
	 * event image
	 * 
	 */
	public static Image EVENT_LUCK_LOSE_3000_W = new ImageIcon(
			"images/event/bad_lose3000_w.jpg").getImage();
	/**
	 * event image
	 */
	public static Image EVENT_BAD_HOSPITAL_3_01 = new ImageIcon(
			"images/event/bad_hospital_3_01.jpg").getImage();
	/**
	 * event image
	 */
	public static Image EVENT_BAD_HOSPITAL_3_02 = new ImageIcon(
			"images/event/bad_hospital_3.jpg").getImage();
	/**
	 * event image
	 */
	public static Image EVENT_BAD_LOSE_1000_01 = new ImageIcon(
			"images/event/bad_lose1000_01.jpg").getImage();
	/**
	 * event image
	 */
	public static Image EVENT_BAD_LOSE_1000_02 = new ImageIcon(
			"images/event/bad_lose1000.jpg").getImage();
	/**
	 * event image
	 */
	public static Image EVENT_BAD_LOSE_1500_01 = new ImageIcon(
			"images/event/bad_lose1500.jpg").getImage();
	/**
	 * event image
	 */
	public static Image EVENT_BAD_LOSE_2000_01 = new ImageIcon(
			"images/event/bad_lose2000.jpg").getImage();
	/**
	 * event image
	 */
	public static Image EVENT_BAD_LOSE_300_01 = new ImageIcon(
			"images/event/bad_lose300_01.jpg").getImage();
	/**
	 * event image
	 */
	public static Image EVENT_BAD_LOSE_300_02 = new ImageIcon(
			"images/event/bad_lose300.jpg").getImage();
	/**
	 * event image
	 */
	public static Image EVENT_BAD_LOSE_400_01 = new ImageIcon(
			"images/event/bad_lose400.jpg").getImage();
	/**
	 * event image
	 */
	public static Image EVENT_BAD_LOSE_40S_01 = new ImageIcon(
			"images/event/bad_lose40s.jpg").getImage();
	/**
	 * event image
	 */
	public static Image EVENT_BAD_LOSE_500_01 = new ImageIcon(
			"images/event/bad_lose500.jpg").getImage();
	/**
	 * event image
	 */
	public static Image EVENT_LUCK_GAIN_1000_01 = new ImageIcon(
			"images/event/luck_gain1000.jpg").getImage();
	/**
	 * event image
	 */
	public static Image EVENT_LUCK_GAIN_2000_01 = new ImageIcon(
			"images/event/luck_gain2000_01.jpg").getImage();
	/**
	 * event image
	 */
	public static Image EVENT_LUCK_GAIN_2000_02 = new ImageIcon(
			"images/event/luck_gain2000.jpg").getImage();
	/**
	 * event image
	 */
	public static Image EVENT_LUCK_GAIN_3999_100S = new ImageIcon(
			"images/event/luck_gain3999_100.jpg").getImage();

	/**
	 * event image
	 */
	public static Image EVENT_LUCK_GAIN_400S_01 = new ImageIcon(
			"images/event/luck_gain400s.jpg").getImage();


	public News(int posX, int posY) {
		super(posX, posY);
		this.name = "news";
	}

	public Image[] getImgageEvents() {
		return imgageEvents;
	}
	public Image get3000() {
		return EVENT_LUCK_LOSE_3000_W;
	}
	
	@Override
	public int getEvent() {
		return GameState.NEWS_EVENT;
	}
}
