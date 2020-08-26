package view;

import javax.swing.JFrame;

import util.FrameUtil;

@SuppressWarnings("serial")
public class JFrameGame extends JFrame {

	/**
	 * 
	 * default main panel
	 * 
	 * */
	private JPanelGame panelGame = null;
	
	public JFrameGame() {
		// set name
		this.setTitle("monopoly");
		// Set default shutdown properties (end of program)
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// set window size
		this.setSize(750 + 200, 650);
		// user cannot change window size
		this.setResizable(false);
		// center
		FrameUtil.setFrameCenter(this);
		// set default Panel
		this.panelGame = new JPanelGame();
		add(this.panelGame);
		
		// set rim 
		this.setUndecorated(false);
	}

	public JPanelGame getPanelGame() {
		return panelGame;
	}
}
