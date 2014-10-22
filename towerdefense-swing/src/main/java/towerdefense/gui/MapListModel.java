package towerdefense.gui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractListModel;

import ca.concordia.soen6441.io.GameMapDao;

public class MapListModel extends AbstractListModel<String> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4411498947924101211L;

	private final List<String> fileList = new ArrayList<String>();
	
	private final GameMapDao gameMapDao;
	
	public MapListModel(GameMapDao gameMapDao) {
		this.gameMapDao = gameMapDao;
	}
	
	
	@Override
	public int getSize() {
		return fileList.size();
	}

	@Override
	public String getElementAt(int index) {
		return fileList.get(index);
	}

	public void readDirectory() {
		fileList.clear();
		try {
			fileList.addAll(gameMapDao.listAllNames());
		} catch (IOException e) {
			fileList.add("ERROR loading existing maps");
		}
		fireContentsChanged(this, 0, getSize());
	}
	
}
