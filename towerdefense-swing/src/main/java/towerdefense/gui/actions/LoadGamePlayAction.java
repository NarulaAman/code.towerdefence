package towerdefense.gui.actions;

import java.awt.event.ActionEvent;
import java.io.IOException;

import javax.swing.AbstractAction;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import towerdefense.gui.GamePlayDialog;
import ca.concordia.soen6441.dao.GamePlayDao;
import ca.concordia.soen6441.io.GamePlayJavaSerialaizationDao;
import ca.concordia.soen6441.logic.GamePlay;

/**
 * Action to start playing the game on the {@link GamePlayDialog}
 *
 */
public class LoadGamePlayAction extends AbstractAction {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7403090617352119267L;
	private static final Icon LOAD_ICON = new ImageIcon(Object.class.getResource("/icons/Load.png"));
	
	private GamePlayDao gamePlayDao=new GamePlayJavaSerialaizationDao();
	
	/**
	 * Constructs a {@link LoadGamePlayAction}, new instances of the {@link GamePlayDialog} will be created
	 * from inside this action for every new game
	 */
	public LoadGamePlayAction() {
		super(null, LOAD_ICON);
		setEnabled(true);
	}
	
	/**
	 * Performs the {@link LoadGamePlayAction}
	 * @param e not used - ignored
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			if (gamePlayDao.listAllNames().size() > 0 && gamePlayDao.listAllNames().contains("gamePlay1")) {
				GamePlay loadedObj= gamePlayDao.load("gamePlay1");
				GamePlayDialog gamePlayDialog = new GamePlayDialog(loadedObj);
				gamePlayDialog.setVisible(true);
			}
			else {
				JOptionPane.showMessageDialog(null,
					    "There is no game saved!\n"
					    + "I could have prevented you from making an error, but I didn't\n"
					    + "This software was written with the programmer in mind and not the user",
					    "No map to load error",
					    JOptionPane.ERROR_MESSAGE);
			}
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
	

}
