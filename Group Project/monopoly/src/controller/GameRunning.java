package controller;

import java.util.List;

import model.Port;
import model.PlayerModel;
import model.Tick;
import view.JPanelGame;

/**
 * 
 * game running process
 * 
 * @author CSC2018 Group 31
 * 
 */
public class GameRunning {

	/**
	 * player list
	 */
	private List<PlayerModel> players = null;

	/**
	 * current player
	 */
	private PlayerModel nowPlayer = null;

	/**
	 * dice point
	 */
	private int point;

	/**
	 * player card state
	 */
	public static int STATE_CARD = 1;
	/**
	 * player card effect state
	 */
	public static int STATE_CARD_EFFECT = 2;
	/**
	 * player throw state
	 */
	public static int STATE_THROWDICE = 3;
	/**
	 * player move state
	 */
	public static int STATE_MOVE = 4;
	/**
	 * 
	 * game stop state
	 * 
	 */
	public static int GAME_STOP = 5;
	/**
	 * 
	 * player current state
	 * 	 
	 *  
	 */
	private int nowPlayerState;

	/**
	 * 
	 * game days
	 * 
	 */
	public static int day = 1;

	/**
	 * 
	 * current map code
	 * 
	 */
	public static int MAP = 1;
	/**
	 * 
	 * game day limits (-1 means no limit)
	 * 
	 */
	public static int GAME_DAY = -1;
	/**
	 * 
	 * game money limits(winning condition) (-1 means no limit)
	 * 
	 */
	public static int MONEY_MAX = -1;

	/**
	 * 
	 * player starting cash
	 * 
	 */
	public static int PLAYER_CASH = 1000;

	private Control control;

	public GameRunning(Control control, List<PlayerModel> players) {
		this.control = control;
		this.players = players;
	}

	/**
	 * 
	 * get current player state
	 * 
	 */
	public int getNowPlayerState() {
		return this.nowPlayerState;
	}

	/**
	 * 
	 * change player state
	 * 
	 */
	public void nextState() {
		// judge game state
		if (gameContinue()) {
			if (this.nowPlayerState == STATE_CARD) {
				// ¡°dice state¡±
				this.nowPlayerState = STATE_CARD_EFFECT;
				// card BUFF
				this.control.cardsBuff();
			} else if (this.nowPlayerState == STATE_CARD_EFFECT) {
				// "card effect state"
				this.nowPlayerState = STATE_THROWDICE;
			} else if (this.nowPlayerState == STATE_THROWDICE) {
				// move state
				this.nowPlayerState = STATE_MOVE;
			} else if (this.nowPlayerState == STATE_MOVE) {
				// change player state
				this.nowPlayerState = STATE_CARD;
				this.nextPlayer();
				// get a point
				this.setPoint((int) (Math.random() * 6));
				// execute next player when finish - STATE_CARD
				this.control.useCards();
			}
		}
	}

	/**
	 * 
	 * get current player
	 * 
	 */
	public PlayerModel getNowPlayer() {
		return this.nowPlayer;
	}

	public void setNowPlayerState(int nowPlayerState) {
		this.nowPlayerState = nowPlayerState;
	}

	/**
	 * 
	 * get non-current player
	 * 
	 */
	public PlayerModel getNotNowPlayer() {
		return this.nowPlayer.equals(this.players.get(0)) ? this.players.get(1)
				: this.players.get(0);
	}

	/**
	 * change player operation
	 */
	private void nextPlayer() {
		// reduce time
		if (this.nowPlayer.getInPrison() > 0) {
			this.nowPlayer.setInPrison(this.nowPlayer.getInPrison() - 1);
		}
		if (this.nowPlayer.getInHospital() > 0) {
			this.nowPlayer.setInHospital(this.nowPlayer.getInHospital() - 1);
		}
		// »»ÈË
		if (this.nowPlayer.equals(this.players.get(0))) {
			this.nowPlayer = this.players.get(1);
		} else {
			this.nowPlayer = this.players.get(0);
			// days increase 1 when finish
			day++;
		}
	}

	/**
	 * 
	 * judge if game over
	 * 
	 * 
	 */
	public boolean gameContinue() {
		PlayerModel p1 = this.nowPlayer;
		PlayerModel p2 = this.nowPlayer.getOtherPlayer();
		// max day
		if (GAME_DAY > 0 && day >= GAME_DAY) {
			this.control.gameOver();
			return false;
		}
		// max money
		if (MONEY_MAX > 0 && p1.getCash() >= MONEY_MAX) {
			this.control.gameOver();
			return false;
		} else if (MONEY_MAX > 0 && p2.getCash() >= MONEY_MAX) {
			this.control.gameOver();
			return false;
		}
		// break down
		if (p1.getCash() < 0) {
			this.control.gameOver();
			return false;
		} else if (p2.getCash() < 0) {
			this.control.gameOver();
			return false;
		}
		return true;
	}

	public void setPlayers(List<PlayerModel> players) {
		this.players = players;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	public int getDay() {
		return day;
	}

	/**
	 * 
	 * starting game setup
	 * 
	 */
	public void startGameInit() {
		// set current player
		this.nowPlayer = this.players.get(0);
		// set current player state is card state
		this.nowPlayerState = STATE_CARD;
		// set points randomly
		this.setPoint((int) (Math.random() * 6));
		// first player use card
		this.control.useCards();
	}

}
