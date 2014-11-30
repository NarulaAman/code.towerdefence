package ca.concordia.soen6441.logger;

import ca.concordia.soen6441.logic.EnemyWave;

public class WaveLogFilter extends LogFilter {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1258054367822878849L;

	private final EnemyWave wave;
	
	public WaveLogFilter(EnemyWave wave) {
		super("Wave " + wave.getId());
		this.wave = wave;
	}
	
	@Override
	boolean filter(LogMessage logMessage) {
		if (logMessage instanceof WaveLogMessage) {
			WaveLogMessage waveLog = (WaveLogMessage) logMessage;
			return waveLog.getEnemyWave().getId() == wave.getId();
		}
		return false;
	}
}
