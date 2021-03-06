package ca.concordia.soen6441.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import ca.concordia.soen6441.dao.GameMapDao;
import ca.concordia.soen6441.logic.GameMap;

/**
 * This class saves and loads the {@link GameMap} to a file given the
 * map name.
 *
 */
public class GameMapJavaSerializationDao implements GameMapDao {
	
	/**
	 * This is the extension used in the filename
	 */
	public static final String MAP_FILENAME_EXTENSION = ".map";
	
	/**
	 * This is the name of the file
	 */
	public static final String FILENAME_STRING_FORMAT = "." + File.separator + "%s" + MAP_FILENAME_EXTENSION;
	

	/**
	 * Save to file a {@link GameMap} given a map name
	 * @param gameMap {@link GameMap} to be saved
	 */
	@Override
	public void save(GameMap gameMap) throws IOException { 
		File file = new File(String.format(FILENAME_STRING_FORMAT, gameMap.getName())); 
		FileOutputStream fileOutputStream = new FileOutputStream(file); 
		ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
		objectOutputStream.writeObject(gameMap);
		objectOutputStream.close();
		fileOutputStream.close();
		
	}
	
	/**
	 * Load the {@link GameMap} from the file.
	 * @param mapName {@link GameMap} to be loaded
	 */
	@Override
	public GameMap load(String mapName) throws IOException, ClassNotFoundException {
		File file = new File(String.format(FILENAME_STRING_FORMAT, mapName));
		FileInputStream fileInputStream = new FileInputStream(file);
		ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
		GameMap gameMap = (GameMap) objectInputStream.readObject();
		objectInputStream.close();
		fileInputStream.close();
		return gameMap;
	}

	/**
	 * List the name of all the saved {@link GameMap}.
	 * @return the list of all map names
	 */
	@Override
	public List<String> listAllNames() throws IOException {
		File directory = new File(".");
		List<String> fileList = new ArrayList<>();
 		for (File file : directory.listFiles())
		{
			if (file.isFile() && file.getName().endsWith(MAP_FILENAME_EXTENSION)) {
				String filename = file.getName();
				String mapName = filename.substring(0, filename.indexOf(MAP_FILENAME_EXTENSION));
				fileList.add(mapName);
			}
		}
		return fileList;
	}
	
}
