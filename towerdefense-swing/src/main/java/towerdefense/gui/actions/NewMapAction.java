package towerdefense.gui.actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import towerdefense.gui.MapEditionDialog;
import towerdefense.gui.MapListPanel.MapSelectionListener;
import towerdefense.gui.NewMapDialog;
import ca.concordia.soen6441.logic.GameMap;


public class NewMapAction extends AbstractAction implements MapSelectionListener {

	/**
	 * 
	 */
	private static final Icon NEW_ICON = new ImageIcon(Object.class.getResource("/icons/newb.png"));
    
	
	private static final long serialVersionUID = 7403090617352119267L;
	private final MapEditionDialog mapEditionDialog;
	private GameMap selectedMap = null;

	private static final String saveMapText = "Would You Like to Save your Game?";
	private static final String newMapText = "Enter the x and y values";

	public NewMapAction(MapEditionDialog mapEditionDialog) {
		super(null, NEW_ICON);
		this.mapEditionDialog = mapEditionDialog;
		setEnabled(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		NewMapDialog newMapDialog = new NewMapDialog();
		JOptionPane.showConfirmDialog(null, newMapDialog,newMapText,JOptionPane.OK_CANCEL_OPTION);
		mapEditionDialog.setMap(new GameMap(newMapDialog.getXField(), newMapDialog.getYField()));
		mapEditionDialog.setVisible(true);

		


    }
	
	@Override
	public void mapSelected(GameMap gameMap) {
		selectedMap = gameMap;
		setEnabled(selectedMap != null);
	}

}
