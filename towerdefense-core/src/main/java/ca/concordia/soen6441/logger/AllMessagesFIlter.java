package ca.concordia.soen6441.logger;


/**
 * The Class AllMessagesFIlter.
 */
public class AllMessagesFIlter extends LogFilter {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 556940165605471976L;

	/**
	 * Instantiates a new all messages filter.
	 */
	public AllMessagesFIlter() {
		super("All Gameplay Log");
	}
	
	/**
	 * This method will filter 
	 */
	boolean filter(LogMessage logMessage) {
		return true;
	}

}
