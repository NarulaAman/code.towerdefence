package towerdefense.gui.actions;

import java.awt.event.ActionEvent;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.swing.AbstractAction;
import javax.swing.Icon;
import javax.swing.ImageIcon;

import towerdefense.gui.MapListPanel;
import towerdefense.gui.log.MapLogDialog;
import ca.concordia.soen6441.dao.MapLoggerDao;
import ca.concordia.soen6441.logger.MapLogger;
import ca.concordia.soen6441.logic.GameMap;

/**
 * Action of the map log
 *
 */
public class MapLogAction extends AbstractAction implements MapListPanel.MapSelectionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private GameMap selectedMap = null;
	private MapLoggerDao mapLoggerDao;
	private static final Icon MAP_LOG_ICON = new ImageIcon(Object.class.getResource("/icons/maplogbutton.png"));
	
	@Inject Provider<MapLogDialog> mapLogDialogProvider;
	
	/**
	 * Build a {@link MapLogAction} with a {@link MapLoggerDao}
	 * @param mapLoggerDao dao of the {@link MapLogger}
	 */
	@Inject
	public MapLogAction(MapLoggerDao mapLoggerDao) {
		super(null,MAP_LOG_ICON);
		setEnabled(false);
		this.mapLoggerDao = mapLoggerDao;
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if(selectedMap != null) {
			try {
				MapLogger mapLogger = mapLoggerDao.load(selectedMap.getName());
				System.out.println(mapLogger.getLogMessages());
				MapLogDialog mapLogDialog = mapLogDialogProvider.get();
				mapLogDialog.setMapLogs(mapLogger.getLogMessages());
				mapLogDialog.setVisible(true);
			}
			catch(Exception ex) {
				ex.printStackTrace();
			}
		}
		
	}

	/* (non-Javadoc)
	 * @see towerdefense.gui.MapListPanel.MapSelectionListener#mapSelected(ca.concordia.soen6441.logic.GameMap)
	 */
	@Override
	public void mapSelected(GameMap gameMap) {
		setEnabled(gameMap != null);
		selectedMap = gameMap;
	}
}
