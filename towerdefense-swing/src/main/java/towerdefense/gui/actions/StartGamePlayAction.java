package towerdefense.gui.actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Icon;
import javax.swing.ImageIcon;

import towerdefense.gui.GamePlayDialog;
import towerdefense.gui.MapListPanel.MapSelectionListener;
import ca.concordia.soen6441.logic.GamePlay;
import ca.concordia.soen6441.logic.Map;

public class StartGamePlayAction extends AbstractAction implements MapSelectionListener {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7403090617352119267L;
	private static final Icon START_ICON = new ImageIcon("start.png");
	private Map selectedMap = null;
	
	public StartGamePlayAction() {
		super(null, START_ICON);
		
		setEnabled(false);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		GamePlayDialog gamePlayDialog = new GamePlayDialog(new GamePlay(selectedMap, 1000));
		gamePlayDialog.setVisible(true);
	}

	@Override
	public void mapSelected(Map map) {
		selectedMap = map;
		setEnabled(selectedMap != null);
		
	}

}
