package ca.concordia.soen6441.logic;

import java.io.IOException;
import java.util.List;

import ca.concordia.soen6441.logger.MapLogger;

/**
 * Interface for a Data Access Object for a {@link MapLogger}
 *
 */
public interface MapLoggerDao {

	/**
	 * Saves a {@link MapLogger} by its name
	 * @param mapLogger to be saved
	 * @throws IOException on error
	 */
	public void save(MapLogger mapLogger) throws IOException;

	/**
	 * Load the {@link MapLogger} by a name
	 * @param name of the MapLogger to be loaded
	 * @return the MapLogger loaded from file
	 * @throws IOException on error
	 * @throws ClassNotFoundException on error
	 */
	public MapLogger load(String name) throws IOException,
			ClassNotFoundException;


}