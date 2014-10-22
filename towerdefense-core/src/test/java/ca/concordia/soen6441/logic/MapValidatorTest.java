package ca.concordia.soen6441.logic;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import ca.concordia.soen6441.logic.primitives.GridPosition;

public class MapValidatorTest {
	
//	static GameMap gameMap = new GameMap(32, 32);

	private MapValidator mapValidator;
	
	private GameMap gameMap;
//	
//	@BeforeClass
//	public static void setUpBeforeClass() throws Exception {
//		gameMap.set(Tile.ENEMY_PATH, new GridPosition(0, 0));
//		gameMap.set(Tile.ENEMY_PATH, new GridPosition(2, 4));
//		gameMap.set(Tile.ENEMY_PATH, new GridPosition(4, 27));
//		gameMap.set(Tile.ENEMY_PATH, new GridPosition(5, 8));
//		gameMap.set(Tile.ENEMY_PATH, new GridPosition(7, 14));
//		gameMap.set(Tile.ENEMY_PATH, new GridPosition(12, 26));
//		gameMap.set(Tile.ENEMY_PATH, new GridPosition(32, 32));
//		
//	}


	@Before
	public void setUp() throws Exception {
		mapValidator = new MapValidator();
		gameMap = new GameMap(32, 32);
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
		
		assertFalse(mapValidator.coordinateOnTheEdge(gameMap, new GridPosition(0, 0)));
		assertFalse(mapValidator.coordinateOnTheEdge(gameMap, new GridPosition(1, 1)));
	}

	@Test
	public void testMapStartPositionCount() {
		assertEquals(0, mapValidator.mapStartPositionCount(gameMap));
		gameMap.setStartGridPosition(new GridPosition(0, 0));		
		assertEquals(1, mapValidator.mapStartPositionCount(gameMap));
		gameMap.setStartGridPosition(new GridPosition(0, 1));
		assertEquals(1, mapValidator.mapStartPositionCount(gameMap));
	}

	@Test
	public void testMapHasNoStart() {
		assertTrue(mapValidator.mapMustHaveStart(gameMap));
	}
	
	@Test
	public void testMapHasNoStartWithAStart() {
		gameMap.setStartGridPosition(new GridPosition(0, 0));
		assertFalse(mapValidator.mapMustHaveStart(gameMap));
	}

	@Test
	public void testMapHasNoEnd() {
		assertTrue(mapValidator.mapMustHaveEnd(gameMap));
	}
	
	@Test
	public void testMapHasNoEndWithAnEnd() {
		gameMap.setEndGridPosition(new GridPosition(0, 0));
		assertFalse(mapValidator.mapMustHaveEnd(gameMap));
	}

}
