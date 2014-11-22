package ca.concordia.soen6441.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import ca.concordia.soen6441.logic.GamePlay;
import ca.concordia.soen6441.logic.GamePlayDao;

public class GamePlayJavaSerialaizationDao implements GamePlayDao {
	
	public static final String GamePlay_FILENAME_EXTENSION = ".game";
	
	public static final String FILENAME_STRING_FORMAT = "." + File.separator + "%s" + GamePlay_FILENAME_EXTENSION;
	
	@Override
	public void save(GamePlay gamePlay) throws IOException {
		File file = new File(String.format(FILENAME_STRING_FORMAT, gamePlay.getName())); 
		FileOutputStream fileOutputStream = new FileOutputStream(file); 
		ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
		objectOutputStream.writeObject(gamePlay);
		objectOutputStream.close();
		fileOutputStream.close();
	}
	
	
	@Override
	public GamePlay load(String gamePlayName) throws IOException, ClassNotFoundException {
		File file = new File(String.format(FILENAME_STRING_FORMAT, gamePlayName));
		FileInputStream fileInputStream = new FileInputStream(file);
		ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
		GamePlay gamePlay = (GamePlay) objectInputStream.readObject();
		objectInputStream.close();
		fileInputStream.close();
		return gamePlay;
	}
	
	@Override
	public List<String> listAllNames() throws IOException {
		File directory = new File(".");
		List<String> fileList = new ArrayList<>();
 		for (File file : directory.listFiles())
		{
			if (file.isFile() && file.getName().endsWith(GamePlay_FILENAME_EXTENSION)) {
				String filename = file.getName();
				String gamePlayName = filename.substring(0, filename.indexOf(GamePlay_FILENAME_EXTENSION));
				fileList.add(gamePlayName);
			}
		}
		return fileList;
	}

}
