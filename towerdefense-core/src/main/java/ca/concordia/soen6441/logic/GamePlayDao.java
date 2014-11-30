package ca.concordia.soen6441.logic;

import java.io.IOException;
import java.util.List;



/**
 * The Interface GamePlayDao.
 */
public interface GamePlayDao {
	
	/**
	 * Save.
	 *
	 * @param gamePlay the game play
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void save(GamePlay gamePlay) throws IOException;
	
	
	/**
	 * Load.
	 *
	 * @param name the name
	 * @return the game play
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws ClassNotFoundException the class not found exception
	 */
	public GamePlay load(String name) throws IOException,
	ClassNotFoundException;
	
	
	/**
	 * List all names.
	 *
	 * @return the list
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public List<String> listAllNames() throws IOException;

}
