package towerdefense.gui.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JOptionPane;

import towerdefense.gui.MapEditionDialog;
import towerdefense.gui.StartGameDialog;
import ca.concordia.soen6441.io.GameMapDao;
import ca.concordia.soen6441.logic.GameMap;
import ca.concordia.soen6441.logic.MapValidator;

/**
 * Action to save a {@link GameMap} in the {@link MapEditionDialog}
 *
 */
public class SaveMapAction implements ActionListener{
	
	private MapValidator mapValidator = new MapValidator();
	private MapEditionDialog mapEditionDialog;
	private GameMapDao gameMapDao;
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
		this.gameMapDao = mapEditionDialog.getMapDao() ;
	}
	

	/**
	 * Performs the {@link SaveMapAction}
	 * @param e not used - ignored
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
        try {
			StringBuilder incorrectMap = new StringBuilder();
			Boolean mapValidate = mapValidator.isValid(mapEditionDialog.getMap(), incorrectMap);

			if(mapValidate) {
				gameMapDao.save(mapEditionDialog.getMap(), mapEditionDialog.getMapName().getText());
			    mapEditionDialog.setVisible(false);
			    startGameDialog.refreshMaps();
			} else {
				JOptionPane.showMessageDialog(mapEditionDialog, incorrectMap, "Map validation error",
					    JOptionPane.ERROR_MESSAGE);
			}
		} catch (IOException exception) {
			exception.printStackTrace();
		}
	}
}
