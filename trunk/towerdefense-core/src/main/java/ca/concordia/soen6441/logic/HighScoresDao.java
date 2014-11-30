package ca.concordia.soen6441.logic;

import java.io.IOException;
import java.util.List;

/**
 * Interface for a Data Access Object for a {@link HighScores}
 *
 */
public interface HighScoresDao {

	/**
	 * Saves a {@link HighScores} by map name
	 * @param highScores to be saved
	 * @throws IOException on error
	 */
	public void save(HighScores highScores);

	/**
	 * Load the {@link HighScores} by a name
	 * @param name of the highScores to be loaded
	 * @return the highScores loaded from file
	 * @throws IOException on error
	 * @throws ClassNotFoundException on error
	 */
	public HighScores load(String name);	
	
	

}