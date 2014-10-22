package towerdefense.gui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractListModel;

import ca.concordia.soen6441.io.GameMapDao;
import ca.concordia.soen6441.logic.GameMap;

/**
 * This is a model to be used by the {@link MapListPanel}, to access the {@link GameMap}s already saved 
 *
 */
public class MapListModel extends AbstractListModel<String> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4411498947924101211L;

	private final List<String> fileList = new ArrayList<String>();
	
	private final GameMapDao gameMapDao;
	
	/**
	 * Creates a {@link MapListModel} with a given {@link GameMapDao}
	 * @param gameMapDao {@link GameMapDao} used to access the saved {@link GameMap}s
	 */
	public MapListModel(GameMapDao gameMapDao) {
		this.gameMapDao = gameMapDao;
	}
	
	
	/* (non-Javadoc)
	 * @see javax.swing.ListModel#getSize()
	 */
	@Override
	public int getSize() {
		return fileList.size();
	}

	/* (non-Javadoc)
	 * @see javax.swing.ListModel#getElementAt(int)
	 */
	@Override
	public String getElementAt(int index) {
		return fileList.get(index);
	}

	/**
	 * Refreshes the persisted {@link GameMap} list
	 */
	public void refreshPersistedMapList() {
		fileList.clear();
		try {
			fileList.addAll(gameMapDao.listAllNames());
		} catch (IOException e) {
			fileList.add("ERROR loading existing maps");
		}
		fireContentsChanged(this, 0, getSize());
	}
	
}
