package ca.concordia.soen6441.logger;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Observable;
import java.util.SortedSet;
import java.util.TreeSet;

import ca.concordia.soen6441.logic.EnemyWave;
import ca.concordia.soen6441.logic.tower.Tower;

public class LogManager extends Observable implements Serializable {

	private List<LogMessage> logMessages = new ArrayList<>();
	
	private SortedSet<LogFilter> logFilters = new TreeSet<>();
	
	private EnemyWave currentWave = null;
	
	public LogManager() {
		logFilters.add(new AllMessagesFIlter());
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

	public List<LogFilter> getLogFilters() {
		List<LogFilter> logFiltersExample = new ArrayList<>(logFilters);
		return logFiltersExample;
	}

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
