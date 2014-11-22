package towerdefense.gui.log;

import java.io.IOException;

import javax.swing.JDialog;
import javax.swing.JFrame;

public class GameLogDialog extends JDialog {

	private GameLogPanel gameLogPanel;
	
	public GameLogDialog() {
		
	}
//	/**
	//	 * Creates the GUI for testing purposes
	//	 * @throws ClassNotFoundException
	//	 * @throws IOException
	//	 */
		private static void createAndShowGUI() throws ClassNotFoundException, IOException {
			//Create and set up the window.
			JFrame frame = new JFrame("GameLogPanel");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			GameLogPanel gameLogPanel = new GameLogPanel();
			frame.setContentPane(gameLogPanel);
	
			//Display the window.
			frame.pack();
			frame.setVisible(true);
	    }
	//
	//	/**
	//	 * Main method used for testing the GUI
	//	 * @param args arguments are ignored by this method
	//	 */
		public static void main(String[] args) {
			javax.swing.SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					try {
						createAndShowGUI();
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
		}
	
}
