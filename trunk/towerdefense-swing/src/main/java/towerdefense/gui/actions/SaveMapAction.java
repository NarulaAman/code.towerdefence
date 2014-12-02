package towerdefense.gui.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.inject.Inject;

import towerdefense.gui.MapEditionDialog;
import towerdefense.gui.StartGameDialog;
import ca.concordia.soen6441.dao.MapLoggerDao;
import ca.concordia.soen6441.logger.MapLogger;
import ca.concordia.soen6441.logic.GameMap;

import com.google.inject.Provider;

/**
 * Action to save a {@link GameMap} in the {@link MapEditionDialog}
 *
 */
public class SaveMapAction implements ActionListener{
	
	@Inject Provider<MapEditionDialog> mapEditionDialogProvider;
	@Inject Provider<StartGameDialog> startGameDialogProvider;
	@Inject MapLoggerDao mapLoggerDao;
	
	/**
	 * Performs the {@link SaveMapAction}
	 * @param e not used - ignored
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if(getMapEditionDialog().saveMap()) {
			getMapEditionDialog().setVisible(false);
			getStartGameDialog().refreshMaps();
			MapLogger mapLogger = mapLoggerDao.load(getMapEditionDialog().getGameMapName());
			if (mapLogger.getLogMessages().size() < 1) {
				mapLogger.log("Map created");
			}
			else {
				mapLogger.log("Map edited");
			}
			mapLoggerDao.save(mapLogger);
		}
	}


	private MapEditionDialog getMapEditionDialog() {
		return mapEditionDialogProvider.get();
	}


	private StartGameDialog getStartGameDialog() {
		return startGameDialogProvider.get();
	}
	
	
}
