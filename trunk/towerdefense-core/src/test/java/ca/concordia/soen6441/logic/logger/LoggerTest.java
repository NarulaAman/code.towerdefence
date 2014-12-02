package ca.concordia.soen6441.logic.logger;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

import ca.concordia.soen6441.logger.GamePlayLogger;
import ca.concordia.soen6441.logger.filter.AllTowersLogFilter;
import ca.concordia.soen6441.logger.filter.GamePlayLogFilter;
import ca.concordia.soen6441.logger.filter.TowerLogFilter;
import ca.concordia.soen6441.logger.filter.WaveLogFilter;
import ca.concordia.soen6441.logic.EnemyWave;
import ca.concordia.soen6441.logic.tower.Tower;

/**
 * Tests for the logger framework of the system.
 */
public class LoggerTest {

	/** The enemy wave1. */
	private EnemyWave enemyWave1;// = mock(EnemyWave.class);

	/** The enemy wave2. */
	private EnemyWave enemyWave2;// = mock(EnemyWave.class);

	/** The tower1. */
	private Tower tower1;
	
	/** The tower2. */
	private Tower tower2;
	
	/** The game play logger. */
	private GamePlayLogger gamePlayLogger;
	

	/**
	 * Sets the up.
	 */
	@Before
	public void setUp() {
		gamePlayLogger = new GamePlayLogger();
		enemyWave1 = mock(EnemyWave.class);
		when(enemyWave1.getId()).thenReturn(1);
		enemyWave2 = mock(EnemyWave.class);
		when(enemyWave2.getId()).thenReturn(2);
		
		tower1 = mock(Tower.class);
		when(tower1.getId()).thenReturn(1);
		tower2 = mock(Tower.class);
		when(tower2.getId()).thenReturn(2);
	}

	/**
	 * Test logger started empty.
	 */
	@Test
	public void testLoggerStartedEmpty() {
		assertTrue(gamePlayLogger.getLogsFor(new GamePlayLogFilter()).size() == 0);
	}

	/**
	 * Test single individual tower log filter.
	 */
	@Test
	public void testSingleIndividualTowerLog() {
		
		gamePlayLogger.log(tower1, "Buy", tower1);
		assertTrue(gamePlayLogger.getLogsFor(new TowerLogFilter(tower1)).size() == 1);

	}
	
	/**
	 * Test multiple tower log filter.
	 */
	@Test
	public void testMultipleTowerLog() {
		gamePlayLogger.log(tower1, "Buy", tower1);
		gamePlayLogger.log(tower2, "Buy", tower2);
		assertTrue(gamePlayLogger.getLogsFor(new TowerLogFilter(tower1)).size() == 1);
		assertTrue(gamePlayLogger.getLogsFor(new TowerLogFilter(tower2)).size() == 1);
		assertTrue(gamePlayLogger.getLogsFor(new AllTowersLogFilter()).size() == 2);
	}
	
	/**
	 * Test wave log filter.
	 */
	@Test
	public void testWaveLog() {
		gamePlayLogger.log(enemyWave1, "Started", enemyWave1);
		gamePlayLogger.log(tower1, "Buy", tower1);
		gamePlayLogger.log(enemyWave1, "Ended", enemyWave1);
		gamePlayLogger.log(enemyWave2, "Started", enemyWave2);
		gamePlayLogger.log(tower2, "Buy", tower2);
		gamePlayLogger.log(tower1, "Upgrade", tower1);	
		gamePlayLogger.log(enemyWave2, "Ended", enemyWave2);
		assertTrue(gamePlayLogger.getLogsFor(new TowerLogFilter(tower1)).size() == 2);
		assertTrue(gamePlayLogger.getLogsFor(new TowerLogFilter(tower2)).size() == 1);
		assertTrue(gamePlayLogger.getLogsFor(new AllTowersLogFilter()).size() == 3);
		assertTrue(gamePlayLogger.getLogsFor(new WaveLogFilter(enemyWave1)).size() == 3);
		assertTrue(gamePlayLogger.getLogsFor(new WaveLogFilter(enemyWave2)).size() == 4);
	}
	
	/**
	 * Test all messages filter.
	 */
	@Test
	public void testAllMessages() {
		gamePlayLogger.log(enemyWave1, "Started", enemyWave1);
		gamePlayLogger.log(tower1, "Buy", tower1);
		gamePlayLogger.log(enemyWave1, "Ended", enemyWave1);
		gamePlayLogger.log(enemyWave2, "Started", enemyWave2);
		gamePlayLogger.log(tower1, "Upgrade", tower1);	
		assertTrue(gamePlayLogger.getLogsFor(new GamePlayLogFilter()).size() == 5);
	}

}
