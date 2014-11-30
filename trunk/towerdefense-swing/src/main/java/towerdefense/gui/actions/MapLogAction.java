package towerdefense.gui.actions;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.swing.AbstractAction;
import javax.swing.Icon;
import javax.swing.ImageIcon;

import com.google.inject.Guice;
import com.google.inject.Injector;

import towerdefense.gui.MapEditionDialog;
import towerdefense.gui.MapListPanel;
import towerdefense.gui.StartGameDialog;
import towerdefense.gui.MapListPanel.MapSelectionListener;
import towerdefense.gui.guice.GuiModule;
import towerdefense.gui.log.LogMessageTableModel;
import towerdefense.gui.log.MapLogDialog;
import ca.concordia.soen6441.logger.LogFilter;
import ca.concordia.soen6441.logger.LogManager;
import ca.concordia.soen6441.logger.LogMessage;
import ca.concordia.soen6441.logger.MapLogger;
import ca.concordia.soen6441.logic.GameMap;
import ca.concordia.soen6441.logic.MapLoggerDao;

public class MapLogAction extends AbstractAction implements MapListPanel.MapSelectionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final LogMessageTableModel logMessageTableModel;
	private List<LogMessage> logMessages= new ArrayList<LogMessage>();
	//private final MapLoggerDao mapLoggerDao;
	private GameMap selectedMap = null;
	private MapLoggerDao mapLoggerDao;
	private static final Icon MAP_LOG_ICON = new ImageIcon(Object.class.getResource("/icons/maplogbutton.png"));
	
	@Inject MapLogDialog mapLogDialog;
	
	@Inject
	public MapLogAction(LogMessageTableModel logMessageTableModel, MapLoggerDao mapLoggerDao) {
		super(null,MAP_LOG_ICON);
		this.mapLoggerDao = mapLoggerDao;
		this.logMessageTableModel = logMessageTableModel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(selectedMap != null) {
			try {
				System.out.println("in action map log");
				
			
				
				MapLogger mapLogger = mapLoggerDao.load(selectedMap.getName());
				System.out.println(mapLogger.getLogMessages());
				mapLogDialog.setMapLogs(mapLogger.getLogMessages());
				mapLogDialog.setVisible(true);
				
				
			}
			catch(Exception ex) {
				ex.printStackTrace();
			}
		}
		
	}

	@Override
	public void mapSelected(GameMap gameMap) {
		setEnabled(selectedMap != null);
		selectedMap = gameMap;
	}
	
	
}
