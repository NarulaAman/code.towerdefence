package ca.concordia.soen6441.logic;


import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

import org.junit.Test;

import ca.concordia.soen6441.io.GameMapJavaSerializationDao;
import ca.concordia.soen6441.io.GamePlayJavaSerialaizationDao;
import ca.concordia.soen6441.logic.primitives.GridPosition;

public class GamePlayDaoTest {
	

	private static final String GAMEPLAY_DATA_FILENAME = "testMapName";
	
	private GamePlayJavaSerialaizationDao gamePlayDao = new GamePlayJavaSerialaizationDao();
	//private GameMapJavaSerializationDao gameMapDao = new GameMapJavaSerializationDao();
	private static GameMap gameMap = new GameMap(32, 32);
	
	private static GamePlay gamePlay = new GamePlay(gameMap, 500);

	/**
	 * Creates a {@link GameMap} to aid the tests
	 * @throws Exception on error
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		gamePlay.setName(GAMEPLAY_DATA_FILENAME);
		gameMap.setTile(new GridPosition(1, 2), Tile.ENEMY_PATH);
		gameMap.setTile(new GridPosition(5, 6), Tile.ENEMY_PATH);
		gameMap.setTile(new GridPosition(2, 27), Tile.ENEMY_PATH);
		gameMap.setTile(new GridPosition(5, 8), Tile.ENEMY_PATH);
		gameMap.setTile(new GridPosition(3, 13), Tile.ENEMY_PATH);
		gameMap.setTile(new GridPosition(15, 26), Tile.ENEMY_PATH);
		
	}
	
	

	@Test
	public final void testSave() throws IOException, ClassNotFoundException {
		gamePlayDao.save(gamePlay);
	}

	@Test
	public void testLoad() {
		fail("Not yet implemented");
	}

	@Test
	public void testListAllNames() {
		fail("Not yet implemented");
	}

}
