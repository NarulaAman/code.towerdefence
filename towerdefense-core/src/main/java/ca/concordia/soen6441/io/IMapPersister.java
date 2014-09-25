package ca.concordia.soen6441.io;

import java.io.File;
import java.io.IOException;

import ca.concordia.soen6441.logic.Map;

public interface IMapPersister {

	public void save(Map map, File file) throws IOException;

	public Map load(File file) throws IOException,
			ClassNotFoundException;

}