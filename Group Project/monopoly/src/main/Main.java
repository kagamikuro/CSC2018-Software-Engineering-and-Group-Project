package main;


import javax.swing.JFrame;
import javax.swing.UIManager;

import view.FrameConfig;
import view.JFrameGame;
import view.WaitFrame;

public class Main {

	static {
		// set look and feel
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Exception e1) {
			
		}
	}

	public static void main(String[] args) {
		// create wait frame
		WaitFrame wFrame = new WaitFrame();
		// create game main window
		JFrameGame frame = new JFrameGame();
		// create game config window
		new FrameConfig(wFrame,frame);
	}
}
