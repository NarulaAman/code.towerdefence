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
 * Test to shoot the enemy closest to the tower
 *
 */
public class ShootClosestToTowerStrategyTest {

	
	private Tower tower;
	private Enemy enemyTarget;
	private Enemy enemyNotTarget;
	private Point2f enemyTargetPosition;
	private Point2f enemyNotTargetPosition;
	private ShootingStrategy strategy;
	
	/**
	 * Preconditions of the test
	 */
	@Before
	public void setUp() {		
		tower = mock(Tower.class);
		enemyTarget = mock(Enemy.class);
		enemyTargetPosition = mock(Point2f.class);
		enemyNotTarget = mock(Enemy.class);
		enemyNotTargetPosition = mock(Point2f.class);
		strategy = new ShootClosestToTowerStrategy();
		
	}
	
	/**
	 * Test that the enemy closest to the tower is shot
	 */
	@Test
	public void testShootEnemyClosestToEndPoint() {
		when(tower.inRange(Matchers.any(Point2f.class))).thenReturn(true);
		when(tower.inRange(Matchers.any(Point2f.class))).thenReturn(true);
		when(enemyTarget.getCurrentPosition()).thenReturn(enemyTargetPosition);
		when(enemyNotTarget.getCurrentPosition()).thenReturn(enemyNotTargetPosition);
		when(tower.distanceTo(enemyTargetPosition)).thenReturn(1.f);
		when(tower.distanceTo(enemyNotTargetPosition)).thenReturn(2.f);
		List<Enemy> enemies = new ArrayList<>();
		enemies.add(enemyTarget);
		enemies.add(enemyNotTarget);
		strategy.setTower(tower);
		strategy.shootIfInRange(enemies);
		verify(tower, times(1)).shoot(enemyTarget);
		verify(tower, never()).shoot(enemyNotTarget);
	}

}
