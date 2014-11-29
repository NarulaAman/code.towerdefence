package towerdefense.gui.actions;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.swing.AbstractAction;

import towerdefense.gui.MapEditionDialog;
import towerdefense.gui.MapListPanel.MapSelectionListener;
import towerdefense.gui.log.LogMessageTableModel;
import towerdefense.gui.log.MapLogDialog;
import ca.concordia.soen6441.logger.LogFilter;
import ca.concordia.soen6441.logger.LogManager;
import ca.concordia.soen6441.logger.LogMessage;
import ca.concordia.soen6441.logic.GameMap;

public class MapLogAction extends AbstractAction implements MapSelectionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final LogMessageTableModel logMessageTableModel;
	private List<LogMessage> logMessages= new ArrayList<LogMessage>();
	private GameMap selectedMap = null;
	
	@Inject
	public MapLogAction(LogMessageTableModel logMessageTableModel) {
		this.logMessageTableModel = logMessageTableModel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(selectedMap != null) {
			
		}
		
	}

	@Override
	public void mapSelected(GameMap gameMap) {
		setEnabled(selectedMap != null);
		selectedMap = gameMap;
	}
//	
//	private MapLogDialog getMapLogDialog() {
//		return new MapLogDialog(logMessageTableModel);
//	}
}
