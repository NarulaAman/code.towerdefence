package ca.concordia.soen6441.logic.tower;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import ca.concordia.soen6441.logic.Enemy;
import ca.concordia.soen6441.logic.TowerFactory;
import ca.concordia.soen6441.logic.primitives.GridPosition;
import ca.concordia.soen6441.logic.tower.FireTower;

/**
 * Tests the special effects of the {@link FireTower}
 *
 */
public class FireTowerTest {

	private final TowerFactory towerFactory = new TowerFactory();
	
	private FireTower tower;
	private Enemy enemy;
	
	@Before
	public void setUp() {
		tower = (FireTower) towerFactory.towerOnCoordinate(FireTower.class, new GridPosition(1, 1));
		enemy = mock(Enemy.class);
	}
	
	
	/**
	 * Test the burn damage of the {@link FireTower}
	 */
	@Test
	public void testBurn() {
		tower.shoot(enemy);
		Mockito.verify(enemy).takeDamage(tower.getDamage());
		Mockito.reset(enemy);
		tower.update(tower.getBurnDurationSecs() + 0.1f);
		Mockito.verify(enemy, times((int)(tower.getBurnDurationSecs() / tower.getBurnRateSecs()))).takeDamage(tower.getBurnDamage());
	}
	/**
	 * Test the burn damage of the {@link FireTower} is not applied after the burning time
	 */
	@Test
	public void testNotBurningAfterBurnDuration() {
		tower.shoot(enemy);
		Mockito.verify(enemy).takeDamage(tower.getDamage());
		// first burn until the max burn duration
		tower.update(tower.getBurnDurationSecs() + 0.1f);
		// now the enemy shouldn't be burnt anymore
		Mockito.reset(enemy);
		tower.update(tower.getBurnDurationSecs() + 0.1f);
		Mockito.verify(enemy, never()).takeDamage(tower.getBurnDamage());
	}
}
