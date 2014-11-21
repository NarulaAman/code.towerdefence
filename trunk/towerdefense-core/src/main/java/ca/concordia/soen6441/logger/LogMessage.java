package ca.concordia.soen6441.logger;

import java.sql.Date;

public class LogMessage implements Comparable<LogMessage>{
	
	private Date timeStamp;
	private String message;

	@Override
	public int compareTo(LogMessage o) {
		return timeStamp.compareTo(o.timeStamp);
	}
}
