package ca.concordia.soen6441.logger;

import ca.concordia.soen6441.logic.tower.Tower;

public class TowerLogFilter extends LogFilter {

	private int towerId;
	
	public TowerLogFilter(int towerId) {
		super("Tower " + towerId);

	}
	
	
	boolean filter(LogMessage logMessage) {
		if (Tower.class.isAssignableFrom(logMessage.getSource().getClass())) {
			if (logMessage.getSource() instanceof Tower) {
				Tower tower = (Tower) logMessage.getSource();
//				return tower.getId() == towerId;
				return true;
			}
		}
		return false;
	}

}
