package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.util.List;

import model.PlayerModel;

/**
 * 
 * player information panel refresh
 * 
 * @author CSC2018 Group 31
 * 
 */
public class PlayersPanel extends Layer {

	private List<PlayerModel> players = null;

	protected PlayersPanel(int x, int y, int w, int h, List<PlayerModel> players) {
		super(x, y, w, h);
		this.players = players;
	}

	/**
	 * 
	 * Player information display panel drawing
	 * 
	 */
	public void paintPlayerInformation(Graphics g) {
		int tempX = 0;
		tempX += 30;
		for (PlayerModel temp : players) {
			// Player information display panel drawing
			paintPlayerPanel(temp, g, tempX, 15);
			tempX += 80;
		}
	}

	/**
	 * 
	 * Player information display panel drawing
	 * 
	 */
	private void paintPlayerPanel(PlayerModel player, Graphics g, int x,
			int y) {
		// player information string
		String[] information = { player.getName(),
				Integer.toString(player.getCash()) + " golds",
				Integer.toString(player.getNx()) + " points",
				Integer.toString(player.getBuildings().size()) + " buildings",
				Integer.toString(player.getCards().size()) + "cards" };
		// avator
		g.drawImage(player.getIMG("mini_02"), x -26 + 15 , y - 10, x -26 + 15 +player.getIMG("mini_02").getWidth(null) ,
				 y - 10 +player
					.getIMG("mini_02").getHeight(null) , 0, 0, player.getIMG("mini_02").getWidth(null), player
						.getIMG("mini_02").getHeight(null), null);
		y += 48;
		g.setColor(Color.DARK_GRAY);
		g.setFont(new Font(null,0,14));
		// information repaint
		FontMetrics fm = g.getFontMetrics();
		for (int k = 0; k < information.length; g.drawString(information[k], x
				+ (45 - fm.stringWidth(information[k])), y += 30), k++)
			;

	}

	@Override
	public void paint(Graphics g) {
		this.createWindow(g);
		// Player information display panel repaint
		this.paintPlayerInformation(g);
		
	}

	@Override
	public void startPanel() {
	}

}
