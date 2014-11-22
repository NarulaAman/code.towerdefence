package runme;
import towerdefense.gui.StartGameDialog;
import towerdefense.gui.guice.GuiModule;

import com.google.inject.Guice;
import com.google.inject.Injector;



/**
 * This is the class to be Run to start our TowerDefense game
 */
public class RunMe {


	/**
	 * Created the GUI, this should be run in the EDT
	 */
	private static void createAndShowGUI() {
		Injector injector = Guice.createInjector(new GuiModule());
		injector.getInstance(StartGameDialog.class);  
    }
 
    /**
     * Main method our application
     * @param args arguments passed by the environment, they are ignored
     */
    public static void main(String[] args) {
    	// Run the GUI in the EDT thread
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }

}
