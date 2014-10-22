package towerdefense.gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListSelectionModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import ca.concordia.soen6441.io.GameMapDao;
import ca.concordia.soen6441.logic.GameMap;

/**
 * This Panel lists the available maps to play (if any)
 */
public class MapListPanel extends JPanel {
	
	
	public interface MapSelectionListener {
		void mapSelected(GameMap gameMap);
	}
	
	private final GameMapDao gameMapDao;
	
	private final JScrollPane scrollPane = new JScrollPane();
	
	private final JList<String> mapList;
	
	private final MapListModel mapListModel;
	
	private final List<MapSelectionListener> mapSelectionListenerList = new ArrayList<>();
	
	public MapListPanel(GameMapDao gameMapDao) {
		this.gameMapDao = gameMapDao;
		setLayout(new GridBagLayout());
		mapListModel = new MapListModel(gameMapDao);
		mapListModel.refreshPersistedMapList();
		mapList = new JList<String>(mapListModel);
		scrollPane.setViewportView(mapList);
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.fill = GridBagConstraints.BOTH;
		constraints.weightx = constraints.weighty = 1.0;
		add(scrollPane, constraints);
		
		final DefaultListSelectionModel selectionModel = new DefaultListSelectionModel();
		selectionModel.setSelectionMode(DefaultListSelectionModel.SINGLE_SELECTION);
		selectionModel.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (e.getValueIsAdjusting() == false) {
					int selectedIndex = selectionModel.isSelectedIndex(e.getFirstIndex()) ? e.getFirstIndex() : e.getLastIndex();
					if (selectedIndex >= 0) {
						String mapName = mapListModel.getElementAt(selectedIndex);
						try {
							GameMap gameMap = getgameMapDao().load(mapName);
							fireMapSelectedListeners(gameMap);
						} catch (ClassNotFoundException | IOException e1) {
							throw new RuntimeException(e1);
						}
						
					}
				}
			}
		});
		mapList.setSelectionModel(selectionModel);
	}
	
	
	
	void fireMapSelectedListeners(GameMap gameMap) {
		for (MapSelectionListener mapSelectionListener : mapSelectionListenerList) {
			mapSelectionListener.mapSelected(gameMap);
		}
	}



	GameMapDao getgameMapDao() {
		return gameMapDao;
	}



	public void addMapSelectionListerner(MapSelectionListener listener) {
		mapSelectionListenerList.add(listener);
		
	}


	
	
}
