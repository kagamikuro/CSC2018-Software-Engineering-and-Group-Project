package model;

/**
 * 
 * Game object parent class
 * 
 * @author CSC2018 Group 31
 *
 */
public class Tick {
	/**
	 * load current tick value
	 */
	protected long nowTick;
	
	/**
	 * tick value of start event
	 */
	protected long startTick;
	/**
	 * event last time
	 */
	protected long lastTime;
	/**
	 * next tick value
	 */
	protected long nextTick;
	
	public long getLastTime() {
		return lastTime;
	}
	
	public long getNowTick() {
		return nowTick;
	}

	public long getStartTick() {
		return startTick;
	}

	public long getNextTick() {
		return nextTick;
	}
	

	public void setNowTick(long nowTick) {
		this.nowTick = nowTick;
	}

	public void setStartTick(long startTick) {
		this.startTick = startTick;
	}

	public void setLastTime(long lastTime) {
		this.lastTime = lastTime;
	}

	public void setNextTick(long nextTick) {
		this.nextTick = nextTick;
	}

}
