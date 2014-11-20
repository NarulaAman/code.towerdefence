package ca.concordia.soen6441.logic;

import static org.mockito.Mockito.mock;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import ca.concordia.soen6441.logic.primitives.GridPosition;
import ca.concordia.soen6441.logic.tower.FireTower;
import ca.concordia.soen6441.logic.tower.Tower;

/**
 * Tests if the {@link Tower} shoots
 *
 */
public class TowerTest {

	private final TowerFactory towerFactory = new TowerFactory();
	
	private Tower tower;
	private Enemy enemy;
	
	/**
	 * Pre-conditions of the test
	 */
	@Before
	public void setUp() {
		tower = towerFactory.towerOnCoordinate(FireTower.class, new GridPosition(1, 1));
		enemy = mock(Enemy.class);
	}
	
	
	/**
	 * Test that shooting an enemy takes damage off him
	 */
	@Test
	public void testShoot() {
		tower.shoot(enemy);
		Mockito.verify(enemy).takeDamage(tower.getDamage());
	}

}
