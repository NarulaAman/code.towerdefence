package ca.concordia.soen6441.logger.filter;

import ca.concordia.soen6441.logger.LogMessage;


/**
 * The Class Game play log filter.
 */
public class GamePlayLogFilter extends LogFilter {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 556940165605471976L;

	/**
	 * Instantiates a new all messages filter.
	 */
	public GamePlayLogFilter() {
		super("All Gameplay Log");
	}
	
	/**
	 * This method will filter 
	 */
	public boolean filter(LogMessage logMessage) {
		return true;
	}

}
