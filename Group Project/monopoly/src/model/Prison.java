package model;

import java.util.List;

import controller.Control;

/**
 * 
 * Prison Players can be in prison or other incidents here
 * 
 * 
 * @author CSC2018 Group 31
 * 
 */
public class Prison extends Building {

	private String[] events = {"Go to jail to visit friends,", "I was wronged and imprisoned,", "I was caught by the prison guard to clean up," };

	private PlayerModel player;

	public Prison(int posX, int posY) {
		super(posX, posY);
		this.name = "prison";
	}

	public String[] getEvents() {
		return events;
	}

	@Override
	public int getEvent() {
		return GameState.PRISON_EVENT;
	}
}
