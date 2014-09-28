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

import ca.concordia.soen6441.io.MapJavaSerializationPersister;
import ca.concordia.soen6441.logic.Tile.TileType;
import ca.concordia.soen6441.logic.primitives.Coordinate;

@FixMethodOrder(MethodSorters.JVM)
public class TestMapPersister {
	
	private static final String MAP_DATA_FILENAME = "testMapFilename.data";
	
	MapJavaSerializationPersister mapPersister = new MapJavaSerializationPersister();
	static Map map = new Map(32, 32);

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		map.set(new Tile(TileType.ENEMY_PATH), new Coordinate(1, 2));
		map.set(new Tile(TileType.ENEMY_PATH), new Coordinate(5, 6));
		
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
		Map map2 = mapPersister.load(new File(MAP_DATA_FILENAME));
		Assert.assertTrue(map2.equals(map));
	}

	@Test
	public final void testSave() throws IOException, ClassNotFoundException {
		mapPersister.save(map, new File(MAP_DATA_FILENAME));
	}

	@Test
	public final void testLoad() throws ClassNotFoundException, IOException {
		Map map2 = mapPersister.load(new File(MAP_DATA_FILENAME));
		Assert.assertTrue(map2.equals(map));
	}

}
