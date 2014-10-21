package towerdefense.gui.actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import towerdefense.gui.MapEditionDialog;
import towerdefense.gui.MapListPanel.MapSelectionListener;
import ca.concordia.soen6441.logic.Map;

public class MapEditAction extends AbstractAction implements MapSelectionListener {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7403090617352119267L;
	private final MapEditionDialog mapEditionDialog;
	private Map selectedMap = null;
	
	public MapEditAction(MapEditionDialog mapEditionDialog) {
		this.mapEditionDialog = mapEditionDialog;
		setEnabled(false);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		mapEditionDialog.setMap(selectedMap);
		mapEditionDialog.setVisible(true);
	}

	@Override
	public void mapSelected(Map map) {
		selectedMap = map;
		setEnabled(selectedMap != null);
		
	}

}
