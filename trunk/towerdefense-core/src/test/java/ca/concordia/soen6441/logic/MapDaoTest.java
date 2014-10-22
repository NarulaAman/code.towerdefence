package ca.concordia.soen6441.logic;

import java.io.File;
import java.io.IOException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import ca.concordia.soen6441.io.GameMapDao;
import ca.concordia.soen6441.io.GameMapJavaSerializationDao;
import ca.concordia.soen6441.logic.primitives.GridPosition;

/**
 * Test cases for the {@link GameMapDao}
 *
 */
@FixMethodOrder(MethodSorters.JVM)
public class MapDaoTest {
	
	private static final String MAP_DATA_FILENAME = "testMapName";
	
	GameMapJavaSerializationDao gameMapDao = new GameMapJavaSerializationDao();
	static GameMap gameMap = new GameMap(32, 32);

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		new File(MAP_DATA_FILENAME).delete();		
		gameMap.setTile(new GridPosition(1, 2), Tile.ENEMY_PATH);
		gameMap.setTile(new GridPosition(5, 6), Tile.ENEMY_PATH);
		gameMap.setTile(new GridPosition(2, 27), Tile.ENEMY_PATH);
		gameMap.setTile(new GridPosition(5, 8), Tile.ENEMY_PATH);
		gameMap.setTile(new GridPosition(3, 13), Tile.ENEMY_PATH);
		gameMap.setTile(new GridPosition(15, 26), Tile.ENEMY_PATH);
		
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
	
	@Test(expected=IOException.class)
	public final void testLoadWithNoExistantFile() throws ClassNotFoundException, IOException {
		GameMap map2 = gameMapDao.load(MAP_DATA_FILENAME);
		Assert.assertTrue(map2.equals(gameMap));
	}

	@Test
	public final void testSave() throws IOException, ClassNotFoundException {
		gameMapDao.save(gameMap, MAP_DATA_FILENAME);
	}

	@Test
	public final void testLoad() throws ClassNotFoundException, IOException {
		GameMap map2 = gameMapDao.load(MAP_DATA_FILENAME);
		Assert.assertTrue(map2.equals(gameMap));
	}

}
