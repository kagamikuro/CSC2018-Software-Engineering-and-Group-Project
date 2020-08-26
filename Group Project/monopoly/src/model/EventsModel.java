package model;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

import controller.Control;
import util.FileUtil;

/**
 * 
 * event information
 * 
 * @author CSC2018 Group 31
 * 
 */
public class EventsModel extends Tick implements Port {

	/**
	 * image source
	 */
	private Image img = null;
	/**
	 * 
	 * Translucent background overlay : black
	 * 
	 */
	private Image BG_BRACK = new ImageIcon("images/event/bg_brack.png").getImage();
	/**
	 * image display position
	 */
	private Point imgPoint = null;
	private boolean imgShow = false;

	
	public Image getBG_BRACK() {
		return BG_BRACK;
	}

	public Image getImg() {
		return img;
	}

	public Point getImgPoint() {
		return imgPoint;
	}

	public boolean isImgShow() {
		return imgShow;
	}

	/**
	 * 
	 * display 
	 * 
	 */
	public void showImg(Image img, int time, Point point) {
		this.img = img;
		this.imgPoint = point;
		this.imgShow = true;
		this.setStartTick(this.nowTick);
		this.setNextTick(this.nowTick + time * Control.rate);
	}

	@Override
	public void updata(long tick) {
		this.nowTick = tick;
	}

	@Override
	public void startGameInit() {
	}
}
