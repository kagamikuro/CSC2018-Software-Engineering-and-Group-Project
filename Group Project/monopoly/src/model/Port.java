package model;

public interface Port {
	/**
	 * 
	 * Model update
	 * 
	 */
	public abstract void updata(long tick);
	
	/**
	 * 
	 * game initialization
	 * 
	 */
	public abstract void startGameInit();
}
