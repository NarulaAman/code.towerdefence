package ca.concordia.soen6441.logic;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import ca.concordia.soen6441.logic.primitives.GridPosition;

/**
 * This class will do unit testing of {@link MapValidator}
 *
 */
public class MapValidatorTest {

	private MapValidator mapValidator;
	
	private GameMap gameMap;


	/**
	 * Setup pre-condition for all the test
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		mapValidator = new MapValidator();
		gameMap = new GameMap(7, 7);
	}


	/**
	 * Test a valid {@link GameMap}
	 */
	@Test
	public void testIsValid() {
		gameMap.setStartGridPosition(new GridPosition(0, 0));
		gameMap.setEndGridPosition(new GridPosition(6, 6));
		gameMap.setTile(new GridPosition(0, 1), Tile.ENEMY_PATH);
		gameMap.setTile(new GridPosition(1, 1), Tile.ENEMY_PATH);
		gameMap.setTile(new GridPosition(1, 2), Tile.ENEMY_PATH);
		gameMap.setTile(new GridPosition(2, 2), Tile.ENEMY_PATH);
		gameMap.setTile(new GridPosition(2, 3), Tile.ENEMY_PATH);
		gameMap.setTile(new GridPosition(3, 3), Tile.ENEMY_PATH);
		gameMap.setTile(new GridPosition(3, 4), Tile.ENEMY_PATH);
		gameMap.setTile(new GridPosition(4, 4), Tile.ENEMY_PATH);
		gameMap.setTile(new GridPosition(4, 5), Tile.ENEMY_PATH);
		gameMap.setTile(new GridPosition(5, 5), Tile.ENEMY_PATH);
		gameMap.setTile(new GridPosition(5, 6), Tile.ENEMY_PATH);
		
		StringBuilder stringBuilder = new StringBuilder();
		assertTrue(mapValidator.isValid(gameMap, stringBuilder));
//		mapValidator.isValid(gameMap, stringBuilder);
//		System.err.println(stringBuilder.toString());
	}

	/**
	 * Test if the gameMap start and end have distance between them 
	 */
	@Test
	public void testDistanceBtwnCoordinates() {
		gameMap.setStartGridPosition(new GridPosition(0, 0));
		gameMap.setEndGridPosition(new GridPosition(0, 2));
		double distance = mapValidator.distanceBtwnCoordinates(gameMap.getStartGridPosition(), gameMap.getEndGridPosition());
		assertTrue(distance > (2 - 0.1));
		assertTrue(distance < (2 + 0.1));
	}

	/**
	 * Test if the gameMap has start and end at edges
	 */
	@Test
	public void testCoordinateIsNotInTheSides() {
		assertTrue(mapValidator.coordinateOnTheEdge(gameMap, new GridPosition(0, 0)));
		assertTrue(mapValidator.coordinateOnTheEdge(gameMap, new GridPosition(gameMap.getWidth()-1, gameMap.getHeight() - 1)));
		assertFalse(mapValidator.coordinateOnTheEdge(gameMap, new GridPosition(1, 1)));
	}

	/**
	 * Test if the gameMap has one start
	 */
	@Test
	public void testMapStartPositionCount() {
		assertEquals(0, mapValidator.mapStartPositionCount(gameMap));
		gameMap.setStartGridPosition(new GridPosition(0, 0));		
		assertEquals(1, mapValidator.mapStartPositionCount(gameMap));
		gameMap.setStartGridPosition(new GridPosition(0, 1));
		assertEquals(1, mapValidator.mapStartPositionCount(gameMap));
	}

	/**
	 * Test if the gameMap has no start
	 */
	@Test
	public void testMapHasNoStart() {
		assertFalse(mapValidator.mapMustHaveStart(gameMap));
	}
	
	/**
	 * Test if the gameMap has a start
	 */
	@Test
	public void testMapHasNoStartWithAStart() {
		gameMap.setStartGridPosition(new GridPosition(0, 0));
		assertTrue(mapValidator.mapMustHaveStart(gameMap));
	}

	/**
	 * Test if the gameMap has no end
	 */
	@Test
	public void testMapHasNoEnd() {
		assertFalse(mapValidator.mapMustHaveEnd(gameMap));
	}
	
	/**
	 * Test if the gameMap has an end
	 */
	@Test
	public void testMapHasNoEndWithAnEnd() {
		gameMap.setEndGridPosition(new GridPosition(0, 0));
		assertTrue(mapValidator.mapMustHaveEnd(gameMap));
	}
	
	/**
	 * Test if the gameMap start and end are at the same position
	 */
	@Test
	public void testMapStartSamePositionAsEnd() {
		gameMap.setStartGridPosition(new GridPosition(0, 0));
		gameMap.setEndGridPosition(new GridPosition(0, 0));
		assertTrue(mapValidator.mapStartSamePositionAsEnd(gameMap));
	}
	
	/**
	 * Test if the gameMap start and end are at the different position
	 */
	@Test
	public void testMapStartDifferentPositionFromEnd() {
		gameMap.setStartGridPosition(new GridPosition(0, 0));
		gameMap.setEndGridPosition(new GridPosition(0, 0));
		assertTrue(mapValidator.mapStartSamePositionAsEnd(gameMap));
	}
	
	/**
	 * Test the correctness of path between start and end
	 */
	@Test
	public void testMapHStartReachesTheEnd() {
		gameMap.setStartGridPosition(new GridPosition(0, 0));
		gameMap.setEndGridPosition(new GridPosition(5, 5));
		assertFalse(mapValidator.mapStartMustReachEnd(gameMap));
		gameMap.setTile(new GridPosition(0, 1), Tile.ENEMY_PATH);
		gameMap.setTile(new GridPosition(1, 1), Tile.ENEMY_PATH);
		gameMap.setTile(new GridPosition(1, 2), Tile.ENEMY_PATH);
		gameMap.setTile(new GridPosition(2, 2), Tile.ENEMY_PATH);
		gameMap.setTile(new GridPosition(2, 3), Tile.ENEMY_PATH);
		gameMap.setTile(new GridPosition(3, 3), Tile.ENEMY_PATH);
		gameMap.setTile(new GridPosition(3, 4), Tile.ENEMY_PATH);
		gameMap.setTile(new GridPosition(4, 4), Tile.ENEMY_PATH);
		gameMap.setTile(new GridPosition(4, 5), Tile.ENEMY_PATH);
		assertTrue(mapValidator.mapStartMustReachEnd(gameMap));
	}
	
	/**
	 * Tests that the validator can detect maps without scenery tiles to place the tower
	 */
	@Test
	public void testMapDoesntHaveScenery() {
		for (int x = 0; x < gameMap.getWidth(); ++x) {
			for (int y = 0; y < gameMap.getHeight(); ++y) {
				gameMap.setTile(new GridPosition(x, y), Tile.ENEMY_PATH);
			}
		}
		GridPosition startPosition = new GridPosition(0, 0);
		gameMap.setTile(startPosition, Tile.SCENERY);
		gameMap.setStartGridPosition(startPosition);
		GridPosition endPosition = new GridPosition(gameMap.getWidth()-1, gameMap.getHeight()-1);
		gameMap.setTile(endPosition, Tile.SCENERY);
		gameMap.setEndGridPosition(endPosition);
		
		assertFalse(mapValidator.mapHasAtLeastOneSceneryTile(gameMap));
	}

}
