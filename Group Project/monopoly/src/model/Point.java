package model;

import controller.Control;

/**
 * 
 * 10 30 50 Point position When the character reaches the point roll position, you can get the amount of response point roll
 * 
 * 
 * @author CSC2018 Group 31
 * 
 */
public class Point extends Building {

	private int point;


	public Point(int posX, int posY, int point) {
		super(posX, posY);
		this.name = point + "point position";
		this.point = point;
	}

	public int getPoint() {
		return point;
	}

	@Override
	public int getEvent() {
		return GameState.POINT_EVENT;
	}
}
