package ca.concordia.soen6441.logic;


import static org.junit.Assert.fail;

import java.io.IOException;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import ca.concordia.soen6441.io.GamePlayJavaSerialaizationDao;
import ca.concordia.soen6441.logic.primitives.GridPosition;

/**
 * The Class GamePlayDaoTest.
 */
public class GamePlayDaoTest {
	

	/** The Constant GAMEPLAY_DATA_FILENAME. */
	private static final String GAMEPLAY_DATA_FILENAME = "testMapName";
	
	/** The game play dao. */
	private GamePlayJavaSerialaizationDao gamePlayDao = new GamePlayJavaSerialaizationDao();
	//private GameMapJavaSerializationDao gameMapDao = new GameMapJavaSerializationDao();
	/** The game map. */
	private static GameMap gameMap = new GameMap(32, 32);
	
	/** The game play. */
	private static GamePlay gamePlay = new GamePlay(gameMap, 500);

	/**
	 * Creates a {@link GameMap} to aid the tests.
	 *
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
	
	

	/**
	 * Test save.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws ClassNotFoundException the class not found exception
	 */
	@Test
	public final void testSave() throws IOException, ClassNotFoundException {
		gamePlayDao.save(gamePlay);
	}

	/**
	 * Test load.
	 *
	 * @throws ClassNotFoundException the class not found exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@Test
	public final void testLoad() throws ClassNotFoundException, IOException {
		GamePlay gamePlay2 = gamePlayDao.load(GAMEPLAY_DATA_FILENAME);
		Assert.assertTrue(gamePlay2.equals(gamePlay));
	}
	
	/**
	 * Test list all names.
	 */
	@Test
	public void testListAllNames() {
		fail("Not yet implemented");
	}

}
