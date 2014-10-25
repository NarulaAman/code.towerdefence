package towerdefense.gui.actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Icon;
import javax.swing.ImageIcon;

import towerdefense.gui.GamePlayDialog;
import towerdefense.gui.MapEditionDialog;
import towerdefense.gui.MapListPanel.MapSelectionListener;
import ca.concordia.soen6441.logic.GameMap;
import ca.concordia.soen6441.logic.GamePlay;

/**
 * Action to start playing the game on the {@link GamePlayDialog}
 *
 */
public class StartGamePlayAction extends AbstractAction implements MapSelectionListener {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7403090617352119267L;
	private static final Icon START_ICON = new ImageIcon(Object.class.getResource("/icons/start.png"));
	private GameMap selectedMap = null;
	
	/**
	 * Constructs a {@link StartGamePlayAction}, new instances of the {@link GamePlayDialog} will be created
	 * from inside this action for every new game
	 */
	public StartGamePlayAction() {
		super(null, START_ICON);
		setEnabled(false);
	}
	
	/**
	 * Performs the {@link StartGamePlayAction}
	 * @param e not used - ignored
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		GamePlayDialog gamePlayDialog = new GamePlayDialog(new GamePlay(selectedMap, 1000));
		gamePlayDialog.setVisible(true);
	}

	/**
	 * Invoked when a {@link GameMap} is selected to be played, can be null
	 * @param gameMap {@link GameMap} to be played 
	 */
	@Override
	public void mapSelected(GameMap gameMap) {
		selectedMap = gameMap;
		setEnabled(selectedMap != null);
		
	}

}
