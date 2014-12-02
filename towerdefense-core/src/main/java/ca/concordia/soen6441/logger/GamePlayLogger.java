package ca.concordia.soen6441.logger;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Observable;
import java.util.SortedSet;
import java.util.TreeSet;

import ca.concordia.soen6441.logger.filter.GamePlayLogFilter;
import ca.concordia.soen6441.logger.filter.AllTowersLogFilter;
import ca.concordia.soen6441.logger.filter.LogFilter;
import ca.concordia.soen6441.logger.filter.TowerLogFilter;
import ca.concordia.soen6441.logger.filter.WaveLogFilter;
import ca.concordia.soen6441.logic.EnemyWave;
import ca.concordia.soen6441.logic.tower.Tower;

/**
 * The Class GamePlayLogger.
 */
public class GamePlayLogger extends Observable implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5717998303500931498L;

	/** The log messages. */
	private List<LogMessage> logMessages = new ArrayList<>();
	
	/** The log filters. */
	private SortedSet<LogFilter> logFilters = new TreeSet<>();
	
	/** The current wave. */
	private EnemyWave currentWave = null;
	
	/**
	 * Instantiates a new log manager.
	 */
	public GamePlayLogger() {
		logFilters.add(new GamePlayLogFilter());
	}
	
	/**
	 * Gets the logs for.
	 *
	 * @param filter the filter
	 * @return the logs for
	 */
	public List<LogMessage> getLogsFor(LogFilter filter) {
		List<LogMessage> returnedMessages = new ArrayList<>();
		for (LogMessage logMessage : logMessages) {
			if (filter.filter(logMessage)) {
				returnedMessages.add(logMessage);
			}
		}
		return returnedMessages;
	}

	/**
	 * Gets the log filters.
	 *
	 * @return the log filters
	 */
	public List<LogFilter> getLogFilters() {
		List<LogFilter> logFiltersExample = new ArrayList<>(logFilters);
		return logFiltersExample;
	}

	/**
	 * Log the message with a source
	 *
	 * @param source the source
	 * @param format the format
	 * @param enemyWave the enemy wave
	 */
	public void log(Object source, String format, EnemyWave enemyWave) {
		if (currentWave != enemyWave) {
			currentWave = enemyWave;
			createLogFiltersFor(currentWave);
		}
		LogMessage logMessage = new WaveLogMessage(source, currentWave, new Date(), String.format(format, enemyWave));
		logMessages.add(logMessage);
		setChanged();
		notifyObservers();
		
		System.out.println("" + logMessage);
		createLogFiltersFor(source);
		setChanged();
		notifyObservers(logMessage);
	}
	


	/**
	 * Log the message with a source
	 *
	 * @param source the source
	 * @param format the format
	 * @param args the args
	 */
	public void log(Object source, String format, Object ... args) {
		LogMessage logMessage = new LogMessage(source, new Date(), String.format(format, args));
		if (currentWave != null) {
			logMessage = new WaveLogMessage(source, currentWave, new Date(), String.format(format, args));
		}
		System.out.println("" + logMessage);
		logMessages.add(logMessage);
		createLogFiltersFor(source);
		setChanged();
		notifyObservers(logMessage);
	}
	
	/**
	 * Creates the log filters for.
	 *
	 * @param source the source
	 */
	private void createLogFiltersFor(Object source) {
		if (source instanceof Tower) {
			Tower tower = (Tower) source;
			logFilters.add(new AllTowersLogFilter());
			logFilters.add(new TowerLogFilter(tower));
		}
		else if (source instanceof EnemyWave) {
			EnemyWave enemyWave = (EnemyWave) source;
			logFilters.add(new WaveLogFilter(enemyWave));
		}
	}
}
