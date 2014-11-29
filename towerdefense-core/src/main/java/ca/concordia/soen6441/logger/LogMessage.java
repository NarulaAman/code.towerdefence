package ca.concordia.soen6441.logger;

import java.io.Serializable;
import java.util.Date;

import javax.inject.Inject;

import ca.concordia.soen6441.guice.GamePlayFactory;
import ca.concordia.soen6441.logic.GameMap;
import ca.concordia.soen6441.logic.GamePlay;


public class LogMessage implements Serializable, Comparable<LogMessage>{


	@Inject
	public GamePlayFactory m;
	
	private Date timeStamp;

	private String message;



	public LogMessage(Date timeStamp, String message) {
		super();
		this.timeStamp = timeStamp;
		this.message = message;
	}



	public Date getTimeStamp() {
		return timeStamp;
	}



	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}



	public String getMessage() {
		return message;
	}



	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public int compareTo(LogMessage o) {
		return timeStamp.compareTo(o.timeStamp);
	}
}
