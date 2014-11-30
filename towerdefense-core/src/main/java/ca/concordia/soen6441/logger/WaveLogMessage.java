package ca.concordia.soen6441.logger;

import java.util.Date;

import ca.concordia.soen6441.logic.EnemyWave;

/**
 * The Class WaveLogMessage.
 */
public class WaveLogMessage extends LogMessage {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -348926985873892507L;
	
	/** The enemy wave. */
	private final EnemyWave enemyWave;

	/**
	 * Instantiates a new wave log message.
	 *
	 * @param source the source
	 * @param enemyWave the enemy wave
	 * @param timeStamp the time stamp
	 * @param message the message
	 */
	public WaveLogMessage(Object source, EnemyWave enemyWave, Date timeStamp, String message) {
		super(source, timeStamp, message);
		this.enemyWave = enemyWave;
	}
	
	/**
	 * This method will return the string
	 */
	
	@Override
	public String toString() {
		return " WAVE LOG: " + super.toString();
	}

	/**
	 * Gets the enemy wave.
	 *
	 * @return the enemy wave
	 */
	public EnemyWave getEnemyWave() {
		return enemyWave;
	}
	
	

}
