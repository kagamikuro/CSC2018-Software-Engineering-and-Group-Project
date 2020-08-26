package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import controller.Control;
import model.DiceModel;


/**
 * 
 * dice layer
 * 
 * @author CSC2018 Group 31
 * 
 */
@SuppressWarnings("serial")
public class Dice extends Layer {

	private Control control;
	private DiceModel dice;
	private DiceButton diceButton;
	

	protected Dice(int x, int y, int w, int h, Control control) {
		super(x, y, w, h);
		setLayout(null);
		this.control = control;
		this.dice = control.getDice();
		this.diceButton = new DiceButton(control, 105, 32);
		add(diceButton);
	}

	@Override
	public void paint(Graphics g) {
		//window paint
		this.createWindow(g);
		//dice paint
		this.paintDice(g, -12, -15);
		//dice button display
		this.showDice();
		// dice button refresh
		diceButton.update(g);
	}

	/**
	 * 
	 * dice paint
	 * 
	 */
	private void paintDice(Graphics g, int i, int j) {
		// set dice motion
		if (dice.getStartTick() < dice.getNowTick()
				&& dice.getNextTick() >= dice.getNowTick()) {
			dice.setDiceState(DiceModel.DICE_RUNNING);
		} else {
			dice.setDiceState(DiceModel.DICE_POINT);
		}

		if (dice.getDiceState() == DiceModel.DICE_POINT) {
			this.paintPoint(g, i, j);
		} else if (dice.getDiceState() == DiceModel.DICE_RUNNING) {
			this.paintRunning(g, i, j, dice.getNowTick() % 4 == 0);
		}
		g.setColor(Color.black);
		g.drawString(dice.getRunning().getNowPlayer().getName() + ":", i + 120,
				j + 45);
	}

	public DiceButton getDiceButton() {
		return diceButton;
	}

	/**
	 * 
	 * Dice motion state drawing
	 * 
	 */
	public void paintRunning(Graphics g, int x, int y, boolean change) {
		if (change) {
			dice.addImgCount(1);
		}
		Image temp = dice.getNowImg();
		g.drawImage(temp, x, y, x + temp.getWidth(null),
				y + temp.getHeight(null), 0, 0, temp.getWidth(null),
				temp.getHeight(null), null);
	}

	/**
	 * 
	 * Dice produces points drawing
	 * 
	 */
	public void paintPoint(Graphics g, int x, int y) {
		Image temp = dice.getDicePoints()[dice.getPoint()];
		g.drawImage(temp, x, y, x + temp.getWidth(null),
				y + temp.getHeight(null), 0, 0, temp.getWidth(null),
				temp.getHeight(null), null);
	}

	/**
	 * 
	 * dice button display
	 * 
	 */
	private void showDice() {
		diceButton.setEnabled(dice.isShowDiceLabel());
	}

	@Override
	public void startPanel() {
	}

}
