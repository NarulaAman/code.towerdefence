package towerdefense.gui.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import towerdefense.gui.MapEditionDialog;
import towerdefense.gui.StartGameDialog;
import ca.concordia.soen6441.logic.GameMap;

/**
 * Action to save a {@link GameMap} in the {@link MapEditionDialog}
 *
 */
public class SaveMapAction implements ActionListener{
	
	private MapEditionDialog mapEditionDialog;
	private StartGameDialog startGameDialog;

	/**
	 * Constructs a {@link SaveMapAction} to save the {@link GameMap} on the {@link MapEditionDialog} and to refresh
	 * the given {@link StartGameDialog} when the save is successful
	 * @param mapEditionDialog {@link MapEditionDialog} to have the {@link GameMap} saved
	 * @param startGameDialog {@link StartGameDialog} to be refreshed when the save is done
	 */
	public SaveMapAction(MapEditionDialog mapEditionDialog, StartGameDialog startGameDialog) {
		this.mapEditionDialog = mapEditionDialog;
		this.startGameDialog = startGameDialog;
	}
	

	/**
	 * Performs the {@link SaveMapAction}
	 * @param e not used - ignored
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if(mapEditionDialog.saveMap()) {
		    mapEditionDialog.setVisible(false);
		    startGameDialog.refreshMaps();
		}
	}
}
