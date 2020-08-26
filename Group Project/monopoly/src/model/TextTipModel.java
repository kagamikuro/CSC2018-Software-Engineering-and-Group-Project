package model;

import java.awt.Color;
import java.awt.Graphics;

import controller.Control;
import view.JPanelGame;
import view.TextTip;

/**
 * 
 * text tip
 * 
 * @author CSC2018 Group 31
 * 
 */
public class TextTipModel extends Tick implements Port{
	
	private PlayerModel player = null;
	
	private String tipString = "The game starts now! Who is the monopoly?";
	
	public TextTipModel (){
	}

	public  String getTipString() {
		return tipString;
	}

	public void setTipString(String tipString) {
		this.tipString = tipString;
	}
	
	
	/**
	 * 
	 * game start init
	 * 
	 */
	public void startGameInit (){}

	@Override
	public void updata(long tick) {
		this.nowTick = tick;
	}

	
	public PlayerModel getPlayer() {
		return player;
	}

	/**
	 * display text tip
	 * 
	 * 
	 * */
	public void showTextTip(PlayerModel player,String str, int time) {
		this.player = player;
		this.setTipString(str);
		this.setStartTick(this.nowTick);
		this.setNextTick(this.nowTick + time * Control.rate);
	}
}
