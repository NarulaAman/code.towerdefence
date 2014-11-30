package ca.concordia.soen6441.logger;

import java.io.Serializable;
import java.util.Date;

import javax.inject.Inject;

import ca.concordia.soen6441.guice.GamePlayFactory;
import ca.concordia.soen6441.logic.GameMap;
import ca.concordia.soen6441.logic.GamePlay;


public class LogMessage implements Serializable, Comparable<LogMessage>{


	private final Object source;
	
	private final Date timeStamp;

	private final String message;

	public LogMessage(Object source, Date timeStamp, String message) {
		super();
		this.source = source;
		this.timeStamp = timeStamp;
		this.message = message;
	}

	public Date getTimeStamp() {
		return timeStamp;
	}

	public String getMessage() {
		return message;
	}

	@Override
	public int compareTo(LogMessage o) {
		return timeStamp.compareTo(o.timeStamp);
	}

	public Object getSource() {
		return source;
	}
	
	@Override
	public String toString() {
		return "" + timeStamp + " " + message;
	}
}
