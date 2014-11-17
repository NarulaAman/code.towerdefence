package ca.concordia.soen6441.logic.tower;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import javax.vecmath.Point2f;

import org.junit.Before;
import org.junit.Test;

import ca.concordia.soen6441.logic.Enemy;
import ca.concordia.soen6441.logic.TowerFactory;
import ca.concordia.soen6441.logic.primitives.GridPosition;

/**
 * Tests the {@link CannonTower} special effect of splash damage
 *
 */
public class CannonTowerTest {

	private final TowerFactory towerFactory = new TowerFactory();
	
	private CannonTower tower;
	private Enemy enemyTarget;
	private Enemy enemySplashed;
	
	/**
	 * Sets up the preconditions of the test
	 */
	@Before
	public void setUp() {
		tower = (CannonTower) towerFactory.towerOnCoordinate(CannonTower.class, new GridPosition(1, 1));
		enemyTarget = mock(Enemy.class);
		enemySplashed = mock(Enemy.class);
	}
	
	
	/**
	 * Test the Splash damage of the {@link CannonTower}
	 */
	@Test
	public void testSplashDamage() {
		when(enemyTarget.getCurrentPosition()).thenReturn(new Point2f(1, 2.f));
		when(enemySplashed.getCurrentPosition()).thenReturn(new Point2f(1, 2.1f));
		List<Enemy> enemies = new ArrayList<>();
		enemies.add(enemyTarget);
		enemies.add(enemySplashed);
		tower.maybeShoot(enemies);
		verify(enemyTarget).takeDamage(tower.getDamage());
		verify(enemySplashed).takeDamage(anyInt());
		
//		tower.update(tower.getBurnDurationSecs() + 0.1f);
//		Mockito.verify(enemy, times((int)(tower.getBurnDurationSecs() / tower.getBurnRateSecs()))).takeDamage(tower.getBurnDamage());
	}

}
