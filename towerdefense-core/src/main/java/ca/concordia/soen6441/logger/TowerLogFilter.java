package ca.concordia.soen6441.logger;

import ca.concordia.soen6441.logic.tower.Tower;


/**
 * The Class TowerLogFilter.
 */
public class TowerLogFilter extends LogFilter {

	/** The tower id. */
	private final int towerId;
	
	/**
	 * Instantiates a new tower log filter.
	 *
	 * @param tower the tower
	 */
	public TowerLogFilter(Tower tower) {
		super("Tower " + tower.getId());
		this.towerId = tower.getId();
	}
	
	
	/**
	 * This method will filter the LogMessage
	 */
	boolean filter(LogMessage logMessage) {
		if (Tower.class.isAssignableFrom(logMessage.getSource().getClass())) {
			if (logMessage.getSource() instanceof Tower) {
				Tower tower = (Tower) logMessage.getSource();
				return tower.getId() == towerId;
			}
		}
		return false;
	}

}
