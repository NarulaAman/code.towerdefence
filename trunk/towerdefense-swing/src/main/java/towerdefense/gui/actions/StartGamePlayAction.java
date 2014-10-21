package towerdefense.gui.actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import towerdefense.gui.GamePlayDialog;
import towerdefense.gui.MapListPanel;
import towerdefense.gui.MapListPanel.MapSelectionListener;
import ca.concordia.soen6441.logic.GamePlay;
import ca.concordia.soen6441.logic.Map;

public class StartGamePlayAction extends AbstractAction implements MapSelectionListener {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7403090617352119267L;
	private Map selectedMap = null;
	
	public StartGamePlayAction() {
		setEnabled(false);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		GamePlayDialog gamePlayDialog = new GamePlayDialog(new GamePlay(selectedMap, 1000));
	}

	@Override
	public void mapSelected(Map map) {
		selectedMap = map;
		setEnabled(selectedMap != null);
		
	}

}
