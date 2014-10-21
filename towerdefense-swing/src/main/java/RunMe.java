import towerdefense.gui.MapEditionDialog;
import towerdefense.gui.StartGameDialog;
import towerdefense.gui.actions.MapEditAction;
import towerdefense.gui.actions.StartGamePlayAction;
import ca.concordia.soen6441.io.MapJavaSerializationPersister;
import ca.concordia.soen6441.io.MapPersister;



public class RunMe {


	private static void createAndShowGUI() {
		MapPersister mapDao = new MapJavaSerializationPersister(); 
		MapEditionDialog mapEditionDialog = new MapEditionDialog(mapDao);
		mapEditionDialog.setVisible(false);
		MapEditAction mapEditAction = new MapEditAction(mapEditionDialog);
		StartGamePlayAction startGamePlayAction = new StartGamePlayAction();
        StartGameDialog startPanel = new StartGameDialog(mapDao, mapEditAction, startGamePlayAction);
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
