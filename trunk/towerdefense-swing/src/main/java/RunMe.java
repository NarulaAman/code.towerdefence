import towerdefense.gui.StartGameDialog;



public class RunMe {


	private static void createAndShowGUI() {


        StartGameDialog startPanel = new StartGameDialog();
        
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
