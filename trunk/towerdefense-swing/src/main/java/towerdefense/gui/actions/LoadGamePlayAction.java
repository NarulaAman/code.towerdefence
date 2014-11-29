package towerdefense.gui.actions;

import java.awt.event.ActionEvent;
import java.io.IOException;

import javax.swing.AbstractAction;
import javax.swing.Icon;
import javax.swing.ImageIcon;

import towerdefense.gui.GamePlayDialog;
import towerdefense.gui.MapListPanel.MapSelectionListener;
import ca.concordia.soen6441.io.GamePlayJavaSerialaizationDao;
import ca.concordia.soen6441.logic.GameMap;
import ca.concordia.soen6441.logic.GamePlay;
import ca.concordia.soen6441.logic.GamePlayDao;

/**
 * Action to start playing the game on the {@link GamePlayDialog}
 *
 */
public class LoadGamePlayAction extends AbstractAction implements MapSelectionListener {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7403090617352119267L;
	private static final Icon START_ICON = new ImageIcon(Object.class.getResource("/icons/start.png"));
	private GameMap selectedMap = null;
	private GamePlayDao gamePlayDao=new GamePlayJavaSerialaizationDao();
	
	/**
	 * Constructs a {@link LoadGamePlayAction}, new instances of the {@link GamePlayDialog} will be created
	 * from inside this action for every new game
	 */
	public LoadGamePlayAction() {
		super(null, START_ICON);
		setEnabled(false);
	}
	
	/**
	 * Performs the {@link LoadGamePlayAction}
	 * @param e not used - ignored
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			GamePlay loadedObj= gamePlayDao.load("gamePlay1");
			GamePlayDialog gamePlayDialog = new GamePlayDialog(loadedObj);
			gamePlayDialog.setVisible(true);
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
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