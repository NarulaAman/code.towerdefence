package ca.concordia.soen6441.dao;

import ca.concordia.soen6441.logic.HighScores;


/**
 * Interface for a Data Access Object for a {@link HighScores}
 *
 */
public interface HighScoresDao {

	/**
	 * Saves a {@link HighScores} by map name.
	 *
	 * @param highScores to be saved
	 */
	public void save(HighScores highScores);

	/**
	 * Load the {@link HighScores} by a name.
	 *
	 * @param name of the highScores to be loaded
	 * @return the highScores loaded from file
	 */
	public HighScores load(String name);	
	
	

}