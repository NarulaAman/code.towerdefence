package ca.concordia.soen6441.logic;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

/**
 * Test cases for the {@link HighScores}
 *
 */
@FixMethodOrder(MethodSorters.JVM)
public class HighScoreTest {
	
	private static final String MAP_DATA_FILENAME = "testHighScore";	
	
	private static HighScores highScore = new HighScores(MAP_DATA_FILENAME) ;

	/**
	 * Creates a {@link HighScores} to aid the tests
	 * @throws Exception on error
	 */
	@Before
	public void setUpBefore() throws Exception {		
		highScore.setName(MAP_DATA_FILENAME);
		highScore.addHighScore(10);
		highScore.addHighScore(20);
		highScore.addHighScore(30);
		highScore.addHighScore(40);
		highScore.addHighScore(50);
		
	}
	
	/**
	 * Create new {@link HighScores} object after execution of every method
	 * @throws Exception an error
	 */
	@After 
	public void setUpAfter() throws Exception {
		highScore = new HighScores(MAP_DATA_FILENAME);	
		
	}
	
	/**
	 * Test if score is added to {@link HighScores}	 
	 */
	@Test
	public final void testAdd() {
		HighScores highScore2=new HighScores(MAP_DATA_FILENAME);
		highScore2.addHighScore(60);
		Assert.assertTrue(highScore2.getScoreList().size()==1);
	}
	
	/**
	 * Test if two objects of {@link HighScores} are equal
	 */
	@Test
	public final void testEqual() {
		HighScores highScore2=new HighScores(MAP_DATA_FILENAME);
		highScore2.addHighScore(10);
		highScore2.addHighScore(20);
		highScore2.addHighScore(30);
		highScore2.addHighScore(40);
		highScore2.addHighScore(50);
		Assert.assertTrue(highScore2.equals(highScore));
	}
	
	/**
	 * Test if the sixth element is added to {@link HighScores}
	 */
	@Test
	public final void testAddScore() {
		highScore.addHighScore(60);
		Assert.assertTrue(highScore.getScoreList().size()==5);
	}
	
	/**
	 * Test if the greatest value is added to {@link HighScores}
	 */
	@Test
	public final void testHighScoreOrder() {
		highScore.addHighScore(60);
		Assert.assertTrue(highScore.getScoreList().get(0)==60);
	}
	
	/**
	 * Test if the lowest value is added to {@link HighScores}
	 */
	@Test
	public final void testLowScoreOrder() {
		highScore.addHighScore(5);		
		Assert.assertTrue(highScore.getScoreList().get(4)==10);
	}
	
	/**
	 * Test if the mid value is added to {@link HighScores}
	 */
	@Test
	public final void testMidScoreOrder() {
		highScore.addHighScore(35);		
		Assert.assertTrue(highScore.getScoreList().get(2)==35);
	}
	
	/**
	 * Test if values are arranged in ascending order in  {@link HighScores}
	 */
	@Test
	public final void testcheckOrder() {
		for(int i=0;i<highScore.getScoreList().size()-1;i++)
		{
		Assert.assertTrue(highScore.getScoreList().get(i)>highScore.getScoreList().get(i+1));
		}
	}

}
