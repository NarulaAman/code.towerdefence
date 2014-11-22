package towerdefense.gui.actions;

import java.awt.event.ActionEvent;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.swing.AbstractAction;
import javax.swing.Icon;
import javax.swing.ImageIcon;

import towerdefense.gui.MapEditionDialog;
import towerdefense.gui.MapListPanel.MapSelectionListener;
import ca.concordia.soen6441.logic.GameMap;

/**
 * Action for the Edit Map button
 */
public class MapEditAction extends AbstractAction implements MapSelectionListener {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7403090617352119267L;
	
	private static final Icon EDIT_ICON = new ImageIcon(Object.class.getResource("/icons/edit.png"));
	

	
	@Inject
	private Provider<MapEditionDialog> mapEditionDialogProvider;
	private GameMap selectedMap = null;
	
	/**
	 * Creates a {@link MapEditAction} to edit maps with the given {@link MapEditionDialog}
	 * @param mapEditionDialog {@link MapEditionDialog} to work with this action
	 */
	public MapEditAction() {
		super(null, EDIT_ICON);
		setEnabled(false);
	}
	/**
	 * Performs the {@link MapEditAction}
	 * @param e not used - ignored
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (selectedMap != null) {
			getMapEditionDialog().setMap(selectedMap.clone());
			getMapEditionDialog().setVisible(true);
		}
	}

	/**
	 * Invoked when a {@link GameMap} is selected to be edited, can be null
	 * @param gameMap {@link GameMap} to be edited 
	 */
	@Override
	public void mapSelected(GameMap gameMap) {
		setEnabled(selectedMap != null);
		selectedMap = gameMap;
	}

	private MapEditionDialog getMapEditionDialog() {
		return mapEditionDialogProvider.get();
	}
}
