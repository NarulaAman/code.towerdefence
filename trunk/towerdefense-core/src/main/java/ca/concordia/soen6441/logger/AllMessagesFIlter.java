package ca.concordia.soen6441.logger;

public class AllMessagesFIlter extends LogFilter {

	/**
	 * 
	 */
	private static final long serialVersionUID = 556940165605471976L;

	public AllMessagesFIlter() {
		super("All Gameplay Log");
	}
	
	boolean filter(LogMessage logMessage) {
		return true;
	}

}
