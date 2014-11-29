package ca.concordia.soen6441.logger;

import ca.concordia.soen6441.logic.tower.Tower;

public class AllTowersLogFilter extends LogFilter {

	public AllTowersLogFilter() {
		super("All Towers");

	}
	
	
	boolean filter(LogMessage logMessage) {
		return logMessage.getSource() instanceof Tower;
	}

}
