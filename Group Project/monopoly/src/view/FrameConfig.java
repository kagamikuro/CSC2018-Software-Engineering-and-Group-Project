package view;

import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import controller.Control;
import controller.GameRunning;
import model.PlayerModel;
import util.FrameUtil;

/**
 * 
 * load user config
 * 
 * */
public class FrameConfig extends JFrame {

	private JButton jbnStart = new JButton("game start");

	private JButton jbnCancel = new JButton("reset config");

	private JButton jbnPlayer01 = new JButton("1P character confirm");
	private JLabel jbnPlayerNameLabel01 = new JLabel("name:");
	private JTextField jbnPlayerNameField01 = new JTextField(12);
	private JButton jbnPlayerName01 = new JButton("1P name confirm");

	private JButton jbnPlayer02 = new JButton("2P character confirm");
	private JLabel jbnPlayerNameLabel02 = new JLabel("name:");
	private JTextField jbnPlayerNameField02 = new JTextField(12);
	private JButton jbnPlayerName02 = new JButton("2P name confirm");

	/**
	 * option tabs
	 * */
	private JTabbedPane tabs;

	/**
	 * optional photo
	 * */
	private ImageIcon[] img = Photo.PLAYER_CHOOSE;
	/**
	 * player01
	 **/
	private JLabel jlPlayer01Choose = null;
	private final JLabel jlPlayer01Selected = new JLabel(
			Photo.PLAYER_01_SELECTED);
	private JButton leftButton01;
	private JButton rightButton01;

	/**
	 * player 02
	 **/
	private JLabel jlPlayer02Choose = null;
	private final JLabel jlPlayer02Selected = new JLabel(
			Photo.PLAYER_02_SELECTED);
	private JButton leftButton02;
	private JButton rightButton02;
	/**
	 * 1P 2P choose able character
	 */
	private int[] chooses = { 0, 0 };
	/**
	 * 1P 2P choosen character
	 */
	private int[] selected = { -1, -2 };
	/**
	 * 1P 2P filled name
	 */
	private String[] selectedName = { "", "" };

	/**
	 * 
	 * main frame
	 * 
	 * */
	private JFrameGame jFrameGame;

	public FrameConfig(WaitFrame wFrame,JFrameGame jFrameGame) {
		wFrame.setVisible(false);
		this.jFrameGame = jFrameGame;
		setTitle("user data confirm");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// set border layout
		this.setLayout(new BorderLayout());
		// add main panel
		this.add(this.createMainPanel(), BorderLayout.CENTER);
		// add button panel
		this.add(this.createButtonPanel(), BorderLayout.SOUTH);
		this.setResizable(false);
		this.setSize(380, 370);
		// center
		FrameUtil.setFrameCenter(this);
		setVisible(true);
	}

	/**
	 * add main panel
	 */
	private JTabbedPane createMainPanel() {
		this.tabs = new JTabbedPane();
		this.tabs.setOpaque(false);
		this.tabs.add("player config", this.createPlayerSelectPanel());
		this.tabs.setToolTipTextAt(0, "could player config");
		this.tabs.add("scene config", this.createMapSelectPanel());
		this.tabs.setToolTipTextAt(1, "could config game scene");
		this.tabs.add("game config", this.createGameSelectPanel());
		this.tabs.setToolTipTextAt(2, "could set win condition.");
		return tabs;
	}

	/**
	 * 
	 * game win condition
	 * 
	 */
	private Component createGameSelectPanel() {
		JPanel panel = new JPanel(new GridLayout(0, 1));
		panel.setBackground(new Color(235,236,237));

		// --------------------------------
		final JPanel dayPanel = new JPanel();
		dayPanel.setBorder(BorderFactory.createTitledBorder(""));
		JLabel day = new JLabel("game days");
		final String[] days = { "no limit", "20", "40", "80", "120", "240", "480" };
		final Choice daysChoice = new Choice();

		for (String a : days) {
			daysChoice.add(a);
		}
		daysChoice.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent arg0) {
				String str = days[daysChoice.getSelectedIndex()];
				if (str.equals("no limit")) {
					GameRunning.GAME_DAY = -1;
				} else {
					GameRunning.GAME_DAY = Integer.parseInt(str);
				}
			}
		});
		dayPanel.add(day);
		dayPanel.add(daysChoice);

		// --------------------------------
		JPanel moneyPanel = new JPanel();
		moneyPanel.setBorder(BorderFactory.createTitledBorder(""));
		JLabel money = new JLabel("winning money");
		final String[] money_ = { "no limit", "10000", "20000", "40000", "80000",
				"200000" };
		final Choice moneyChoice = new Choice();
		for (String a : money_) {
			moneyChoice.add(a);
		}
		moneyChoice.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent arg0) {
				String str = money_[moneyChoice.getSelectedIndex()];
				if (str.equals("no limit")) {
					GameRunning.MONEY_MAX = -1;
				} else {
					GameRunning.MONEY_MAX = Integer.parseInt(str);
				}
			}
		});
		moneyPanel.add(money);
		moneyPanel.add(moneyChoice);
		// ---------------------------------
		// --------------------------------
		JPanel cashPanel = new JPanel();
		cashPanel.setBorder(BorderFactory.createTitledBorder(""));
		JLabel cash = new JLabel("player initial money");
		final String[] cash_ = { "1000", "2000", "5000", "7000", "10000",
				"20000" };
		final Choice cashChoice = new Choice();
		for (String a : cash_) {
			cashChoice.add(a);
		}
		cashChoice.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent arg0) {
				String str = cash_[cashChoice.getSelectedIndex()];
					GameRunning.PLAYER_CASH = Integer.parseInt(str);
//					System.out.println(GameRunning.PLAYER_CASH);
				}
		});
		cashPanel.add(cash);
		cashPanel.add(cashChoice);

		JPanel infoPanel = new JPanel();
		infoPanel.setBorder(BorderFactory.createTitledBorder(""));
		JLabel info = new JLabel();
		info.setText("<html>could change game winning condition.<strong>(default is break down)</strong></html>");
		infoPanel.add(info);

		panel.add(dayPanel);
		panel.add(moneyPanel);
		panel.add(cashPanel);
		panel.add(infoPanel);
		return panel;
	}

	/**
	 * 
	 * map select panel
	 * 
	 */
	private JPanel createMapSelectPanel() {
		JPanel jp = new JPanel();
		jp.setLayout(new GridLayout());
		jp.setBackground(new Color(235,236,237));
		JPanel lPane = new JPanel(new BorderLayout());
		String[] maps = { "\"LOVE map\"", "\"ghost map\"", "\"lucky map\"" };
		final ImageIcon[] maps1 = {
				new ImageIcon("images/other/1.png"),
				new ImageIcon("images/other/2.png"),
				new ImageIcon("images/other/3.png") };
		final JList jlst = new JList(maps);
		jlst.setSelectedIndex(0);
		final JLabel mapV = new JLabel(maps1[0]);
		final JButton ok = new JButton("yes");
		ok.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				GameRunning.MAP = jlst.getSelectedIndex() + 1;
				ok.setText("selected");
			}
		});
		jlst.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				mapV.setIcon(maps1[jlst.getSelectedIndex()]);
				ok.setText("yes");
			}
		});
		lPane.add(jlst);
		lPane.add(ok, BorderLayout.SOUTH);
		JPanel rPane = new JPanel();
		rPane.add(mapV);
		JSplitPane jSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
				false, lPane, rPane);
		jp.add(jSplitPane);
		return jp;
	}

	/**
	 * player select panel
	 * */
	private JPanel createPlayerSelectPanel() {
		JPanel jp = new JPanel();
		jp.setLayout(null);
		jp.setBackground(new Color(235,236,237));
		// add 1P panel
		addPlayer01Config(12, 0, jp);
		// add 2p panel
		addPlayer02Config(212, 0, jp);
		// add reset button
		addCancelButton(jp);
		return jp;
	}

	private void addCancelButton(JPanel panel) {
		jbnCancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
					reLoad();
			}

			/**
			 * Reload character selection tab
			 */
			private void reLoad() {
				leftButton01.setEnabled(true);
				rightButton01.setEnabled(true);
				jbnPlayer01.setEnabled(true);
				jlPlayer01Selected.setVisible(false);
				jlPlayer01Choose.setIcon(img[0]);
				jbnPlayerNameField01.setText("");
				jbnPlayerNameField01.setEditable(true);
				jbnPlayerName01.setEnabled(true);
				selected[0] = -1;
				chooses[0] = 0;

				leftButton02.setEnabled(true);
				rightButton02.setEnabled(true);
				jbnPlayer02.setEnabled(true);
				jlPlayer02Selected.setVisible(false);
				jlPlayer02Choose.setIcon(img[0]);
				jbnPlayerNameField02.setText("");
				jbnPlayerNameField02.setEditable(true);
				jbnPlayerName02.setEnabled(true);
				selected[1] = -2;
				chooses[1] = 0;
				repaint();
			}
		});
		jbnCancel.setBounds(256 + 7, 235, 80, 30);
		panel.add(jbnCancel);
	}

	/**
	 *add 1p config
	 */
	private void addPlayer01Config(int x, int y, JPanel jp) {
		// create player image label
		jlPlayer01Choose = new JLabel(img[chooses[0]]);
		jlPlayer01Choose.setBounds(x + 8, y, 128, 128);
		// create player image choosen label
		jlPlayer01Selected.setBounds(x + 8, y, 128, 128);
		jlPlayer01Selected.setVisible(false);
		// create left button 
		leftButton01 = this.createButton(x, 92 + y, Photo.BUTTON_LEFT, 'a');
		// add listener
		leftButton01.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// set as loop
				if (chooses[0] <= 0) {
					chooses[0] = img.length;
				}
				jlPlayer01Choose.setIcon(img[--chooses[0]]);
			}
		});

		jp.add(leftButton01);
		// create right button
		rightButton01 = this.createButton(128 + x, 92 + y, Photo.BUTTON_RIGHT,
				'd');
		// add listener
		rightButton01.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// set loop
				if (chooses[0] >= img.length - 1) {
					chooses[0] = -1;
				}
				jlPlayer01Choose.setIcon(img[++chooses[0]]);
			}
		});
		jp.add(rightButton01);
		// add confirm frame
		jbnPlayer01.setBounds(12 + x, 128 + y, 120, 30);
		// add event listener
		jbnPlayer01.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if ((chooses[0] != selected[1])) {
					// set cannot click
					leftButton01.setEnabled(false);
					rightButton01.setEnabled(false);
					jbnPlayer01.setEnabled(false);
					// add select image
					jlPlayer01Selected.setVisible(true);
					selected[0] = chooses[0];
				}
			}
		});
		jp.add(jbnPlayer01);
		jp.add(jlPlayer01Selected);
		jp.add(jlPlayer01Choose);
		// add name frame
		jbnPlayerNameLabel01.setBounds(x + 12, y + 128 + 36, 50, 30);
		jbnPlayerNameField01.setBounds(x + 12 + 30, y + 128 + 36, 120 - 30, 30);
		jbnPlayerName01.setBounds(x + 12, y + 128 + 36 + 36, 120, 30);
		// button add listener
		jbnPlayerName01.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!jbnPlayerNameField01.getText().equals("")) {
					selectedName[0] = jbnPlayerNameField01.getText();
					jbnPlayerNameField01.setEditable(false);
					jbnPlayerName01.setEnabled(false);

				}

			}
		});
		jp.add(jbnPlayerNameLabel01);
		jp.add(jbnPlayerNameField01);
		jp.add(jbnPlayerName01);
	}

	/**
	 * add 2p panel
	 */
	private void addPlayer02Config(int x, int y, JPanel jp) {
		// create player image label
		jlPlayer02Choose = new JLabel(img[chooses[1]]);
		jlPlayer02Choose.setBounds(x + 8, y, 128, 128);
		// create player image choosen label
		jlPlayer02Selected.setBounds(x + 8, y, 128, 128);
		jlPlayer02Selected.setVisible(false);
		// create left button
		leftButton02 = this.createButton(x, 92 + y, Photo.BUTTON_LEFT, 'a');
		//  add listener
		leftButton02.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if (chooses[1] <= 0) {
					chooses[1] = img.length;
				}
				jlPlayer02Choose.setIcon(img[--chooses[1]]);
			}
		});

		jp.add(leftButton02);
	
		rightButton02 = this.createButton(128 + x, 92 + y, Photo.BUTTON_RIGHT,
				'd');

		rightButton02.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
		
				if (chooses[1] >= img.length - 1) {
					chooses[1] = -1;
				}
				jlPlayer02Choose.setIcon(img[++chooses[1]]);
			}
		});

		jp.add(rightButton02);

		jbnPlayer02.setBounds(12 + x, 128 + y, 120, 30);
		
		jbnPlayer02.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (selected[0] != chooses[1]) {
				
					leftButton02.setEnabled(false);
					rightButton02.setEnabled(false);
					jbnPlayer02.setEnabled(false);
				
					jlPlayer02Selected.setVisible(true);
					selected[1] = chooses[1];
				}
			}
		});
		jp.add(jbnPlayer02);
		jp.add(jlPlayer02Selected);
		jp.add(jlPlayer02Choose);

		jbnPlayerNameLabel02.setBounds(x + 12, y + 128 + 36, 50, 30);
		jbnPlayerNameField02.setBounds(x + 12 + 30, y + 128 + 36, 120 - 30, 30);
		jbnPlayerName02.setBounds(x + 12, y + 128 + 36 + 36, 120, 30);

		jbnPlayerName02.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!jbnPlayerNameField02.getText().equals("")) {
					selectedName[1] = jbnPlayerNameField02.getText();
					jbnPlayerNameField02.setEditable(false);
					jbnPlayerName02.setEnabled(false);

				}

			}
		});
		jp.add(jbnPlayerNameLabel02);
		jp.add(jbnPlayerNameField02);
		jp.add(jbnPlayerName02);
	}

	/**
	 * 
	 * icon button
	 * 
	 * */
	public JButton createButton(int x, int y, ImageIcon[] img, char keyLinstenr) {
		JButton add = new JButton("", img[0]);
		add.setPressedIcon(img[3]);
		add.setRolloverIcon(img[2]);
		add.setMnemonic(keyLinstenr);
		add.setBounds(x, y, img[0].getIconWidth(), img[0].getIconHeight());
		return add;
	}

	/**
	 * add button panel
	 */
	private JPanel createButtonPanel() {
		JPanel jp = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		
		// Start button to add a listener
		jbnStart.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (selected[0] < 0 || selected[1] < 0) {
					JOptionPane.showMessageDialog(null, "please finish player config!");
				} else if (selectedName[0].equals("")
						|| selectedName[1].equals("")) {
					JOptionPane.showMessageDialog(null, "please finish name config!");
				} else {
					int choose = JOptionPane.showConfirmDialog(null, "start£¿");
					if (choose == JOptionPane.OK_OPTION) {
						// game start
						startGame();
					}
				}
			}

			/**
			 * game start
			 * */
			private void startGame() {
				setVisible(false);
				jFrameGame.setVisible(true);
				Control control = jFrameGame.getPanelGame().getControl();
				// deal with player data
				dealPlayers(control);
				// Controller start
				control.start();
			}

			/**
			 * deal with player data config
			 */
			private void dealPlayers(Control control) {
				List<PlayerModel> tempPlayer = control.getPlayers();
				// pass name
				tempPlayer.get(0).setName(selectedName[0]);
				tempPlayer.get(1).setName(selectedName[1]);
				// pass player code
				tempPlayer.get(0).setPart(selected[0]);
				tempPlayer.get(1).setPart(selected[1]);
				// pass component
				tempPlayer.get(0).setOtherPlayer(tempPlayer.get(1));
				tempPlayer.get(1).setOtherPlayer(tempPlayer.get(0));
			}

		});

		jp.add(jbnStart);
		return jp;
	}
}
