package ca.concordia.soen6441.logic.tower.shootingstrategy;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import javax.vecmath.Point2f;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;

import ca.concordia.soen6441.logic.Enemy;
import ca.concordia.soen6441.logic.tower.Tower;

/**
 * Test to shoot the weakest enemy
 *
 */
public class ShootWeakestStrategyTest {
	
	private Tower tower;
	private Enemy enemyTarget;
	private Enemy enemyNotTarget;
	private ShootingStrategy strategy;
	
	/**
	 * Preconditions of the test
	 */
	@Before
	public void setUp() {	
		
		tower = mock(Tower.class);
		enemyTarget = mock(Enemy.class);
		enemyNotTarget = mock(Enemy.class);
		strategy = new ShootWeakestStrategy();
	}
	
	/**
	 * Test that the weakest enemy is shot
	 */
	@Test
	public void testShootEnemyClosestToEndPoint() {
		when(tower.inRange(Matchers.any(Point2f.class))).thenReturn(true);
		when(enemyTarget.getHealth()).thenReturn(50);
		when(enemyNotTarget.getHealth()).thenReturn(100);
		List<Enemy> enemies = new ArrayList<>();
		enemies.add(enemyTarget);
		enemies.add(enemyNotTarget);
		strategy.setTower(tower);
		strategy.shootIfInRange(enemies);
		verify(tower, times(1)).shoot(enemyTarget);
		verify(tower, never()).shoot(enemyNotTarget);
	}

}
