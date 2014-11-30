package ca.concordia.soen6441.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import ca.concordia.soen6441.logger.MapLogger;
import ca.concordia.soen6441.logic.MapLoggerDao;

/**
 * This class saves and loads the {@link MapLogger} to a file given the
 * map name.
 *
 */
public class MapLoggerJavaSerializationDao implements MapLoggerDao {
	
	/**
	 * This is the extension used in the filename
	 */
	public static final String MAP_FILENAME_EXTENSION = ".log";
	
	/**
	 * This is the name of the file
	 */
	public static final String FILENAME_STRING_FORMAT = "." + File.separator + "%s" + MAP_FILENAME_EXTENSION;
	

	/**
	 * Save to file a {@link MapLogger} given a map name
	 * @param mapLogger {@link MapLogger} to be saved
	 */
	@Override
	public void save(MapLogger mapLogger) { 
		try{
			File file = new File(String.format(FILENAME_STRING_FORMAT, mapLogger.getName())); 
			FileOutputStream fileOutputStream = new FileOutputStream(file); 
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
			objectOutputStream.writeObject(mapLogger);
			objectOutputStream.close();
			fileOutputStream.close();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * Load the {@link MapLogger} from the file.
	 * @param mapName {@link MapLogger} to be loaded
	 */
	@Override
	public MapLogger load(String mapName) {
		try{
			File file = new File(String.format(FILENAME_STRING_FORMAT, mapName));
			FileInputStream fileInputStream = new FileInputStream(file);
			ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
			MapLogger MapLogger = (MapLogger) objectInputStream.readObject();
			objectInputStream.close();
			fileInputStream.close();
			return MapLogger;
		} catch (FileNotFoundException ex) {
			return new MapLogger(mapName);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
