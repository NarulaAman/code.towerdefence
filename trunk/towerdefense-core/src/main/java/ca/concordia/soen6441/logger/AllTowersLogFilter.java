package ca.concordia.soen6441.logger;

import ca.concordia.soen6441.logic.tower.Tower;


/**
 * The Class AllTowersLogFilter.
 */
public class AllTowersLogFilter extends LogFilter {

	/**
	 * Instantiates a new all towers log filter.
	 */
	public AllTowersLogFilter() {
		super("All Towers");

	}
	
/**
 * This method will filter 
 */
	boolean filter(LogMessage logMessage) {
		return logMessage.getSource() instanceof Tower;
	}

}
