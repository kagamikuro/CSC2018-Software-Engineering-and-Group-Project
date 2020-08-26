package model;


import controller.Control;

/**
 * 
 * Hospital When the player arrives at this location, the patient can be discharged; after the character is hospitalized, the game will continue from here. Hospitalization time: 1-4 days
 * 
 * 
 * @author CSC2018 Group 31
 * 
 */
public class Hospital extends Building {
	
	private String[] events = {
			"Checked out flu in the hospital.",
			"When you run into a neurological patient, come forward and study the condition together.",
			"I was robbed at the entrance of the hospital and was beaten by a group. I need to be hospitalized for observation.",
			"Visiting friends in the hospital."
	};
	
	public Hospital(int posX, int posY) {
		super(posX, posY);
		this.name = "hospital";
	}
	public String[] getEvents() {
		return events;
	}
	@Override
	public int getEvent() {
		return GameState.HOSPITAL_EVENT;
	}
}
