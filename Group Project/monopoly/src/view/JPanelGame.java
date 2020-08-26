package view;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import controller.Control;

@SuppressWarnings("serial")
public class JPanelGame extends JPanel{

	private JFrameGame gameFrame = null;
	private JLayeredPane layeredPane;

	private List<Layer> lays = null;
	private Background backgroundUI = null;
	private Lands landsUI = null;
	private Buildings buildingsUI = null;
	private Players playersUI = null;
	private TextTip textTip = null;
	private PlayersPanel layerPlayersPanel = null;
	private Dice dice = null;
	private Event event = null;
	private Shop shop = null;
	private Running running = null;
	private Effect effect = null;

	private PlayerInfo playerInfo = null;
	
	private MassageYesNo massageYesNo = null;
	private MassageOk massageOk = null;
	private MassageSimple massageSimple = null;

	private Control control = null;


	public int posX = 100;

	public int posY = 100;

	public JPanelGame() {
		setLayout(new BorderLayout());
		// initialize game
		initGame();
	}

	/**
	 * 
	 * initialize game
	 * 
	 */
	private void initGame() {
		// add control
		control = new Control();
		// initialize UI
		initUI();
		// pass panel 
		control.setPanel(this);
	}

	public Control getControl() {
		return control;
	}

	/**
	 * 
	 * initialize UI
	 * 
	 */
	private void initUI() {
		// create background UI
		this.backgroundUI = new Background(0, 0, 950, 650,
				control.getBackground(),this);
		// create land UI
		this.landsUI = new Lands(posX, posY, 950, 650, control.getLand());
		// create building UI
		this.buildingsUI = new Buildings(posX, posY, 950, 650,
				control.getBuilding());
		// create player display UI
		this.playersUI = new Players(posX, posY, 950, 650,control.getRunning(), control.getPlayers());
		// player information UI
		this.layerPlayersPanel = new PlayersPanel(posX + 64, posY + 66, 170,
				250, control.getPlayers());
		// text tip UI
		this.textTip = new TextTip(0,0,950,650,control.getTextTip());
		// dice event UI
		this.dice = new Dice(posX + 64, posY + 320, 170, 90, control);
		// event display UI
		this.event = new Event(0, 0, 950, 650, control.getEvents());
		// shop UI
		this.shop = new Shop(0, 0, 750, 650, control, this);
		// game running UI
		this.running = new Running(780, 0, 200, 80, control.getRunning(),this);
		// background effect UI
		this.effect = new Effect(0, 0, 950, 650, control.getEffect(),this);
		// game information panel UI
		this.playerInfo = new PlayerInfo(control.getPlayers(),this);
		// conversation UI
		this.massageYesNo = new MassageYesNo("choose", "create a conversation", this);
		// conversation UI
		this.massageOk = new MassageOk("confirm", "create a conversation", this);
		// conversation UI
		this.massageSimple = new MassageSimple("mult- choose", "create a conversation", this);

		// lays store all the panel component
		lays = new ArrayList<Layer>();
		lays.add(backgroundUI);
		lays.add(dice);
		lays.add(playersUI);
//		lays.add(textTip);
		lays.add(layerPlayersPanel);
		lays.add(buildingsUI);
		lays.add(landsUI);
		lays.add(backgroundUI);
		lays.add(running);
		lays.add(effect);
		// lays.add(shop);
		// lays.add(massageYesNo);

		layeredPane = new JLayeredPane();
		layeredPane.setLayout(null);

		int add = 1;
		//layeredPane.add(this.massageOk, add++);
		layeredPane.add(this.event, add++);
		layeredPane.add(this.effect, add++);
		layeredPane.add(this.textTip, add++);
		layeredPane.add(this.dice, add++);
		layeredPane.add(this.playersUI, add++);
		layeredPane.add(this.layerPlayersPanel, add++);
		layeredPane.add(this.buildingsUI, add++);
		layeredPane.add(this.landsUI, add++);
		layeredPane.add(this.running, add++);
		layeredPane.add(this.backgroundUI, add++);
		layeredPane.add(this.shop, add++);
		layeredPane.add(this.playerInfo,add++);

		
		//layeredPane.add(this.massageYesNo, add++);
		//layeredPane.add(this.massageSimple, add++);
		
		add(layeredPane);
	}

	
	public MassageYesNo getMassageYesNo() {
		return massageYesNo;
	}

	public MassageOk getMassageOk() {
		return massageOk;
	}

	public MassageSimple getMassageSimple() {
		return massageSimple;
	}

	public Running getRunning() {
		return running;
	}

	public Dice getDice() {
		return dice;
	}

	public Shop getShop() {
		return this.shop;
	}

	public JLayeredPane getLayeredPane() {
		return layeredPane;
	}

	public Background getBackgroundUI() {
		return backgroundUI;
	}

	public Effect getEffect() {
		return effect;
	}

	public JFrameGame getGameFrame() {
		return gameFrame;
	}

	public PlayerInfo getPlayerInfo() {
		return playerInfo;
	}

	public void setGameFrame(JFrameGame gameFrame) {
		this.gameFrame = gameFrame;
	}

	/**
	 * 
	 * init game config
	 * 
	 */
	public void startGamePanelInit() {
		for (Layer temp : this.lays) {
			// refresh window UI
			temp.startPanel();
		}
	}

}
