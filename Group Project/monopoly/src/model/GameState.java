package model;

/**
 * 
 * game variable
 * 
 * 
 * @author CSC2018 Group 31
 *
 */
public class GameState {

	// Stay in the building and return state
	
	public  final static int HOSPITAL_EVENT = 1;

	public  final static int HUOSE_EVENT = 2;
	
	public  final static int LOTTERY_EVENT = 3;
	
	public  final static int NEWS_EVENT = 4;
	
	public  final static int ORIGIN_EVENT = 5;
	
	public  final static int PARK_EVENT = 6;
	
	public  final static int POINT_EVENT = 7;
	
	public  final static int PRISON_EVENT = 8;
	
	public  final static int SHOP_EVENT = 9;
	
	// Passing by the building and return state
	
	public final static int ORIGIN_PASS_EVENT = 1;
	
	// Use card status
	
	
	public  final static int CARD_CROSSING = 1;
	
	public  final static int CARD_REDUCELEVEL = 2;
	
	public  final static int CARD_ROB = 3;
	
	public  final static int CARD_TALLAGE = 4;
	
	public  final static int CARD_TORTOISE = 5;
	
	public  final static int CARD_TRAP = 6;
	
	public  final static int CARD_STOP = 7;
	
	// Card effect status
	
	public final static int CARD_BUFF_STOP = 1;
	
	public final static int CARD_BUFF_TORTOISE = 2;
}
