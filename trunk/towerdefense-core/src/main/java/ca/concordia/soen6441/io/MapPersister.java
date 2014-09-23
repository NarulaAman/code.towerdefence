package ca.concordia.soen6441.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import ca.concordia.soen6441.logic.Map;

public class MapPersister implements IMapPersister {
	
	/* (non-Javadoc)
	 * @see ca.concordia.soen6441.io.IMapPersister#save(ca.concordia.soen6441.logic.Map, java.io.File)
	 */
	@Override
	public void save(Map map, File file) throws IOException {
		FileOutputStream outPut = new FileOutputStream(file);
		ObjectOutputStream oos = new ObjectOutputStream(outPut);
		oos.writeObject(map);
		oos.close();
	}
	
	/* (non-Javadoc)
	 * @see ca.concordia.soen6441.io.IMapPersister#load(java.io.File)
	 */
	@Override
	public Map load(File file) throws IOException, ClassNotFoundException {
		FileInputStream fis = new FileInputStream(file);
		ObjectInputStream ois = new ObjectInputStream(fis);
		Map map = (Map) ois.readObject();
		ois.close();
		return map;
	}
	
}
