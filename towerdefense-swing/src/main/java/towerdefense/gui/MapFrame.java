package towerdefense.gui;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import ca.concordia.soen6441.logic.Map;




public class MapFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JPanel gridPanel = null;
	
	public MapFrame()
	{
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		gridPanel = new GridPanel(new Map(10, 10));
		add(gridPanel);
		pack();
	}

	
	 public static void main(String[] args) {
	        SwingUtilities.invokeLater(new Runnable() {
	            @Override
	            public void run() {
	                new MapFrame();
	            }
	        });

	    }
}
