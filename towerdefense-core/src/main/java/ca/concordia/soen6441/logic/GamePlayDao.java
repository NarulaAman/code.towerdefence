package ca.concordia.soen6441.logic;

import java.io.IOException;
import java.util.List;


public interface GamePlayDao {
	
	public void save(GamePlay gamePlay) throws IOException;
	
	
	public GameMap load(String name) throws IOException,
	ClassNotFoundException;
	
	
	public List<String> listAllNames() throws IOException;

}
