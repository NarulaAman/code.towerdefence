package ca.concordia.soen6441.io;

import java.io.IOException;
import java.util.List;

import ca.concordia.soen6441.logic.Map;

/**
 * Interface used to persist and load a map
 *
 */
public interface MapPersister {

	/**
	 * Saves a map by its name
	 * @param map to be saved
	 * @param name to save the map under
	 * @throws IOException
	 */
	public void save(Map map, String name) throws IOException;

	/**
	 * Load the map by a name
	 * @param name of the map to be loaded
	 * @return the map loaded from file
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public Map load(String name) throws IOException,
			ClassNotFoundException;
	
	public List<String> listAllNames() throws IOException;

}