package view;

import java.awt.Graphics;
import java.awt.Image;
import java.util.List;

import javax.swing.ImageIcon;

import controller.GameRunning;
import model.LandModel;
import model.PlayerModel;

/**
 * 
 * player position update layer
 * 
 * @author CSC2018 Group 31
 * 
 */
public class Players extends Layer {

	private GameRunning run = null;
	private List<PlayerModel> players = null;

	protected Players(int x, int y, int w, int h) {
		super(x, y, w, h);
	}

	protected Players(int x, int y, int w, int h,GameRunning run, List<PlayerModel> players) {
		super(x, y, w, h);
		this.run = run;
		this.players = players;
	}

	public void paint(Graphics g) {
		// Draw the player's situation on the map
		for (PlayerModel temp : players) {
			paintPlayer(temp, g);
		}
	}

	/**
	 * 
	 * draw single player
	 * 
	 */
	private void paintPlayer(PlayerModel player, Graphics g) {
		// Determine whether it is the current player
		boolean show = true;
		Image temp = player.getIMG("mini");
		if (player.equals(this.run.getNowPlayer())) {
			temp = player.getIMG("mini_on");
		} else {
			if (this.x == player.getOtherPlayer().getX()
					&& this.y == player.getOtherPlayer().getY()) {
				show = false;
			}
		}
		if (show)
			g.drawImage(temp, player.getX() + 28, player.getY() + 28, player.getX() + 60,
					player.getY() + 60, 0, 0, 32, 32, null);
	}


	@Override
	public void startPanel() {
	}
}
