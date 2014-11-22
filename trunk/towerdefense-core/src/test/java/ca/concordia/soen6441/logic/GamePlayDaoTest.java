package ca.concordia.soen6441.logic;

import java.io.File;
import java.io.IOException;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

//import ca.concordia.soen6441.io.GamePlayJavaSerializationDao;
import ca.concordia.soen6441.logic.primitives.GridPosition;

/**
 * Test cases for the {@link GameMapDao}
 *
 */
@FixMethodOrder(MethodSorters.JVM)
public class GamePlayDaoTest {
	
	private static final String MAP_DATA_FILENAME = "testMapName";
	
	//private GamePlayJavaSerializationDao gameMapDao = new GameMapJavaSerializationDao();
	private static GameMap gameMap = new GameMap(32, 32);
	
}