package ca.concordia.soen6441.io;

import java.io.File;
import java.io.IOException;
import java.util.List;

import ca.concordia.soen6441.logic.Map;

public interface MapPersister {

	public void save(Map map, String name) throws IOException;

	public Map load(String name) throws IOException,
			ClassNotFoundException;
	
	public List<String> listAllNames() throws IOException;

}