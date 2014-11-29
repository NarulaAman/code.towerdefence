package ca.concordia.soen6441.logger;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Observable;

public class LogManager extends Observable implements Serializable {

	private List<LogMessage> logMessages = new ArrayList<>();
	
	
	public LogManager() {
		logMessages.add(new LogMessage(new Date(), "amand"));
		logMessages.add(new LogMessage(new Date(), "alex"));
		logMessages.add(new LogMessage(new Date(), "amado"));
	}
	
	public List<LogMessage> getLogsFor(LogFilter filter) {
		List<LogMessage> returnedMessages = new ArrayList<>();
		for (LogMessage logMessage : logMessages) {
			if (filter.filter(logMessage)) {
				returnedMessages.add(logMessage);
			}
		}
		return returnedMessages;
	}

	public void log(Class<?> declaringClass, Object object, String logMessage) {
		logMessages.add(new LogMessage(new Date(), logMessage));
		System.out.println(logMessage);
	}
	
	
	public List<LogFilter> getLogFilters() {
		List<LogFilter> logFiltersExample = new ArrayList<>();
		logFiltersExample.add(new LogFilter("asldfkjs"));
		logFiltersExample.add(new LogFilter("955lfsds"));
		logFiltersExample.add(new LogFilter("--123dfkjs"));
		return logFiltersExample;
	}
}
