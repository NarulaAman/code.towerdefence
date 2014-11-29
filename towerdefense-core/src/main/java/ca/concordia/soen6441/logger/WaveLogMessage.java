package ca.concordia.soen6441.logger;

import java.util.Date;

import ca.concordia.soen6441.logic.EnemyWave;

public class WaveLogMessage extends LogMessage {

	/**
	 * 
	 */
	private static final long serialVersionUID = -348926985873892507L;
	
	private final EnemyWave enemyWave;

	public WaveLogMessage(EnemyWave enemyWave, Date timeStamp, String message) {
		super(timeStamp, message);
		this.enemyWave = enemyWave;
	}
	
	@Override
	public String toString() {
		return " WAVE LOG: " + super.toString();
	}

	public EnemyWave getEnemyWave() {
		return enemyWave;
	}
	
	

}
