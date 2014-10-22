package towerdefense.gui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractListModel;

import ca.concordia.soen6441.io.MapDao;

public class MapListModel extends AbstractListModel<String> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4411498947924101211L;

	private final List<String> fileList = new ArrayList<String>();
	
	private final MapDao mapPersister;
	
	public MapListModel(MapDao mapPersister) {
		this.mapPersister = mapPersister;
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
			fileList.addAll(mapPersister.listAllNames());
		} catch (IOException e) {
			fileList.add("ERROR loading existing maps");
		}
		fireContentsChanged(this, 0, getSize());
	}
	
}
