package ca.concordia.soen6441.logger;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LogManager {
	
	private List<LogMessage> logMessages = new ArrayList<>();
	
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
