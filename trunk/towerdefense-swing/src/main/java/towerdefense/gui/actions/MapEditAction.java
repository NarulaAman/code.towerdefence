package towerdefense.gui.actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Icon;
import javax.swing.ImageIcon;

import towerdefense.gui.MapEditionDialog;
import towerdefense.gui.MapListPanel.MapSelectionListener;
import ca.concordia.soen6441.logic.GameMap;

public class MapEditAction extends AbstractAction implements MapSelectionListener {
	
	/**
	 * 
	 */
	private static final Icon EDIT_ICON = new ImageIcon(Object.class.getResource("/icons/edit.png"));
	
	private static final long serialVersionUID = 7403090617352119267L;
	private final MapEditionDialog mapEditionDialog;
	private GameMap selectedMap = null;
	
	public MapEditAction(MapEditionDialog mapEditionDialog) {
		super(null, EDIT_ICON);
		this.mapEditionDialog = mapEditionDialog;
		setEnabled(false);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		mapEditionDialog.setMap(selectedMap);
		mapEditionDialog.setVisible(true);
	}

	@Override
	public void mapSelected(GameMap gameMap) {
		selectedMap = gameMap;
		setEnabled(selectedMap != null);
		
	}

}
