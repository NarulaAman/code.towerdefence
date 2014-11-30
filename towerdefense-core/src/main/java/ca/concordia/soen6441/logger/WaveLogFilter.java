package ca.concordia.soen6441.logger;

import ca.concordia.soen6441.logic.EnemyWave;


/**
 * The Class WaveLogFilter.
 */
public class WaveLogFilter extends LogFilter {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1258054367822878849L;

	/** The wave. */
	private final EnemyWave wave;
	
	/**
	 * Instantiates a new wave log filter.
	 *
	 * @param wave the wave
	 */
	public WaveLogFilter(EnemyWave wave) {
		super("Wave " + wave.getId());
		this.wave = wave;
	}
	
	/**
	 * This method will filter
	 */
	@Override
	boolean filter(LogMessage logMessage) {
		if (logMessage instanceof WaveLogMessage) {
			WaveLogMessage waveLog = (WaveLogMessage) logMessage;
			return waveLog.getEnemyWave().getId() == wave.getId();
		}
		return false;
	}
}
