package ca.concordia.soen6441.logger.filter;

import ca.concordia.soen6441.logger.LogMessage;
import ca.concordia.soen6441.logic.tower.Tower;


/**
 * The Class AllTowersLogFilter.
 */
public class AllTowersLogFilter extends LogFilter {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5221378397687327760L;

	/**
	 * Instantiates a new all towers log filter.
	 */
	public AllTowersLogFilter() {
		super("All Towers");

	}
	
	/**
	 * This method will filter all messages from towers
	 */
	public boolean filter(LogMessage logMessage) {
		return logMessage.getSource() instanceof Tower;
	}

}
