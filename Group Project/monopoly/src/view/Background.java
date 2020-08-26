package view;

import java.awt.Graphics;
import java.awt.Image;

import util.FileUtil;

import model.BackgroundModel;


/**
 * 
 * background update layer
 * 
 * @author CSC2018 Group 31
 * 
 */
public class Background extends Layer {

	/**
	 * background image
	 */
	private Image bg = null;
	/**
	 * 
	 * background model
	 * 
	 */
	private BackgroundModel background = null;
	private JPanelGame panel;

	protected Background(int x, int y, int w, int h,
			BackgroundModel background,JPanelGame panel) {
		super(x, y, w, h);
		this.background = background;
		this.panel = panel;
	}

	public void paint(Graphics g) {
		// draw background
		this.paintBg(g);
	}
	/**
	 * 
	 * hide window
	 * 
	 */
	public void moveToBack() {
		this.panel.getLayeredPane().moveToBack(this);
	}

	/**
	 * 
	 * display window
	 * 
	 */
	public void moveToFront() {
		this.panel.getLayeredPane().moveToFront(this);
	}
	
	/**
	 * 
	 * background paint method
	 * 
	 */
	public void paintBg(Graphics g){
		g.drawImage(this.bg, 0, 0, this.bg.getWidth(null),
				this.bg.getHeight(null), 0, 0, this.bg.getWidth(null),
				this.bg.getHeight(null), null);
	}
	

	@Override
	public void startPanel() {
		this.bg = background.getBg();
	}

}
