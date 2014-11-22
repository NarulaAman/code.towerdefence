package ca.concordia.soen6441.logger;

import java.sql.Date;

import javax.inject.Inject;

import ca.concordia.soen6441.guice.GamePlayFactory;
import ca.concordia.soen6441.logic.GameMap;
import ca.concordia.soen6441.logic.GamePlay;

public class LogMessage implements Comparable<LogMessage>{
	
	@Inject
	public GamePlayFactory m;
	
	private Date timeStamp;
	private String message;

	@Override
	public int compareTo(LogMessage o) {
		return timeStamp.compareTo(o.timeStamp);
	}
	
	public GamePlay method() {
		return m.create(new GameMap(3, 3), 100);
	}
}
