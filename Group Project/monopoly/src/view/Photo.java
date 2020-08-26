package view;

import java.awt.Image;

import javax.swing.ImageIcon;
/**
 * 
 * set interface image class
 * 
 * */

public class Photo {
	
	/**
	 *  1p selected image
	 */
	public static ImageIcon PLAYER_01_SELECTED = new ImageIcon("images/config/playerChoose/selected_01.png");
	/**
	 *  2p select image
	 */
	public static ImageIcon PLAYER_02_SELECTED = new ImageIcon("images/config/playerChoose/selected_02.png");
	/**
	 *  left button
	 */
	public static ImageIcon[] BUTTON_LEFT = {
		new ImageIcon("images/config/left button/normal.png"),
		new ImageIcon("images/config/left button/disabled.png"),
		new ImageIcon("images/config/left button/mouseOver.png"),
		new ImageIcon("images/config/left button/pressed.png")
	};
	/**
	 *  right button
	 */
	public static ImageIcon[] BUTTON_RIGHT = {
		new ImageIcon("images/config/right button/normal.png"),
		new ImageIcon("images/config/right button/disabled.png"),
		new ImageIcon("images/config/right button/mouseOver.png"),
		new ImageIcon("images/config/right button/pressed.png")
	};
	/**
	 * player image
	 * */
	public static ImageIcon[] PLAYER_CHOOSE = {
		new ImageIcon("images/player/0/head_h5.png"),
		new ImageIcon("images/player/1/head_h5.png"),
		new ImageIcon("images/player/2/head_h5.png"),
		new ImageIcon("images/player/3/head_h5.png")
	};
}
