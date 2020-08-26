package model;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

import controller.GameRunning;
import util.FileUtil;
import view.JPanelGame;

/**
 * 
 * background refresh
 * 
 * @author CSC2018 Group 31
 *
 */
public class BackgroundModel extends Tick implements Port{
	/**
	 * background image
	 */
	private Image bg = null;
	public BackgroundModel (){
	}
	
	public Image getBg() {
		return bg;
	}
	
	public void setBg(Image bg) {
		this.bg = bg;
	}

	/**
	 * 
	 * game start setup
	 * 
	 */
	public void startGameInit (){
		this.bg = new ImageIcon("images/background/bg_0"+GameRunning.MAP+".jpg").getImage();
	}
	@Override
	public void updata(long tick) {
		this.nowTick = tick;
	}
}
