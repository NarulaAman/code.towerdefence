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
import ca.concordia.soen6441.io.HighScoresJavaSerializationDao;
import ca.concordia.soen6441.logic.primitives.GridPosition;

/**
 * Test cases for the {@link HighScoresJavaSerializationDao}
 *
 */
@FixMethodOrder(MethodSorters.JVM)
public class HighScoresDaoTest {
	
	private static final String MAP_DATA_FILENAME = "testHighScore";
	
	private HighScoresJavaSerializationDao highScoreDao = new HighScoresJavaSerializationDao();
	private static HighScores highScore = new HighScores();

	/**
	 * Creates a {@link HighScores} to aid the tests
	 * @throws Exception on error
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		highScore.setName(MAP_DATA_FILENAME);
		highScore.addHighScore(10);
		highScore.addHighScore(20);
		highScore.addHighScore(30);
		highScore.addHighScore(40);
		highScore.addHighScore(50);
		
	}

	/**
	 * Delete the file created during the tests
	 * @throws Exception on error
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		new File(MAP_DATA_FILENAME).delete();
	}	
	
	/**
	 * Tests if an error occurs when opening a non-existant file
	 * @throws ClassNotFoundException on error
	 * @throws IOException on error
	 */
	@Test(expected=IOException.class)
	public final void testLoadWithNoExistantFile() throws ClassNotFoundException, IOException {
		HighScores highScore2 = highScoreDao.load(MAP_DATA_FILENAME + "non-existant");
		Assert.assertTrue(highScore2.equals(highScore));
	}

	/**
	 * Tests if the save happens without errors
	 * @throws IOException on error
	 * @throws ClassNotFoundException on error
	 */
	@Test
	public final void testSave() throws IOException, ClassNotFoundException {
		highScoreDao.save(highScore);
	}

	/**
	 * Tests if the load method loaded the same method it saved before
	 * @throws ClassNotFoundException on error
	 * @throws IOException on error
	 */
	@Test
	public final void testLoad() throws ClassNotFoundException, IOException {
		HighScores highScore2 = highScoreDao.load(MAP_DATA_FILENAME);		
		Assert.assertTrue(highScore2.equals(highScore));
	}

}
