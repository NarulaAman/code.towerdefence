package ca.concordia.soen6441.logic.tower.shootingstrategy;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.atLeastOnce;
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
import org.mockito.Mockito;

import ca.concordia.soen6441.logic.Enemy;
import ca.concordia.soen6441.logic.GameMap;
import ca.concordia.soen6441.logic.Tile;
import ca.concordia.soen6441.logic.TowerFactory;
import ca.concordia.soen6441.logic.primitives.GridPosition;
import ca.concordia.soen6441.logic.tower.FireTower;
import ca.concordia.soen6441.logic.tower.Tower;

/**
 * Test to shoot the enemy closest to the end point
 *
 */
public class ShootStrongestStrategyTest {

	
	private final TowerFactory towerFactory = new TowerFactory();
	private Tower tower;
	private Enemy enemyTarget;
	private Enemy enemyNotTarget;
	
	/**
	 * Preconditions of the test
	 */
	@Before
	public void setUp() {	
		
		tower = towerFactory.towerOnCoordinate(FireTower.class, new GridPosition(4, 3));
		tower.setShootingStrategy(new ShootClosestStrategy());
		enemyTarget = mock(Enemy.class);
		enemyNotTarget = mock(Enemy.class);
	}
	
	/**
	 * Test that the enemy closest to the end point is shot
	 */
	@Test
	public void testShootEnemyClosestToEndPoint() {
		when(enemyTarget.getCurrentPosition()).thenReturn(new Point2f(3, 4));
		when(enemyNotTarget.getCurrentPosition()).thenReturn(new Point2f(2, 3));
		List<Enemy> enemies = new ArrayList<>();
		enemies.add(enemyTarget);
		enemies.add(enemyNotTarget);
		tower.maybeShoot(enemies);
		Mockito.verify(enemyTarget, atLeastOnce()).takeDamage(anyInt());
		verify(enemyNotTarget, never()).takeDamage(anyInt());
	}

}
