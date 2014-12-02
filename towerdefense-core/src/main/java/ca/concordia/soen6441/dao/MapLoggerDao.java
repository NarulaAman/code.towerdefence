package ca.concordia.soen6441.dao;

import ca.concordia.soen6441.logger.MapLogger;

/**
 * Interface for a Data Access Object for a {@link MapLogger}
 *
 */
public interface MapLoggerDao {

	/**
	 * Saves a {@link MapLogger} by its name.
	 *
	 * @param mapLogger to be saved
	 */
	public void save(MapLogger mapLogger);

	/**
	 * Load the {@link MapLogger} by a name.
	 *
	 * @param name of the MapLogger to be loaded
	 * @return the MapLogger loaded from file
	 */
	public MapLogger load(String name);


}