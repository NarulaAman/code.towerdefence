package towerdefense.gui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.io.IOException;

import javax.swing.DefaultListSelectionModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import ca.concordia.soen6441.io.MapPersister;
import ca.concordia.soen6441.logic.Map;

/**
 * This Panel lists the available maps to play (if any)
 */
public class MapListPanel extends JPanel {
	
	
	public interface MapSelectionListener {
		void mapSelected(Map map);
	}
	
	private final MapPersister mapPersister;
	
	private final JScrollPane scrollPane = new JScrollPane();
	
	private final JList<String> mapList;
	
	final MapListModel mapListModel;
	
	MapSelectionListener mapSelectionListener = null;
	
	public MapListPanel(MapPersister mapPersister) {
		this.mapPersister = mapPersister;
		setLayout(new GridBagLayout());
		mapListModel = new MapListModel(mapPersister);
		mapListModel.readDirectory();
		mapList = new JList<String>(mapListModel);
		scrollPane.setViewportView(mapList);
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.fill = GridBagConstraints.BOTH;
		constraints.weightx = constraints.weighty = 1.0;
//		constraints.anchor = GridBagConstraints.EAST;
		add(scrollPane, constraints);
		
		DefaultListSelectionModel defaultListSelectionModel = new DefaultListSelectionModel();
		defaultListSelectionModel.setSelectionMode(DefaultListSelectionModel.SINGLE_SELECTION);
		defaultListSelectionModel.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (e.getValueIsAdjusting() == false) {
					int selectedIndex = e.getFirstIndex();
					if (selectedIndex >= 0) {
						String mapName = mapListModel.getElementAt(selectedIndex);
						try {
							Map map = getMapPersister().load(mapName);
							fireMapSelectedListeners(map);
						} catch (ClassNotFoundException | IOException e1) {
							throw new RuntimeException(e1);
						}
						
					}
				}
			}
		});
		mapList.setSelectionModel(defaultListSelectionModel);
	}
	
	
	
	void fireMapSelectedListeners(Map map) {
		if (mapSelectionListener != null) {
			mapSelectionListener.mapSelected(map);
		}
	}



	MapPersister getMapPersister() {
		return mapPersister;
	}



	public void setMapSelectionListerner(MapSelectionListener listener) {
		mapSelectionListener = listener;
		
	}


	
	
}
