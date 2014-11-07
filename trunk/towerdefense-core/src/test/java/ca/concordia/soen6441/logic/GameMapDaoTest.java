package ca.concordia.soen6441.logic;

import java.io.File;
import java.io.IOException;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import ca.concordia.soen6441.io.GameMapJavaSerializationDao;
import ca.concordia.soen6441.logic.primitives.GridPosition;

/**
 * Test cases for the {@link GameMapDao}
 *
 */
@FixMethodOrder(MethodSorters.JVM)
public class GameMapDaoTest {
	
	private static final String MAP_DATA_FILENAME = "testMapName";
	
	private GameMapJavaSerializationDao gameMapDao = new GameMapJavaSerializationDao();
	private static GameMap gameMap = new GameMap(32, 32);

	/**
	 * Creates a {@link GameMap} to aid the tests
	 * @throws Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		gameMap.setTile(new GridPosition(1, 2), Tile.ENEMY_PATH);
		gameMap.setTile(new GridPosition(5, 6), Tile.ENEMY_PATH);
		gameMap.setTile(new GridPosition(2, 27), Tile.ENEMY_PATH);
		gameMap.setTile(new GridPosition(5, 8), Tile.ENEMY_PATH);
		gameMap.setTile(new GridPosition(3, 13), Tile.ENEMY_PATH);
		gameMap.setTile(new GridPosition(15, 26), Tile.ENEMY_PATH);
		
	}

	/**
	 * Delete the file created during the tests
	 * @throws Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		new File(MAP_DATA_FILENAME).delete();
	}	
	
	/**
	 * Tests if an error occurs when opening a non-existant file
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	@Test(expected=IOException.class)
	public final void testLoadWithNoExistantFile() throws ClassNotFoundException, IOException {
		GameMap map2 = gameMapDao.load(MAP_DATA_FILENAME + "non-existant");
		Assert.assertTrue(map2.equals(gameMap));
	}

	/**
	 * Tests if the save happens without errors
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	@Test
	public final void testSave() throws IOException, ClassNotFoundException {
		gameMapDao.save(gameMap);
	}

	/**
	 * Tests if the load method loaded the same method it saved before
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	@Test
	public final void testLoad() throws ClassNotFoundException, IOException {
		GameMap map2 = gameMapDao.load(MAP_DATA_FILENAME);
		Assert.assertTrue(map2.equals(gameMap));
	}

}
