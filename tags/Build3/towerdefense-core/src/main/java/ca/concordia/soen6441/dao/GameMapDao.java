package ca.concordia.soen6441.dao;

import java.io.IOException;
import java.util.List;

import ca.concordia.soen6441.logic.GameMap;

/**
 * Interface for a Data Access Object for a {@link GameMap}
 *
 */
public interface GameMapDao {

	/**
	 * Saves a {@link GameMap} by its name
	 * @param gameMap to be saved
	 * @throws IOException on error
	 */
	public void save(GameMap gameMap) throws IOException;

	/**
	 * Load the {@link GameMap} by a name
	 * @param name of the gameMap to be loaded
	 * @return the gameMap loaded from file
	 * @throws IOException on error
	 * @throws ClassNotFoundException on error
	 */
	public GameMap load(String name) throws IOException,
			ClassNotFoundException;
	
	
	/**
	 * List the name of all the saved {@link GameMap}
	 * @return The list of saved maps
	 * @throws IOException on error
	 */
	public List<String> listAllNames() throws IOException;

}