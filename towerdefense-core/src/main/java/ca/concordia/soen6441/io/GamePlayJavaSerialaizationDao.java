package ca.concordia.soen6441.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import ca.concordia.soen6441.dao.GamePlayDao;
import ca.concordia.soen6441.logic.GameMap;
import ca.concordia.soen6441.logic.GamePlay;

/**
 * This class saves and loads the {@link GamePlay} to a file given the
 * GamePlay1 name.
 *
 */
public class GamePlayJavaSerialaizationDao implements GamePlayDao {
	
	/** The Constant GAMEPLAY_FILENAME_EXTENSION. */
	public static final String GAMEPLAY_FILENAME_EXTENSION = ".game";
	
	/** The Constant GAMEFILENAME_STRING_FORMAT. */
	public static final String GAMEFILENAME_STRING_FORMAT = "." + File.separator + "%s" + GAMEPLAY_FILENAME_EXTENSION;
	
	/**
	 * Save to file a {@link GamePlay} given a GamePlay name
	 * @param gamePlay {@link GamePlay} to be saved
	 */
	@Override
	public void save(GamePlay gamePlay) throws IOException {
		File file = new File(String.format(GAMEFILENAME_STRING_FORMAT, gamePlay.getName())); 
		FileOutputStream fileOutputStream = new FileOutputStream(file); 
		ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
		objectOutputStream.writeObject(gamePlay);
		objectOutputStream.close();
		fileOutputStream.close();
	}
	
	
	/**
	 * Load the {@link GamePlay} from the file.
	 * @param gamePlayName {@link GamePlay} to be loaded
	 */
	@Override
	public GamePlay load(String gamePlayName) throws IOException, ClassNotFoundException {
		File file = new File(String.format(GAMEFILENAME_STRING_FORMAT, gamePlayName));
		FileInputStream fileInputStream = new FileInputStream(file);
		ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
		GamePlay gamePlay = (GamePlay) objectInputStream.readObject();
		objectInputStream.close();
		fileInputStream.close();
		return gamePlay;
	}
	
	/**
	 * List the name of all the saved {@link GamePlay}.
	 * @return the list of all saved games names
	 */
	@Override
	public List<String> listAllNames() throws IOException {
		File directory = new File(".");
		List<String> fileList = new ArrayList<>();
 		for (File file : directory.listFiles())
		{
			if (file.isFile() && file.getName().endsWith(GAMEPLAY_FILENAME_EXTENSION)) {
				String filename = file.getName();
				String gamePlayName = filename.substring(0, filename.indexOf(GAMEPLAY_FILENAME_EXTENSION));
				fileList.add(gamePlayName);
			}
		}
		return fileList;
	}

}
