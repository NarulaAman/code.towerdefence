package ca.concordia.soen6441.logic;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import ca.concordia.soen6441.logic.primitives.GridPosition;

public class MapValidatorTest {


	private MapValidator mapValidator;
	
	private GameMap gameMap;


	@Before
	public void setUp() throws Exception {
		mapValidator = new MapValidator();
		gameMap = new GameMap(7, 7);
	}


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

	@Test
	public void testDistanceBtwnCoordinates() {
		gameMap.setStartGridPosition(new GridPosition(0, 0));
		gameMap.setEndGridPosition(new GridPosition(0, 2));
		double distance = mapValidator.distanceBtwnCoordinates(gameMap.getStartGridPosition(), gameMap.getEndGridPosition());
		assertTrue(distance > (2 - 0.1));
		assertTrue(distance < (2 + 0.1));
	}

	@Test
	public void testCoordinateIsNotInTheSides() {
		assertTrue(mapValidator.coordinateOnTheEdge(gameMap, new GridPosition(0, 0)));
		assertTrue(mapValidator.coordinateOnTheEdge(gameMap, new GridPosition(gameMap.getWidth()-1, gameMap.getHeight() - 1)));
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
		assertFalse(mapValidator.mapMustHaveStart(gameMap));
	}
	
	@Test
	public void testMapHasNoStartWithAStart() {
		gameMap.setStartGridPosition(new GridPosition(0, 0));
		assertTrue(mapValidator.mapMustHaveStart(gameMap));
	}

	@Test
	public void testMapHasNoEnd() {
		assertFalse(mapValidator.mapMustHaveEnd(gameMap));
	}
	
	@Test
	public void testMapHasNoEndWithAnEnd() {
		gameMap.setEndGridPosition(new GridPosition(0, 0));
		assertTrue(mapValidator.mapMustHaveEnd(gameMap));
	}
	
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

}
