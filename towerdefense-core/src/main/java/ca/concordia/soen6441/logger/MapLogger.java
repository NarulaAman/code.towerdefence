package ca.concordia.soen6441.logger;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import ca.concordia.soen6441.logic.MapLoggerDao;



/**
 * The Class MapLogger.
 */

public class MapLogger implements Serializable{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 4682860387217811691L;
	
	@Inject static MapLoggerDao mapLoggerDao;

	private String mapName;
	
	/** The log messages. */
	private List<LogMessage> logMessages = new ArrayList<>();
	
	/**
	 * Instantiates a new map logger.
	 *
	 * @param name the name
	 */
	public MapLogger(String name) {
		this.mapName = name;
	}
	
	/**
	 * Log.
	 *
	 * @param message the message
	 */
	public void log(String message) {
		logMessages.add(new LogMessage(this, new Date(), message));
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return mapName;
	}
	
	/**
	 * Gets the log messages.
	 *
	 * @return the log messages
	 */
	public List<LogMessage> getLogMessages() {
		return logMessages;
	}
	
	public void save() {
		mapLoggerDao.save(this);
	}
	
}
