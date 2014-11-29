package ca.concordia.soen6441.logger;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LogManager implements Serializable {

	private List<LogMessage> logMessages = new ArrayList<>();
	private final String[] logTypes = { "Select Log", "Game Log", "Tower Log", "Map Log" };
	
	
	public LogManager() {
		logMessages.add(new LogMessage(new Date(), "amand"));
		logMessages.add(new LogMessage(new Date(), "alex"));
		logMessages.add(new LogMessage(new Date(), "amado"));
	}
	
	public List<LogMessage> getLogsFor(Object object) {
		return logMessages;
	}

	public void log(Class<?> declaringClass, Object object, String logMessage) {
		logMessages.add(new LogMessage(new Date(), logMessage));
		System.out.println(logMessage);
	}
	
}
