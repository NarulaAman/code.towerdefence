package ca.concordia.soen6441.logic.tower;

import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyFloat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import ca.concordia.soen6441.logger.LogManager;
import ca.concordia.soen6441.logic.Enemy;
import ca.concordia.soen6441.logic.TowerFactory;
import ca.concordia.soen6441.logic.primitives.GridPosition;

/**
 * Tests the {@link IceTower} special effects
 *
 */
public class IceTowerTest {

	private final LogManager logger = mock(LogManager.class);
	private final TowerFactory towerFactory = new TowerFactory(logger);
	
	private IceTower tower;
	private Enemy enemy;
	
	/**
	 * Preconditions of all tests
	 */
	@Before
	public void setUp() {
		tower = (IceTower) towerFactory.towerOnCoordinate(IceTower.class, new GridPosition(1, 1));
		enemy = mock(Enemy.class);
	}
	
	
	/**
	 * Test the if the {@link IceTower} is slowing down the enemies
	 */
	@Test
	public void testSlowingDownOfTheEnemies() {
		ArgumentCaptor<Float> argument = ArgumentCaptor.forClass(Float.class);
		Mockito.when(enemy.getSpeed()).thenReturn(10.f);
		tower.shoot(enemy);
		tower.update(tower.getSlownessDurationSecs() + 0.1f);
		Mockito.verify(enemy, times(2)).setSpeed(argument.capture());
		assertTrue(argument.getAllValues().get(0).floatValue() < 10.f);
		assertTrue(argument.getAllValues().get(1).floatValue() > 9.9 && argument.getValue().floatValue() < 10.1);
		Mockito.verify(enemy, times(2)).setSpeed(anyFloat()); // original speed was set
	}

}
