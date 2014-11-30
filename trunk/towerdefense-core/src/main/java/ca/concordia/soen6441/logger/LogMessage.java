package ca.concordia.soen6441.logger;

import java.io.Serializable;
import java.util.Date;

import javax.inject.Inject;

import ca.concordia.soen6441.guice.GamePlayFactory;
import ca.concordia.soen6441.logic.GameMap;
import ca.concordia.soen6441.logic.GamePlay;


/**
 * The Class LogMessage.
 */
public class LogMessage implements Serializable, Comparable<LogMessage>{


	/** The source. */
	private final Object source;
	
	/** The time stamp. */
	private final Date timeStamp;

	/** The message. */
	private final String message;

	/**
	 * Instantiates a new log message.
	 *
	 * @param source the source
	 * @param timeStamp the time stamp
	 * @param message the message
	 */
	public LogMessage(Object source, Date timeStamp, String message) {
		super();
		this.source = source;
		this.timeStamp = timeStamp;
		this.message = message;
	}

	/**
	 * Gets the time stamp.
	 *
	 * @return the time stamp
	 */
	public Date getTimeStamp() {
		return timeStamp;
	}

	/**
	 * Gets the message.
	 *
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * This method will compare the value
	 */
	@Override
	public int compareTo(LogMessage o) {
		return timeStamp.compareTo(o.timeStamp);
	}

	/**
	 * Gets the source.
	 *
	 * @return the source
	 */
	public Object getSource() {
		return source;
	}
	
	/**
	 * This method will convert into string
	 */
	@Override
	public String toString() {
		return "" + timeStamp + " " + message;
	}
}
