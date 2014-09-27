package ca.concordia.soen6441.logic;



import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import ca.concordia.soen6441.logic.primitives.Coordinate;
import ca.concordia.soen6441.logic.primitives.Damage;

public class TestEnemy {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public final void testHitWith() {
		Enemy enemy = new Enemy(null, new Coordinate(0, 0), 100);
		enemy.hitWith(new Damage(20));
		Assert.assertTrue(enemy.hitPoints == 80);
	}

	@Test
	public final void testIsAlive() {
		Enemy enemy = new Enemy(null, new Coordinate(0, 0), 100);
		enemy.hitWith(new Damage(20));
		enemy.hitWith(new Damage(20));
		enemy.hitWith(new Damage(20));
		enemy.hitWith(new Damage(20));
		enemy.hitWith(new Damage(20));
		Assert.assertFalse(enemy.isAlive());
		
	}

}
