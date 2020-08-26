package model;

import java.util.ArrayList;
import java.util.List;

/**
 * global building information
 * 
 * @author CSC2018 Group 31
 * 
 */
public class BuildingsModel extends Tick implements Port{
	/**
	 * building list
	 */
	private List<Building> buildings = null;
	
	private LandModel land = null;

	
	public BuildingsModel (LandModel land){
		this.land = land;
	}


	/**
	 * 
	 * initialize building
	 * 
	 */
	private void initBuilding() {
		// initialize list
		buildings = new ArrayList<Building>();
		// add building to map
		int[][] temp = this.land.getLand();
		for (int i = 0; i < temp.length; i++) {
			for (int j = 0; j < temp[i].length; j++) {
				switch (temp[i][j]) {
				case LandModel.SPACE:
					Building tempBuidling = new House(i, j);
					// set building attribute is purchasable
					tempBuidling.setPurchasability(true);
					buildings.add(tempBuidling);
					break;
				case LandModel.HOSPITAL:// hospital
					buildings.add(new Hospital(i, j));
					//set hospital point
					LandModel.hospital = new java.awt.Point(j * 60,i * 60);
//					System.out.println(LandModel.hospital );
					break;
				case LandModel.LOTTERY:
					buildings.add(new Lottery(i, j));
					break;
				case LandModel.NEWS:
					buildings.add(new News(i, j));
					break;
				case LandModel.ORIGIN:
					buildings.add(new Origin(i, j));
					break;
				case LandModel.PARK:
					buildings.add(new Park(i, j));
					break;
				case LandModel.PIONT_10:
					buildings.add(new Point(i, j, 10));
					break;
				case LandModel.PIONT_30:
					buildings.add(new Point(i, j, 30));
					break;
				case LandModel.PIONT_50:
					buildings.add(new Point(i, j, 50));
					break;
				case LandModel.SHOP:
					buildings.add(new Shop_(i, j));
					break;
				case LandModel.PRISON:// prison
					buildings.add(new Prison(i, j));
					//set prison point
					LandModel.prison = new java.awt.Point(j * 60, i * 60);
//					System.out.println(LandModel.prison );
					break;
				default:
					break;
				}
			}
		}
	}

	/**
	 * 
	 * get building list
	 * 
	 * @return
	 */
	public List<Building> getBuilding(){
		return buildings;
	}
	/**
	 * 
	 * get current position building
	 * 
	 */
	public Building getBuilding(int x,int y){
		for (Building temp : this.buildings){
			if (temp.getPosX() == x && temp.getPosY() == y){
				return temp;
			}
		}
		return null;
	}
	/**
	 * 
	 * game start set
	 * 
	 */
	public void startGameInit (){
		// initialize building
		initBuilding();
	}

	@Override
	public void updata(long tick) {
		this.nowTick = tick;
		
	}
}