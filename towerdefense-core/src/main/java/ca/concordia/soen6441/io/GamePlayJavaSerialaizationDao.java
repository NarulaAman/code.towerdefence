package ca.concordia.soen6441.io;

import ca.concordia.soen6441.logic.GameMapDao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import ca.concordia.soen6441.logic.GameMap;
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
	public GameMap load(String mapName) throws IOException, ClassNotFoundException {
		return null;
	}
	public List<String> listAllNames() throws IOException {
		return null;
	}

}
