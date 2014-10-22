package towerdefense.gui.actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import towerdefense.gui.MapEditionDialog;
import towerdefense.gui.MapListPanel.MapSelectionListener;
import towerdefense.gui.NewMapDialog;
import ca.concordia.soen6441.logic.GameMap;
import ca.concordia.soen6441.logic.MapValidator;

public class NewMapAction extends AbstractAction implements MapSelectionListener {

	/**
	 * 
	 */
	private static final Icon EDIT_ICON = new ImageIcon(Object.class.getResource("/icons/edit.png"));

	private static final long serialVersionUID = 7403090617352119267L;
	private final MapEditionDialog mapEditionDialog;
	private GameMap selectedMap = null;

	private static final String saveMapText = "Would You Like to Save your Game?";
	private static final String newMapText = "Enter the x and y values";

	public NewMapAction(MapEditionDialog mapEditionDialog) {
		super(null, EDIT_ICON);
		this.mapEditionDialog = mapEditionDialog;
		setEnabled(false);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		int dialogResult = JOptionPane.showConfirmDialog (null,saveMapText,"Warning",JOptionPane.YES_NO_OPTION);
		

		if(dialogResult == JOptionPane.YES_OPTION) {

			Boolean checkMapValidate = mapEditionDialog.saveMap();

			if(checkMapValidate) {
				setNewMap();
			}
			
	    } else {
	    	setNewMap();
	    }


    }

	public void setNewMap()
	{
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
