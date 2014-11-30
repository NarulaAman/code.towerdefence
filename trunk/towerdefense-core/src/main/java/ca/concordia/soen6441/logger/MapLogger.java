package ca.concordia.soen6441.logger;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MapLogger implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4682860387217811691L;
	
	private String mapName;
	
	private List<LogMessage> logMessages = new ArrayList<>();
	
	public void log(String message) {
		logMessages.add(new LogMessage(this, new Date(), message));
	}
	
}
