package towerdefense.gui.actions;

import java.awt.event.ActionEvent;

import javax.inject.Inject;
import javax.swing.AbstractAction;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import towerdefense.gui.MapEditionDialog;
import towerdefense.gui.NewMapDialog;
import ca.concordia.soen6441.logic.GameMap;

import com.google.inject.Provider;


/**
 * Action to create a new {@link GameMap} in the {@link MapEditionDialog}
 *
 */
public class NewMapAction extends AbstractAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7403090617352119267L;
	private static final Icon NEW_ICON = new ImageIcon(Object.class.getResource("/icons/new_button.png"));
	private static final String MAP_SIZE_TEXT = "Enter the width and height for the new Map";
	
	@Inject
	private Provider<MapEditionDialog> mapEditionDialog;

	/**
	 * Constructs a {@link NewMapAction} to edit a map o a given {@link MapEditionDialog}
	 * @param mapEditionDialog {@link MapEditionDialog} to 
	 */
	public NewMapAction() {
		super(null, NEW_ICON);
		setEnabled(true);
	}

	/**
	 * Performs the {@link NewMapAction}
	 * @param e not used - ignored
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		NewMapDialog newMapDialog = new NewMapDialog();
		
		boolean stillNeedWidthHeight = true;
		do {
			int okOrCancel = JOptionPane.showConfirmDialog(null, newMapDialog, MAP_SIZE_TEXT, JOptionPane.OK_CANCEL_OPTION);
			if(okOrCancel == JOptionPane.CANCEL_OPTION) {
				return;
			}
			int width = newMapDialog.getWidthValue();
			int height = newMapDialog.getHeightValue();
			
			if (width < 2 || height < 2) {
				JOptionPane.showMessageDialog(null, "Width and Height should be greater than 1");
			}
			else {
				stillNeedWidthHeight = false;
				getMapEditionDialog().setMap(new GameMap(width, height));
				getMapEditionDialog().setVisible(true);
			}
		} while (stillNeedWidthHeight);
    }
	
	
	private MapEditionDialog getMapEditionDialog() {
		return mapEditionDialog.get();
	}
}
