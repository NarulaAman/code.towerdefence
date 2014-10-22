package ca.concordia.soen6441.io;

import java.io.IOException;
import java.util.List;

import ca.concordia.soen6441.logic.GameMap;

/**
 * Interface used to persist and load a gameMap
 *
 */
public interface GameMapDao {

	/**
	 * Saves a gameMap by its name
	 * @param gameMap to be saved
	 * @param name to save the gameMap under
	 * @throws IOException
	 */
	public void save(GameMap gameMap, String name) throws IOException;

	/**
	 * Load the gameMap by a name
	 * @param name of the gameMap to be loaded
	 * @return the gameMap loaded from file
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public GameMap load(String name) throws IOException,
			ClassNotFoundException;
	
	public List<String> listAllNames() throws IOException;

}