package ca.concordia.soen6441.logic;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import ca.concordia.soen6441.logic.primitives.GridPosition;
import ca.concordia.soen6441.logic.tower.FireTower;
import ca.concordia.soen6441.logic.tower.Tower;

public class TowerTest {

	private final TowerFactory towerFactory = new TowerFactory();
	
	private Tower tower;
	private Enemy enemy;
	
	@Before
	public void setUp() {
		tower = towerFactory.towerOnCoordinate(FireTower.class, new GridPosition(1, 1));
		enemy = mock(Enemy.class);
	}
	
	
	@Test
	public void testShoot() {
 
		when(enemy.getHealth()).thenReturn(100);
		tower.shoot(enemy);
		Mockito.verify(enemy).setHealth(100-tower.getDamage());
	}

}
