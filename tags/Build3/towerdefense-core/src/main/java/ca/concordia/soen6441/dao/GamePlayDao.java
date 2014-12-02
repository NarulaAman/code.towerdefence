package ca.concordia.soen6441.dao;

import java.io.IOException;
import java.util.List;

import ca.concordia.soen6441.logic.GamePlay;



/**
 * The Interface GamePlayDao.
 */
public interface GamePlayDao {
	
	/**
	 * Save the game play.
	 *
	 * @param gamePlay the game play
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void save(GamePlay gamePlay) throws IOException;
	
	
	/**
	 * Load the game play.
	 *
	 * @param name the name
	 * @return the game play
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws ClassNotFoundException the class not found exception
	 */
	public GamePlay load(String name) throws IOException,
	ClassNotFoundException;
	
	
	/**
	 * List all names of the saved gameplay.
	 *
	 * @return the list
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public List<String> listAllNames() throws IOException;

}
