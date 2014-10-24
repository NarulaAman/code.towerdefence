package towerdefense.gui.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import towerdefense.gui.MapEditionDialog;
import towerdefense.gui.MapListPanel;
import towerdefense.gui.StartGameDialog;
import ca.concordia.soen6441.io.GameMapDao;
import ca.concordia.soen6441.logic.GameMap;
import ca.concordia.soen6441.logic.MapValidator;

public class SaveMapAction implements ActionListener{
	
	private MapValidator mapValidator = new MapValidator();
	private MapEditionDialog mapEditionDialog;
	private GameMapDao gameMapDao;
	private StartGameDialog startGameDialog;

	public SaveMapAction(MapEditionDialog mapEditionDialog, StartGameDialog startGameDialog) {
		this.mapEditionDialog = mapEditionDialog;
		this.startGameDialog = startGameDialog;
		this.gameMapDao = mapEditionDialog.getMapDao() ;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
            try {
			
			StringBuilder incorrectMap = new StringBuilder("");
			Boolean mapValidate = mapValidator.isValid(mapEditionDialog.getMap(), incorrectMap);


			if(mapValidate) {
				gameMapDao.save(mapEditionDialog.getMap(), mapEditionDialog.getMapName().getText());
			    mapEditionDialog.setVisible(false);
			    startGameDialog.refreshMaps();
			    
			} else {
				JOptionPane.showMessageDialog(null, incorrectMap);
			}
		
			
		} catch (IOException exception) {
			exception.printStackTrace();
		}
		
	}

	public void setStartGameDialog(StartGameDialog startGameDialog) {
		this.startGameDialog = startGameDialog;
	}
}
