package ca.concordia.soen6441.logic;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

import java.io.File;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import ca.concordia.soen6441.logic.primitives.GridPosition;

public class MapValidatorTest {
	
	private static final String MAP_DATA_FILENAME = "testMapName";
	
	static GameMap gameMap = new GameMap(32, 32);

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		new File(MAP_DATA_FILENAME).delete();		
		gameMap.set(Tile.ENEMY_PATH, new GridPosition(0, 0));
		gameMap.set(Tile.ENEMY_PATH, new GridPosition(2, 4));
		gameMap.set(Tile.ENEMY_PATH, new GridPosition(4, 27));
		gameMap.set(Tile.ENEMY_PATH, new GridPosition(5, 8));
		gameMap.set(Tile.ENEMY_PATH, new GridPosition(7, 14));
		gameMap.set(Tile.ENEMY_PATH, new GridPosition(12, 26));
		gameMap.set(Tile.ENEMY_PATH, new GridPosition(32, 32));
		
	}
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		new File(MAP_DATA_FILENAME).delete();
	}	

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
		
	}

	@Test
	public void testIsValid() {
		fail("Not yet implemented");
	}

	@Test
	public void testDistanceBtwnCoordinates() {
		fail("Not yet implemented");
	}

	@Test
	public void testCoordinateIsNotInTheSides() {
		fail("Not yet implemented");
	}

	@Test
	public void testMapStartPositionCount() {
		fail("Not yet implemented");
	}

	@Test
	public void testMapHasNoStart() {
		fail("Not yet implemented");
	}

	@Test
	public void testMapHasNoEnd() {
		fail("Not yet implemented");
	}

}
