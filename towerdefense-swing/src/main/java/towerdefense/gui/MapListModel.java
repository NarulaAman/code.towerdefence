package towerdefense.gui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractListModel;

import ca.concordia.soen6441.logic.GameMap;
import ca.concordia.soen6441.logic.GameMapDao;

/**
 * This is a model to be used by the {@link MapListPanel}, to access the {@link GameMap}s already saved 
 *
 */
public class MapListModel extends AbstractListModel<String> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4411498947924101211L;

	private final List<String> mapNameList = new ArrayList<String>();
	
	private final GameMapDao gameMapDao;
	
	/**
	 * Creates a {@link MapListModel} with a given {@link GameMapDao}
	 * @param gameMapDao {@link GameMapDao} used to access the saved {@link GameMap}s
	 */
	public MapListModel(GameMapDao gameMapDao) {
		this.gameMapDao = gameMapDao;
	}
	
	
	/**
	 * Method returns how many maps are saved in memory
	 * @return number of maps
	 */
	@Override
	public int getSize() {
		return mapNameList.size();
	}

	/**
	 * returns map name by the index
	 * @param index of the map
	 * @return map name
	 */
	@Override
	public String getElementAt(int index) {
		return mapNameList.get(index);
	}

	/**
	 * Refreshes the persisted {@link GameMap} list
	 */
	public void refreshPersistedMapList() {
		mapNameList.clear();
		try {
			mapNameList.addAll(gameMapDao.listAllNames());
		} catch (IOException e) {
			mapNameList.add("ERROR loading existing maps");
		}
		fireContentsChanged(this, 0, getSize());
	}
	
}
