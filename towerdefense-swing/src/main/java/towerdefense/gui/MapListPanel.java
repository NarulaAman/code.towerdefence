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

import ca.concordia.soen6441.logic.GameMap;
import ca.concordia.soen6441.logic.GameMapDao;

/**
 * This Panel has a list the available {@link GameMap}s to play (if any)
 */
public class MapListPanel extends JPanel {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6449208548018087189L;



	/**
	 * This is a Listener to be notified when a map is selected in the list of maps 
	 *
	 */
	public interface MapSelectionListener {
		void mapSelected(GameMap gameMap);
	}
	
	private final GameMapDao gameMapDao;
	
	private final JScrollPane scrollPane = new JScrollPane();
	
	private final JList<String> mapList;
	
	private final MapListModel mapListModel;
	
	private final List<MapSelectionListener> mapSelectionListenerList = new ArrayList<>();
	
	/**
	 * Creates a {@link MapListPanel} with a given {@link GameMapDao}
	 * @param gameMapDao {@link GameMapDao} to be used by this {@link MapListPanel}
	 */
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
							GameMap gameMap = getGameMapDao().load(mapName);
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
	
	/**
	 * Invoke the {@link MapSelectionListener}s for a selected {@link GameMap}
	 * @param gameMap {@link GameMap} that was selected
	 */
	private void fireMapSelectedListeners(GameMap gameMap) {
		for (MapSelectionListener mapSelectionListener : mapSelectionListenerList) {
			mapSelectionListener.mapSelected(gameMap);
		}
	}



	/**
	 * Returns the current {@link GameMapDao}
	 * @return the current {@link GameMapDao}
	 */
	private GameMapDao getGameMapDao() {
		return gameMapDao;
	}



	/**
	 * Adds a {@link MapSelectionListener} to be notified when a {@link GameMap} is selected
	 * @param listener {@link MapSelectionListener} to be notified when a {@link GameMap} is selected
	 */
	public void addMapSelectionListerner(MapSelectionListener listener) {
		mapSelectionListenerList.add(listener);
	}

	/**
	 * Refreshes the list of maps
	 */
	public void refreshList() {
		mapListModel.refreshPersistedMapList();
		repaint();
	}

	
	
}
