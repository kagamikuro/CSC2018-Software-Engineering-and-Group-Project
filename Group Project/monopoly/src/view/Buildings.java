package view;

import java.awt.Graphics;
import java.awt.Image;
import java.util.List;

import javax.swing.ImageIcon;

import model.Building;
import model.PlayerModel;


/**
 * building refresh layer
 * 
 * @author CSC2018 Group 31
 * 
 */
public class Buildings extends Layer {

	/**
	 * 
	 * load building information
	 * 
	 */
	private model.BuildingsModel informationBuilding;
	/**
	 * 
	 * building list
	 * 
	 */
	private List<Building> building;
	/**
	 * house image
	 * 
	 */
	private  Image HOUSE_01 = new ImageIcon("images/building/house01.png").getImage();
	/**
	 * house image
	 * 
	 */
	private  Image HOUSE_02 = new ImageIcon("images/building/house02.png").getImage();
	/**
	 * transparent image
	 * 
	 */
	public  Image TRANSPARENT = new ImageIcon("images/window/transparent.png").getImage();
	
	protected Buildings(int x, int y, int w, int h,
			model.BuildingsModel informationBuilding) {
		super(x, y, w, h);
		this.informationBuilding = informationBuilding;
	}

	public void paint(Graphics g) {
		// draw buildings
		paintBuildings(g);
	}
	
	/**
	 * 
	 * draw buildings
	 * 
	 */
	private void paintBuildings(Graphics g) {
		for(Building temp : this.building){
			// draw buildings
			paintBuilding(temp,g);
		}
	}
	private void paintBuilding(Building building, Graphics g) {
		int x = 0;
		int y = 0;
		if (building.getOwner() != null) {
			int level = building.getLevel();
			int i = building.getPosX();
			int j = building.getPosY();
			Image temp = building.getOwner().getNumber() == 1 ? this.HOUSE_01
					: this.HOUSE_02;
			if (level > 0) {
				g.drawImage(temp, x + j * 60,
						y + i * 60 - (temp.getHeight(null) - 60), x + (j + 1)
								* 60, y + (i + 1) * 60, 60 * (level - 1), 0,
						60 * level, temp.getHeight(null), null);
			}
			// Transparent white strip
			g.drawImage(this.TRANSPARENT, x + j * 60, y + i * 60, x + (j + 1)
					* 60, y + (i + 1) * 60, 0, 0, 60, 60, null);
			// building owner name display
			g.drawString("" + building.getOwner().getName(), x + j * 60 + 4, y + i
					* 60 + 14);
		}
	}


	@Override
	public void startPanel() {
		this.building = this.informationBuilding.getBuilding();
	}

}
