package model;

import java.awt.Image;
import java.awt.Point;

import javax.swing.ImageIcon;

import controller.GameRunning;

/**
 * land
 * 
 * 
 * @author CSC2018 Group 31
 * 
 */
public class LandModel extends Tick implements Port {
	/**
	 * 
	 * land image
	 * 
	 */
	private Image landsIMG = null;
	/**
	 * space
	 */
	public final static int SPACE = 1;
	/**
	 * 10 points
	 */
	public final static int PIONT_10 = 2;
	/**
	 * 30 points
	 */
	public final static int PIONT_30 = 3;
	/**
	 * 50 points
	 */
	public final static int PIONT_50 = 4;
	/**
	 * shop
	 */
	public final static int SHOP = 5;
	/**
	 * lottery
	 */
	public final static int LOTTERY = 6;
	/**
	 * news
	 */
	public final static int NEWS = 7;
	/**
	 * hostipal
	 */
	public final static int HOSPITAL = 8;
	/**
	 * park
	 */
	public final static int PARK = 9;
	/**
	 * origin
	 */
	public final static int ORIGIN = 10;
	/**
	 * prison
	 */
	public final static int PRISON = 11;
	/**
	 * no building
	 */
	public final static int NULL_SET = 0;
	/**
	 * hospital information
	 */
	public static Point hospital = new Point(0, 0);
	/**
	 * prison information
	 */
	public static Point prison = new Point(0, 0);

	public LandModel() {
		this.landsIMG = new ImageIcon("images/window/land.jpg").getImage();
	}

	public Image getLandsIMG() {
		return landsIMG;
	}

	public void setLandsIMG(Image landsIMG) {
		this.landsIMG = landsIMG;
	}
	
	 protected int[][] land3 = {
			 // map setting 1
			 { ORIGIN, SHOP, NEWS, SPACE, NEWS, SPACE, NEWS, PRISON,NEWS, SPACE, NEWS, SPACE, NEWS },
				{ PIONT_30, NULL_SET, NULL_SET, NULL_SET, NULL_SET, NULL_SET, NULL_SET, NULL_SET,NULL_SET, NULL_SET, NULL_SET, NULL_SET, PIONT_30 },
				{ PIONT_50, NULL_SET, NULL_SET, NULL_SET, NULL_SET, NULL_SET, NULL_SET, NULL_SET,NULL_SET, NULL_SET, NULL_SET, NULL_SET, PIONT_50 },
				{ PIONT_10, NULL_SET, NULL_SET, NULL_SET, NULL_SET, NULL_SET, NULL_SET, NULL_SET,NULL_SET, NULL_SET, NULL_SET, NULL_SET, PIONT_10 },
				{ PIONT_50, NULL_SET, NULL_SET, NULL_SET, NULL_SET, NULL_SET, NULL_SET, NULL_SET,NULL_SET, NULL_SET, NULL_SET, NULL_SET, PIONT_50 },
				{ PIONT_30, NULL_SET, NULL_SET, NULL_SET, NULL_SET, NULL_SET, NULL_SET, NULL_SET,NULL_SET, NULL_SET, NULL_SET, NULL_SET, PIONT_10 },
				{ PIONT_50, NULL_SET, NULL_SET, NULL_SET, NULL_SET, NULL_SET, NULL_SET, NULL_SET,NULL_SET, NULL_SET, NULL_SET, NULL_SET, PIONT_50 },
				{ ORIGIN, NEWS, SPACE, NEWS, SPACE, NEWS, SPACE, NEWS,HOSPITAL, NEWS, SPACE, SHOP, SPACE }};
	 
	 protected int[][] land2 = {
	 // map setting 2
			 { ORIGIN, SHOP, SPACE, SPACE, PIONT_50, NEWS, PRISON, SPACE,SPACE, SPACE, PIONT_50, SPACE, NEWS },
				{ SPACE, NULL_SET, NULL_SET, NULL_SET, NULL_SET, NULL_SET, NULL_SET, NULL_SET,NULL_SET, NULL_SET, NULL_SET, NULL_SET, SPACE },
				{ PARK, NULL_SET, NULL_SET, NULL_SET, NEWS, SPACE, SPACE, SPACE,SHOP, SPACE, PIONT_50, SPACE, HOSPITAL },
				{ PIONT_50, NULL_SET, NULL_SET, NULL_SET, SPACE, NULL_SET, NULL_SET, NULL_SET,NULL_SET, NULL_SET, NULL_SET, NULL_SET, SPACE },
				{ SPACE, NULL_SET, NULL_SET, NULL_SET, SHOP, PIONT_10, PIONT_30, PIONT_10,PARK, SPACE, SPACE, SPACE, PIONT_50 },
				{ SPACE, NULL_SET, NULL_SET, NULL_SET, SPACE, NULL_SET, NULL_SET, NULL_SET,SPACE, NULL_SET, NULL_SET, NULL_SET, NULL_SET },
				{ PIONT_30, NULL_SET, NULL_SET, NULL_SET, SPACE, NULL_SET, NULL_SET, NULL_SET,SPACE, NULL_SET, NULL_SET, NULL_SET, NULL_SET },
				{ PRISON, SPACE, SPACE, HOSPITAL, NEWS, SPACE, PARK, SPACE,NEWS, NULL_SET, NULL_SET, NULL_SET, NULL_SET }};

	 //{ ORIGIN, SPACE, HOSPITAL, PIONT_50, SHOP, SPACE, PRISON, SPACE,
	 protected int[][] land1 = {
	 // map setting 3
	{ ORIGIN, SPACE, HOSPITAL, PIONT_50, SHOP, SPACE, PRISON, SPACE,
	 SPACE, PARK, NULL_SET, NULL_SET, NULL_SET },
	 { SPACE, NULL_SET, NULL_SET, NULL_SET, SPACE, NULL_SET, NULL_SET,
	 NULL_SET, NULL_SET, PIONT_30, SPACE, SPACE, NEWS },
	 { SPACE, NULL_SET, NULL_SET, NULL_SET, SPACE, NULL_SET, NULL_SET,
	 NULL_SET, NULL_SET, NULL_SET, NULL_SET, NULL_SET, SPACE },
	 { SPACE, NULL_SET, NULL_SET, NULL_SET, SPACE, NULL_SET, NULL_SET,
	 NULL_SET, NULL_SET, NULL_SET, NULL_SET, NULL_SET, SPACE },
	 { SPACE, NULL_SET, NULL_SET, NULL_SET, SPACE, NULL_SET, NULL_SET,
	 NULL_SET, NULL_SET, NULL_SET, NULL_SET, NULL_SET, SPACE },
	 { SPACE, NULL_SET, NULL_SET, NULL_SET, SPACE, NULL_SET, NULL_SET,
	 NULL_SET, NULL_SET, NULL_SET, NULL_SET, NULL_SET, SPACE },
	 { PARK, NULL_SET, NULL_SET, NULL_SET, PIONT_30, NULL_SET, NULL_SET,
	 NULL_SET, NULL_SET, NULL_SET, NULL_SET, NULL_SET, SPACE },
	 
	 { NEWS, PIONT_30, SPACE, SPACE, SHOP, PIONT_10, SPACE, SPACE,
	 SPACE, SPACE, HOSPITAL, PIONT_50, SPACE } };

	 protected int[][] land;
	 
	public int[][] getLand() {
		return land;
	}

	/**
	 * map matching event
	 * */
	public int matchLand(PlayerModel player) {
		return land[player.getY()][player.getX()];
	}

	/**
	 * 
	 * game start setup
	 * 
	 */
	public void startGameInit() {
		if (GameRunning.MAP == 1){
			land = land1;
		} else if (GameRunning.MAP == 2){
			land = land2;
		} else if (GameRunning.MAP == 3) {
			land = land3;
		}
	}

	@Override
	public void updata(long tick) {
		this.nowTick = tick;

	}
}
