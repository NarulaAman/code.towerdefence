import towerdefense.gui.MapEditionDialog;
import towerdefense.gui.StartGameDialog;
import towerdefense.gui.actions.MapEditAction;
import towerdefense.gui.actions.StartGamePlayAction;
import ca.concordia.soen6441.io.GameMapJavaSerializationDao;
import ca.concordia.soen6441.io.GameMapDao;



public class RunMe {


	private static void createAndShowGUI() {
		GameMapDao gameMapDao = new GameMapJavaSerializationDao(); 
		MapEditionDialog mapEditionDialog = new MapEditionDialog(gameMapDao);
		mapEditionDialog.setVisible(false);
		MapEditAction mapEditAction = new MapEditAction(mapEditionDialog);
		StartGamePlayAction startGamePlayAction = new StartGamePlayAction();
        StartGameDialog startPanel = new StartGameDialog(gameMapDao, mapEditAction, startGamePlayAction);
        startPanel.setVisible(true);
    }
 
    public static void main(String[] args) {
    	// Run the GUI in the EDT thread
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }

}
