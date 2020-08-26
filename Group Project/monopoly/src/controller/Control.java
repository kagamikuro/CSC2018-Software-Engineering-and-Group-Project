package controller;

import java.applet.AudioClip;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JApplet;
import javax.swing.JOptionPane;

import model.BackgroundModel;
import model.Building;
import model.BuildingsModel;
import model.Card;
import model.DiceModel;
import model.EffectModel;
import model.EventsModel;
import model.GameState;
import model.Hospital;
import model.LandModel;
import model.Music;
import model.News;
import model.Origin;
import model.Park;
import model.PlayerModel;
import model.Point;
import model.Port;
import model.Prison;
import model.Shop_;
import model.TextTipModel;
import model.TortoiseCard;
import util.FileUtil;
import util.MyThread;
import view.JPanelGame;

/**
 * 
 * game control
 * 
 * 
 * @author CSC2018 Group 31
 * 
 */
public class Control {
	/**
	 * 
	 * game tick value
	 * 
	 */
	public static long tick;
	/**
	 * 
	 * Screen refresh rate per second
	 * 
	 */
	public static int rate = 30;
	/**
	 * 
	 * game panel
	 * 
	 */
	private JPanelGame panel;
	/**
	 * 
	 * game object
	 * 
	 */
	private GameRunning run = null;

	private List<Port> models = new ArrayList<Port>();
	private List<PlayerModel> players = null;
	private BuildingsModel building = null;
	private BackgroundModel background = null;
	private LandModel land = null;
	private TextTipModel textTip = null;
	private DiceModel dice = null;
	private EventsModel events = null;
	private EffectModel effect = null;

	private Music music = null;
	
	/**
	 * 
	 * game timer
	 * 
	 */
	private Timer gameTimer = null;

	public Control() {
		// Create a game state
		this.run = new GameRunning(this, players);
		// Initialize the game object
		this.initClass();
		// Add player model to game state
		this.run.setPlayers(players);
	}

	public void setPanel(JPanelGame panel) {
		this.panel = panel;
	}

	/**
	 * 
	 * Initialize the game object
	 * 
	 */
	private void initClass() {
		// Create a new event model
		this.events = new EventsModel();
		this.models.add(events);
		// Create a new scene effect model
		this.effect = new EffectModel();
		this.models.add(effect);
		// Create a new background model
		this.background = new BackgroundModel();
		this.models.add(background);
		// Create a new land model
		this.land = new LandModel();
		this.models.add(land);
		// Create a new land model
		this.textTip = new TextTipModel();
		this.models.add(textTip);
		// Create a new building model
		this.building = new BuildingsModel(land);
		this.models.add(building);
		// Create a new player array
		this.players = new ArrayList<PlayerModel>();
		this.players.add(new PlayerModel(1, this));
		this.players.add(new PlayerModel(2, this));
		this.models.add(players.get(0));
		this.models.add(players.get(1));
		// Create a new dice model
		this.dice = new DiceModel(run);
		this.models.add(dice);
		
		// Create a player
		this.music = new Music();
	}

	/**
	 * 
	 * game timer
	 * 
	 */
	private void createGameTimer() {
		this.gameTimer = new Timer();
		this.gameTimer.schedule(new TimerTask() {
			@Override
			public void run() {
				tick++;
				// update each object
				for (Port temp : models) {
					temp.updata(tick);
				}
				// UI update
				panel.repaint();
			}
		}, 0, (1000 / rate));
	}

	/**
	 * 
	 * controller start
	 * 
	 */
	public void start() {
		// create a timer
		this.createGameTimer();
		// Refresh the initial data of the object
		for (Port temp : this.models) {
			temp.startGameInit();
		}
		// game environment start
		this.run.startGameInit();
		// panel initialize
		this.panel.startGamePanelInit();
		// game background music
		this.startMusic();
		// start effect
		this.effect.showImg("start");
	}

	
	/**
	 * 
	 * game background music
	 * 
	 */
	private void startMusic() {
		music.start();
	}

	public List<PlayerModel> getPlayers() {
		return players;
	}

	public BuildingsModel getBuilding() {
		return building;
	}

	public BackgroundModel getBackground() {
		return background;
	}

	public LandModel getLand() {
		return land;
	}

	public EffectModel getEffect() {
		return effect;
	}

	public TextTipModel getTextTip() {
		return textTip;
	}

	public GameRunning getRunning() {
		return run;
	}

	public DiceModel getDice() {
		return dice;
	}

	public EventsModel getEvents() {
		return events;
	}

	public JPanelGame getPanel() {
		return panel;
	}

	/**
	 * 
	 * 
	 * press dice
	 * 
	 * 
	 */
	public void pressButton() {
		PlayerModel player = this.run.getNowPlayer();
		if (player.getInHospital() > 0 || player.getInPrison() > 0) {
			this.run.nextState();
			if (player.getInHospital() > 0) {
				this.textTip.showTextTip(player, player.getName() + "in hospital", 3);
			} else if (player.getInPrison() > 0) {
				this.textTip.showTextTip(player, player.getName() + "in prison", 3);
			}
			this.run.nextState();
		} else {
			// Set the start time of the dice object
			this.dice.setStartTick(Control.tick);
			// Set the end time of the dice object
			this.dice.setNextTick(this.dice.getStartTick()
					+ this.dice.getLastTime());
			// Pass the running object points to the dice object
			this.dice.setPoint(this.run.getPoint());
			// Transition state to mobile state
			this.run.nextState();
			// The player moves after the dice is turned
			this.run.getNowPlayer().setStartTick(this.dice.getNextTick() + 10);
			this.run.getNowPlayer().setNextTick(
					this.run.getNowPlayer().getStartTick()
							+ this.run.getNowPlayer().getLastTime()
							* (this.run.getPoint() + 1));
		}
	}

	/**
	 * 
	 * 
	 * player move
	 * 
	 * 
	 */
	public void movePlayer() {
		// person move
		for (int i = 0; i < (60 / this.run.getNowPlayer().getLastTime()); i++) {

			if (GameRunning.MAP == 1){
				this.move01();
			} else if (GameRunning.MAP == 2){
				this.move02();
			} else if (GameRunning.MAP == 3) {
				this.move03();
			}
		}
	}

	/**
	 * 
	 * player pass building
	 * 
	 */
	public void prassBuilding() {
		// current player
		PlayerModel player = this.run.getNowPlayer();
		// current building
		Building building = this.building.getBuilding(player.getY() / 60,
				player.getX() / 60);
		if (building != null && player.getX() % 60 == 0
				&& player.getY() % 60 == 0) {
			// Passing the house event
			int event = building.passEvent();
			// enter the house event
			disposePassEvent(building, event, player);
		}
	}

	/**
	 * 
	 * Passing the house event
	 * 
	 */
	private void disposePassEvent(Building b, int event, PlayerModel player) {
		switch (event) {
		case GameState.ORIGIN_PASS_EVENT:
			// when pass origin
			passOrigin(b, player);
			break;
		default:
			break;
		}
	}

	/**
	 * 
	 * when pass origin
	 * 
	 */
	private void passOrigin(Building b, PlayerModel player) {
		this.textTip.showTextTip(player, player.getName() + " pass origin,"
				+ ((Origin) b).getPassReward() + "gold got.", 3);
		player.setCash(player.getCash() + ((Origin) b).getPassReward());
	}

	/**
	 * 
	 * 
	 * player move
	 * 
	 * 
	 */
	private void move02() {
		int dice = this.run.getPoint() + 1;
		PlayerModel p = this.run.getNowPlayer();
		// unit move pixel
		int movePixel = 1;
		if (p.getX() < 12 * 60 && p.getY() == 0) {
			p.setX(p.getX() + movePixel);
		} else if (p.getX() == 12 *60 && p.getY() < 2 * 60){
			p.setY(p.getY() + movePixel);
		} else if (p.getX() == 12 * 60 && p.getY() == 2 * 60){
			if ((int)(Math.random() * 2 ) == 0){
				p.setX(p.getX() - movePixel);
			} else {
				p.setY(p.getY() + movePixel);
			}
		} else if (p.getX() == 12 * 60 && p.getY() > 2 * 60 && p.getY() < 4 * 60){
			p.setY(p.getY() + movePixel);
		} else if (p.getX() > 8 * 60 && p.getX() <= 12 * 60 && p.getY() == 4 * 60){
			p.setX(p.getX() - movePixel);
		} else if (p.getX() == 8 * 60 && p.getY() == 4 * 60){
			if ((int)(Math.random() * 2 ) == 0){
				p.setX(p.getX() - movePixel);
			} else {
				p.setY(p.getY() + movePixel);
			}
		} else if (p.getX() > 4 * 60 && p.getX() < 8 * 60 && p.getY() == 4 * 60) {
			p.setX(p.getX() - movePixel);
		} else if (p.getX() == 8 * 60 && p.getY() > 4 * 60 && p.getY() < 7 * 60){
			p.setY(p.getY() + movePixel);
		} else if (p.getX() >  4 * 60 && p.getX() <= 8 * 60 && p.getY() == 7 * 60){
			p.setX(p.getX() - movePixel);
		} else if (p.getX() > 4 * 60 && p.getX() < 12 * 60 && p.getY() == 2 * 60){
			p.setX(p.getX() - movePixel);
		} else if (p.getX() == 4 * 60 && p.getY() >= 2 * 60 && p.getY() < 7 * 60){
			p.setY(p.getY() + movePixel);
		} else if (p.getX() > 0 && p.getX() <= 4 * 60 && p.getY() == 7 * 60){
			p.setX(p.getX() - movePixel);
		} else if (p.getX() == 0 && p.getY() > 0){
			p.setY(p.getY() - movePixel);
		}
	}
	
	/**
	 * 
	 * 
	 * player move
	 * 
	 * 
	 */
	private void move01() {
		int dice = this.run.getPoint() + 1;
		PlayerModel p = this.run.getNowPlayer();
		// unit move pixel
		int movePixel = 1;
		Boolean turn = dice % 2 != 0;
		if (p.getX() < 9 * 60 && p.getY() == 0) {
			// up
			if (p.getX() == 4 * 60 && turn) {
				// Bifurcation situation
				p.setY(p.getY() + movePixel);
			} else {
				p.setX(p.getX() + movePixel);
			}
		} else if (p.getX() == 9 * 60 && p.getY() >= 0 && p.getY() < 60) {
			// [0,9]
			// ↓
			p.setY(p.getY() + movePixel);
		} else if (p.getX() >= 8 * 60 && p.getX() < 12 * 60
				&& p.getY() >= 1 * 60 && p.getY() <= 60 * 1.5) {
			// →
			p.setX(p.getX() + movePixel);
		} else if (p.getX() == 12 * 60 && p.getY() >= 1 * 60
				&& p.getY() < 7 * 60) {
			// ↓
			p.setY(p.getY() + movePixel);
		} else if (p.getX() > 0 && p.getY() == 7 * 60) {
			// ←
			p.setX(p.getX() - movePixel);
		} else if (p.getX() == 0 && p.getY() > 0) {
			// ↑
			p.setY(p.getY() - movePixel);
		} else if (p.getX() == 4 * 60 && p.getY() > 0 && p.getY() < 7 * 60) {
			// ↓
			p.setY(p.getY() + movePixel);
		}
	}
	/**
	 * 
	 * 
	 * player move
	 * 
	 * 
	 */
	private void move03() {
		PlayerModel p = this.run.getNowPlayer();
		// unit move pixel
		int movePixel = 1;
		if (p.getX() < 12 * 60 && p.getY() == 0) {
			p.setX(p.getX() + movePixel);
		} else if (p.getX() == 12 *60 && p.getY() < 7 * 60){
			p.setY(p.getY() + movePixel);
		} else if (p.getX() > 0 && p.getY() == 7 * 60){
			p.setX(p.getX() - movePixel);
		} else if (p.getX() == 0 && p.getY() > 0){
			p.setY(p.getY() - movePixel);
		}
	}
	/**
	 * 
	 * After the player moves, stop to judge
	 * 
	 */
	public void playerStopJudge() {
		// current player
		PlayerModel player = this.run.getNowPlayer();
		if (player.getInHospital() > 0) {
			this.textTip.showTextTip(player, player.getName() + "in hospital, cannot move.",
					2);
			// change player state
			this.run.nextState();
		} else if (player.getInPrison() > 0) {
			this.textTip.showTextTip(player, player.getName() + "in prison, cannot move.",
					2);
			// change player state
			this.run.nextState();
		} else {
			// player operate（buy building or accident）
			this.playerStop();
		}
	}

	/**
	 * 
	 * After the player moves, stop to operate
	 * 
	 */
	public void playerStop() {
		// current player
		PlayerModel player = this.run.getNowPlayer();
		// this building
		Building building = this.building.getBuilding(player.getY() / 60,
				player.getX() / 60);
		if (building != null) {// get building
			int event = building.getEvent();
			// building event
			disposeStopEvent(building, event, player);

		}
	}

	/**
	 * 
	 * building stop event
	 * 
	 * 
	 */
	private void disposeStopEvent(Building b, int event, PlayerModel player) {
		switch (event) {
		case GameState.HOSPITAL_EVENT:
			stopInHospital(b, player);
			break;
		case GameState.HUOSE_EVENT:
			stopInHouse(b, player);
			break;
		case GameState.LOTTERY_EVENT:
			stopInLottery(b, player);
			break;
		case GameState.NEWS_EVENT:
			stopInNews(b, player);
			break;
		case GameState.ORIGIN_EVENT:
			stopInOrigin(b, player);
			break;
		case GameState.PARK_EVENT:
			stopInPack(b, player);
			break;
		case GameState.POINT_EVENT:
			stopInPoint(b, player);
			break;
		case GameState.PRISON_EVENT:
			stopInPrison(b, player);
			break;
		case GameState.SHOP_EVENT:
			stopInShop(b, player);
			break;
		}

	}

	/**
	 * 
	 * stop in shop
	 * 
	 */
	private void stopInShop(Building b, PlayerModel player) {
		if (player.getNx() > 0){
		// Regenerate products for store shelves
		((Shop_) b).createCards();
		// Update new card items for the store panel
		this.panel.getShop().addCards((Shop_) b);
		// Push the store panel to the top
		this.panel.getShop().moveToFront();
		} else {
			this.run.nextState();
		}
	}

	/**
	 * 
	 * stop in prison
	 * 
	 */
	private void stopInPrison(Building b, PlayerModel player) {
		int days = (int) (Math.random() * 3) + 2;
		player.setInPrison(days);
		int random = (int) (Math.random() * ((Prison) b).getEvents().length);
		String text = ((Prison) b).getEvents()[random];
		this.textTip.showTextTip(player, player.getName() + text + "stay"
				+ (days - 1) + "days.", 3);
		new Thread(new MyThread(run, 1)).start();
	}

	/**
	 * 
	 * stop in point position
	 * 
	 */
	private void stopInPoint(Building b, PlayerModel player) {
		player.setNx(((Point) b).getPoint() + player.getNx());
		this.textTip.showTextTip(player, player.getName() + " got "
				+ ((Point) b).getPoint() + "point.", 3);
		new Thread(new MyThread(run, 1)).start();
	}

	/**
	 * 
	 * stop in park
	 * 
	 */
	private void stopInPack(Building b, PlayerModel player) {
		int random = (int) (Math.random() * ((Park) b).getImgageEvents().length);

		switch (random) {
		case 0:
		case 1:
			// lost 1 gold
			player.setCash(player.getCash() - 1);
			break;
		case 2:
			// lost 200 gold
			player.setCash(player.getCash() - 200);
			break;
		case 3:
			// get 200 gold
			player.setCash(player.getCash() + 200);
			break;
		}
		// Show events in the event layer
		this.events.showImg(((Park) b).getImgageEvents()[random], 3, new Point(
				320, 160, 0));
		new Thread(new MyThread(run, 3)).start();
	}

	/**
	 * 
	 * stop in origin
	 * 
	 */
	private void stopInOrigin(Building b, PlayerModel player) {
		this.textTip.showTextTip(player, player.getName() + " stay at origin, "
				+ ((Origin) b).getReward() + "gold got.", 3);
		player.setCash(player.getCash() + ((Origin) b).getReward());
		new Thread(new MyThread(run, 1)).start();
	}

	/**
	 * 
	 * stop in news point
	 * 
	 */
	private void stopInNews(Building b, PlayerModel player) {
		int random = (int) (Math.random() * ((News) b).getImgageEvents().length);
		switch (random) {
		case 0:
		case 1:
			// set days
			player.setInHospital(player.getInHospital() + 4);
			// The player position switches to the hospital position
			if (LandModel.hospital != null) {
				player.setX(LandModel.hospital.x);
				player.setY(LandModel.hospital.y);
			}
			break;
		case 2:
		case 3:
			player.setCash(player.getCash() - 1000);
			break;
		case 4:
			player.setCash(player.getCash() - 1500);
			break;
		case 5:
			player.setCash(player.getCash() - 2000);
			break;
		case 6:
		case 7:
			player.setCash(player.getCash() - 300);
			break;
		case 8:
			player.setCash(player.getCash() - 400);
			break;
		case 9:
			// The point volume is less than value then event cannot occur
			if (player.getNx() < 40) {
				stopInNews(b, player);
				return;
			}
			player.setNx(player.getNx() - 40);
			break;
		case 10:
			player.setCash(player.getCash() - 500);
			break;
		case 11:
			player.setCash(player.getCash() + 1000);
			break;
		case 12:
		case 13:
			player.setCash(player.getCash() + 2000);
			break;
		case 14:
			player.setCash(player.getCash() + 3999);
			player.setNx(player.getNx() + 100);
			break;
		case 15:
			player.setNx(player.getNx() + 300);
			break;
		case 16:
			for (int i = 0; i  < player.getCards().size();i++){
//				System.out.println(player.getCards().get(i).getcName());
				// crossing card
				if (player.getCards().get(i).getName().equals("CrossingCard")){
					player.getCards().remove(i);
					// Opponent reduces money
					player.getOtherPlayer().setCash(player.getOtherPlayer().getCash() - 3000);
					this.textTip.showTextTip(player, player.getName() + "let \"3000 golds\"cross to "+ player.getOtherPlayer().getName()+"the fuck", 6);
					this.events.showImg(((News) b).get3000(), 3, new Point(
							420, 160, 0));
					new Thread(new MyThread(run, 3)).start();
					return;
				}
			}
			player.setCash(player.getCash() - 3000);
			break;
		}
		// Show events in the event layer
		this.events.showImg(((News) b).getImgageEvents()[random], 3, new Point(
				420, 160, 0));
		new Thread(new MyThread(run, 3)).start();
	}

	/**
	 * 
	 * stop in lottery point
	 * 
	 */
	private void stopInLottery(Building b, PlayerModel player) {
		// TODO
		new Thread(new MyThread(run, 1)).start();
	}

	/**
	 * 
	 * 
	 * stop in house area
	 * 
	 * 
	 */
	private void stopInHouse(Building b, PlayerModel player) {
		if (b.isPurchasability()) {// player house
			if (b.getOwner() == null) { 
				// execute buying opperation
				this.buyHouse(b, player);
			} else {// someone own
				if (b.getOwner().equals(player)) {
					// execute house level up
					this.upHouseLevel(b, player);
				} else {// others
					// give tax
					this.giveTax(b, player);
				}
			}
		}
	}

	/**
	 * 
	 * give tax opperation
	 * 
	 * 
	 */
	private void giveTax(Building b, PlayerModel player) {
		if (b.getOwner().getInHospital() > 0) {
			// text tip
			this.textTip.showTextTip(player, b.getOwner().getName()
					+ "in hospital no fee.", 3);
		} else if (b.getOwner().getInPrison() > 0) {
			// text tip
			this.textTip.showTextTip(player, b.getOwner().getName()
					+ "in prison no fee", 3);
		} else {
			int revenue = b.getRevenue();
			// this player reduce cash
			player.setCash(player.getCash() - revenue);
			// owner got gold
			b.getOwner().setCash(b.getOwner().getCash() + revenue);
			// text tip
			this.textTip.showTextTip(player, player.getName() + "pass"
					+ b.getOwner().getName() + "\'s house"+"fee:" + revenue + "gold.", 3);

		}
		new Thread(new MyThread(run, 1)).start();
	}

	/**
	 * 
	 * house level up
	 * 
	 */
	private void upHouseLevel(Building b, PlayerModel player) {
		if (b.canUpLevel()) {
			// house level up
			int price = b.getUpLevelPrice();
			String name = b.getName();
			String upName = b.getUpName();
			int choose = JOptionPane.showConfirmDialog(null,
					"dear:" + player.getName() + "\r\n" + "do you levelup this ？\r\n" + name
							+ "→" + upName + "\r\n" + "price:：" + price + " golds.");
			if (choose == JOptionPane.OK_OPTION) {
				if (player.getCash() >= price) {
					b.setLevel(b.getLevel() + 1);
					// reduce cash
					player.setCash(player.getCash() - price);
					// text tip
					this.textTip.showTextTip(player, player.getName() + " from "
							+ name + " to " + upName + ".cost " + price
							+ "gold. ", 3);
				} else {
					// text tip
					this.textTip.showTextTip(player, player.getName()
							+ " no enough money , operation failed. ", 3);
				}
			}
		}
		new Thread(new MyThread(run, 1)).start();
	}

	/**
	 * 
	 * execute buy house
	 * 
	 * 
	 */
	private void buyHouse(Building b, PlayerModel player) {
		int price = b.getUpLevelPrice();
		int choose = JOptionPane.showConfirmDialog(
				null,
				"dear:" + player.getName() + "\r\n" + "do you buy this house？\r\n"
						+ b.getName() + "→" + b.getUpName() + "\r\n" + "price:"
						+ price + " golds.");

		if (choose == JOptionPane.OK_OPTION) {
			// buy
			if (player.getCash() >= price) {
				b.setOwner(player);
				b.setLevel(1);
				// Add the house to the current player’s house list
				player.getBuildings().add(b);
				// reduce cash
				player.setCash(player.getCash() - price);
				this.textTip.showTextTip(player, player.getName()
						+ " buy a new house, cost: " + price + "golds. ", 3);
			} else {
				this.textTip.showTextTip(player, player.getName()
						+ " no money ,failed. ", 3);
			}
		}
		new Thread(new MyThread(run, 1)).start();
	}

	/**
	 * 
	 * stop in hospital
	 * 
	 */
	private void stopInHospital(Building b, PlayerModel player) {
		int days = (int) (Math.random() * 4) + 2;
		player.setInHospital(days);
		int random = (int) (Math.random() * ((Hospital) b).getEvents().length);
		String text = ((Hospital) b).getEvents()[random];
		this.textTip.showTextTip(player, player.getName() + text + "stay"
				+ (days - 1) + "days.", 3);
		new Thread(new MyThread(run, 1)).start();
	}

	/**
	 * 
	 * cards effect
	 * 
	 */
	public void cardsBuff() {
		List<Card>delete = new ArrayList<Card>();
		for (Card a : this.run.getNowPlayer().getEffectCards()) {
			int buff = a.cardBuff();
			cardBuff(a, buff,delete);
		}
		this.run.getNowPlayer().getEffectCards().removeAll(delete);
		this.run.nextState();
	}

	/**
	 * 
	 * card buff last
	 * 
	 * 
	 */
	private void cardBuff(Card card, int buff,List<Card>delete) {
		switch (buff) {
		case GameState.CARD_BUFF_TORTOISE:
			// tortoise card BUff
			buffTortoiseCard((TortoiseCard) card,delete);
			break;
		case GameState.CARD_BUFF_STOP:
			// stop card Buff
			buffStopCard(card,delete);
			break;
		}
	}

	/**
	 * 
	 * stop card Buff
	 * 
	 * 
	 */
	private void buffStopCard(Card card,List<Card>delete) {
		// text tip
		this.textTip.showTextTip(card.geteOwner(), card.geteOwner().getName()
				+ " by \"stop card\" effect，cannot move.. ", 2);
		// remove card
		delete.add(card);
		this.run.nextState();
		new Thread(new MyThread(run, 1)).start();
	}
	

	/**
	 * 
	 * tortoise card BUff
	 * 
	 */

	private void buffTortoiseCard(TortoiseCard card,List<Card>delete) {
		if (card.getLife() <= 0) {
			delete.add(card);
			return;
		} else {
			card.setLife(card.getLife() - 1);
		}
		this.textTip.showTextTip(card.geteOwner(), card.geteOwner().getName()
				+ " by \"tortoise card\" effect, only can move forward 1 step.. ", 2);
		this.run.setPoint(0);
	}

	/**
	 * 
	 * use card
	 * 
	 */
	public void useCards() {
		PlayerModel p = this.run.getNowPlayer();
		while (true) {
			if (p.getCards().size() == 0) {
				// no card, skip
				this.run.nextState();
				break;
			} else {
				Object[] options = new Object[p.getCards().size() + 1];
				int i;
				for (i = 0; i < p.getCards().size(); i++) {
					options[i] = p.getCards().get(i).getcName() + "\r\n";
				}
				options[i] = "skip, no use";
				int response = JOptionPane.showOptionDialog(null,
						" " + p.getName() + ", choose the card ", "using card",
						JOptionPane.YES_OPTION, JOptionPane.PLAIN_MESSAGE,
						null, options, options[0]);
				if (response != i && response != -1) {
					// get card
					int th = p.getCards().get(response).useCard();
					// use card
					useCard(p.getCards().get(response), th);
				} else {
					// no use , skip state
					this.run.nextState();
					break;
				}
			}
		}
	}

	/**
	 * 
	 * use card
	 * 
	 */
	private void useCard(Card card, int th) {
		switch (th) {
		case GameState.CARD_REDUCELEVEL:
			useReduceLevelCard(card);
			break;
		case GameState.CARD_ROB:
			useRobCard(card);
			break;
		case GameState.CARD_STOP:
			useStopCard(card);
			break;
		case GameState.CARD_TALLAGE:
			useTallageCard(card);
			break;
		case GameState.CARD_TORTOISE:
			useTortoiseCard(card);
			break;
		case GameState.CARD_TRAP:
			useTrapCard(card);
			break;
		case GameState.CARD_CROSSING:
			useCrossingCard(card);
			break;
		}
	}

	/**
	 * 
	 * using crossing card
	 * 
	 */
	private void useCrossingCard(Card card) {
		Object[] options1 = { "Reselect" };
		JOptionPane.showOptionDialog(null, " The crossing card will be used automatically when a major event occurs.",
				"using card phase.", JOptionPane.YES_OPTION,
				JOptionPane.PLAIN_MESSAGE, null, options1,
				options1[0]);
	}

	/**
	 * 
	 * using trap card
	 * 
	 */
	private void useTrapCard(Card card) {
		Object[] options = { "confirm", "reselect" };
		int response = JOptionPane.showOptionDialog(null, "confirm\"trap card\"let \""
				+ card.getOwner().getOtherPlayer().getName() + "\"in prison 2 days?",
				"using card phase.", JOptionPane.YES_OPTION, JOptionPane.PLAIN_MESSAGE,
				null, options, options[0]);
		if (response == 0) {
			// using
			PlayerModel cPlayer = card.getOwner().getOtherPlayer();
			// set days
			cPlayer.setInPrison(cPlayer.getInPrison() + 2);
			// set player position to hostipal
			if (LandModel.prison != null) {
				cPlayer.setX(LandModel.prison.x);
				cPlayer.setY(LandModel.prison.y);
			}
			// add text tip
			this.textTip
					.showTextTip(card.getOwner(), card.getOwner().getName()
							+ " used \"trap card\",let \""
							+ card.getOwner().getOtherPlayer().getName()
							+ "\"in prison 2 days.", 2);
			// 　remove card
			card.getOwner().getCards().remove(card);
		}
	}

	/**
	 * 
	 * using tortoise card
	 * 
	 * 
	 */
	private void useTortoiseCard(Card card) {
		Object[] options = { card.getOwner().getName(),
				card.getOwner().getOtherPlayer().getName(), "reselect" };
		int response = JOptionPane.showOptionDialog(null,
				" please choose target player, do him with\"tortoise card\".", "using card phase.", JOptionPane.YES_OPTION,
				JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
		if (response == 0) {
			card.getOwner().getEffectCards().add(card);
			card.seteOwner(card.getOwner());
			// add text tip
			this.textTip.showTextTip(card.getOwner(), card.getOwner().getName()
					+ " using \"tortoise card\" to my self. ", 2);
			card.getOwner().getCards().remove(card);
		} else if (response == 1) {
			card.getOwner().getOtherPlayer().getEffectCards().add(card);
			card.seteOwner(card.getOwner().getOtherPlayer());
			this.textTip.showTextTip(card.getOwner(), card.getOwner().getName()
					+ " do\"" + card.getOwner().getOtherPlayer().getName()
					+ "\"with\"tortoise card\". ", 2);
			card.getOwner().getCards().remove(card);
		}
	}

	/**
	 * 
	 * using tallage card
	 * 
	 * 
	 */
	private void useTallageCard(Card card) {
		Object[] options = { "confirm", "reselect" };
		int response = JOptionPane.showOptionDialog(null, "comfirm using\"tallage card\"from \""
				+ card.getOwner().getOtherPlayer().getName() + "\"get 10% tax?",
				"card using phase.", JOptionPane.YES_OPTION, JOptionPane.PLAIN_MESSAGE,
				null, options, options[0]);
		if (response == 0) {
			// using
			int money = (int) (card.getOwner().getOtherPlayer().getCash() / 10);
			card.getOwner().setCash(card.getOwner().getCash() + money);
			card.getOwner()
					.getOtherPlayer()
					.setCash(card.getOwner().getOtherPlayer().getCash() - money);
			// add text tip
			this.textTip.showTextTip(card.getOwner(), card.getOwner().getName()
					+ " using \"tallage card\",from \""
					+ card.getOwner().getOtherPlayer().getName()
					+ "\"get 10% tax", 2);
			// 　remove card
			card.getOwner().getCards().remove(card);
		}
	}

	/**
	 * 
	 * 
	 * using stop card
	 * 
	 */
	private void useStopCard(Card card) {
		Object[] options = { card.getOwner().getName(),
				card.getOwner().getOtherPlayer().getName(), "reselect" };
		int response = JOptionPane.showOptionDialog(null,
				" please choose target player,do him with \"stop card\".", "card using phase.", JOptionPane.YES_OPTION,
				JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
		if (response == 0) {
			card.getOwner().getEffectCards().add(card);
			card.seteOwner(card.getOwner());
			// add text tip
			this.textTip.showTextTip(card.getOwner(), card.getOwner().getName()
					+ " using \"stop card\" with myself. ", 2);
			card.getOwner().getCards().remove(card);
		} else if (response == 1) {
			card.getOwner().getOtherPlayer().getEffectCards().add(card);
			card.seteOwner(card.getOwner().getOtherPlayer());
			this.textTip.showTextTip(card.getOwner(), card.getOwner().getName()
					+ " to\"" + card.getOwner().getOtherPlayer().getName()
					+ "\"used\"stop card\". ", 2);
			card.getOwner().getCards().remove(card);
		}
	}

	/**
	 * 
	 * 
	 * using rob card
	 * 
	 * 
	 */
	private void useRobCard(Card card) {
		if (card.getOwner().getCards().size() >= PlayerModel.MAX_CAN_HOLD_CARDS) {
			// cannot use
			Object[] options = { "reselect" };
			JOptionPane.showOptionDialog(null, " your cards reached maximum,cannot using\"rob card\"",
					"card using phase.", JOptionPane.YES_OPTION,
					JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
		} else if (card.getOwner().getOtherPlayer().getCards().size() == 0) {
			// cannot use
			Object[] options = { "reselect" };
			JOptionPane.showOptionDialog(null, " \""
					+ card.getOwner().getOtherPlayer().getName()
					+ "\"no card，cannot use\"rob card\"", "card using phase.", JOptionPane.YES_OPTION,
					JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
		} else {
			PlayerModel srcPlayer = card.getOwner().getOtherPlayer();
			// random select
//			System.out.println(srcPlayer.getCards().size() + "zhang");
			Card getCard = srcPlayer.getCards().get((int) (srcPlayer.getCards().size() * Math.random()));
			// player lost card
			srcPlayer.getCards().remove(getCard);
			// card owner get card
			card.getOwner().getCards().add(getCard);
			// change card owner
			getCard.setOwner(card.getOwner());
			// add text tip
			this.textTip.showTextTip(card.getOwner(), card.getOwner().getName()
					+ " used \"rob card\", rob \"" + srcPlayer.getName() + "\"a\""
					+ getCard.getcName() + ".\". ", 2);
			// 　remove card
			card.getOwner().getCards().remove(card);
		}
	}

	/**
	 * 
	 * using reduce level card
	 * 
	 */
	private void useReduceLevelCard(Card card) {
		Building building = this.building.getBuilding(
				card.getOwner().getY() / 60, card.getOwner().getX() / 60);
		if (building.getOwner() != null
				&& building.getOwner().equals(card.getOwner().getOtherPlayer())) {// build belongs to opponent
			if (building.getLevel() > 0) { // could reduce
				// reduce
				building.setLevel(building.getLevel() - 1);
				// add text tip
				this.textTip.showTextTip(card.getOwner(), card.getOwner()
						.getName()
						+ " used \"reduce level card\"，reduce\""
						+ card.getOwner().getOtherPlayer().getName()
						+ "\"'s house 1 level. ", 2);
				// 　remove card
				card.getOwner().getCards().remove(card);
			} else {
				// cannot used, cannot reduce level
				Object[] options = { "reselece" };
				JOptionPane.showOptionDialog(null, " current building cannot be reduced", "card using phase.",
						JOptionPane.YES_OPTION, JOptionPane.PLAIN_MESSAGE,
						null, options, options[0]);
			}
		} else {
			// 无法使用.
			Object[] options = { "reselece" };
			JOptionPane.showOptionDialog(null, " current building cannot be reduced", "card using phase.",
					JOptionPane.YES_OPTION, JOptionPane.PLAIN_MESSAGE, null,
					options, options[0]);
		}
	}


	/**
	 * 
	 * exit shop
	 * 
	 */
	public void exitShop() {
		new Thread(new MyThread(run, 1)).start();
	}

	/**
	 * 
	 * buy card in store
	 * 
	 * 
	 */
	public void buyCard(Shop_ shop) {
		int chooseCard = this.panel.getShop().getChooseCard();
		if (chooseCard >= 0
				&& this.panel.getShop().getCard().get(chooseCard) != null) {
			// buy card if successful
			if (this.buyCard(shop, chooseCard)) {
				// remove card
				this.panel.getShop().getCard().get(chooseCard).setEnabled(false);
				// initailize choosen card
				this.panel.getShop().setChooseCard(-1);
			}
		}
	}

	/**
	 * 
	 * buy card
	 * 
	 * 
	 */
	public boolean buyCard(Shop_ shop, int p) {
		if (this.panel.getShop().getCard().get(p) != null) {
			if (this.run.getNowPlayer().getCards().size() >= PlayerModel.MAX_CAN_HOLD_CARDS) {
				JOptionPane.showMessageDialog(null, "you can hold :"
						+ PlayerModel.MAX_CAN_HOLD_CARDS + "cards, cannot buy card at this moment!");
				return false;
			}
			if (this.run.getNowPlayer().getNx() < shop.getCards().get(p)
					.getPrice()) {
				JOptionPane.showMessageDialog(null, "current card needs :"
						+ shop.getCards().get(p).getPrice() + "points, your points are not enough.");
				return false;
			}
			// set card owners
			shop.getCards().get(p).setOwner(this.run.getNowPlayer());
			// add card to player cards list
			this.run.getNowPlayer().getCards().add(shop.getCards().get(p));
			// reduce points
			this.run.getNowPlayer().setNx(
					this.run.getNowPlayer().getNx()
							- shop.getCards().get(p).getPrice());
		}
		return true;
	}

	/**
	 * 
	 * game over~
	 * 
	 * 
	 * @param winer
	 */
	public void gameOver () {
		this.run.setNowPlayerState(GameRunning.GAME_STOP);
		this.panel.getBackgroundUI().moveToFront();
		this.panel.getRunning().moveToFront();
		this.panel.getPlayerInfo().moveToFront();
		this.panel.getEffect().moveToFront();
		this.music.gameOver();
		this.effect.showImg("timeover2");
		
	}
}
