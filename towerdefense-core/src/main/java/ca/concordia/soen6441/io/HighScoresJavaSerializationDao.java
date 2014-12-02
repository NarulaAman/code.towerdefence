package ca.concordia.soen6441.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import ca.concordia.soen6441.dao.HighScoresDao;
import ca.concordia.soen6441.logic.HighScores;

/**
 * This class saves and loads the {@link HighScores} to a file given the
 * map name.
 *
 */
public class HighScoresJavaSerializationDao implements HighScoresDao {
	
	/**
	 * This is the extension used in the filename
	 */
	public static final String MAP_FILENAME_EXTENSION = ".highscores";
	
	/**
	 * This is the name of the file
	 */
	public static final String FILENAME_STRING_FORMAT = "." + File.separator + "%s" + MAP_FILENAME_EXTENSION;
	

	/**
	 * Save to file a {@link HighScores} given a map name
	 * @param gameMap {@link HighScores} to be saved
	 */
	@Override
	public void save(HighScores highScores) { 
		try {
			File file = new File(String.format(FILENAME_STRING_FORMAT, highScores.getName())); 
			FileOutputStream fileOutputStream = new FileOutputStream(file); 
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
			objectOutputStream.writeObject(highScores);
			objectOutputStream.close();
			fileOutputStream.close();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		
	}
	
	/**
	 * Load the {@link highScores} from the file.
	 * @param mapName {@link highScores} to be loaded
	 */
	@Override
	public HighScores load(String mapName) {
		try {
			File file = new File(String.format(FILENAME_STRING_FORMAT, mapName));
			FileInputStream fileInputStream = new FileInputStream(file);
			ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
			HighScores highScores = (HighScores) objectInputStream.readObject();
			objectInputStream.close();
			fileInputStream.close();
			return highScores;
		} catch (FileNotFoundException ex) {
			return new HighScores(mapName);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
