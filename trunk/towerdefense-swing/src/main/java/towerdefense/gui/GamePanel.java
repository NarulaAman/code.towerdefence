package towerdefense.gui;

import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import ca.concordia.soen6441.logic.GamePlay;
import ca.concordia.soen6441.logic.Map;

public class GamePanel extends JPanel {
	
	private static final Image TOWER_ICON = new ImageIcon("tilepath-exit.jpg")
	.getImage();
	private MapPanel mapPanel;
	public GamePanel(GamePlay gamePlay) {
		super(new GridBagLayout());
		
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.fill = GridBagConstraints.BOTH;
		constraints.weightx = constraints.weighty = 1.0;
		mapPanel = new MapPanel(gamePlay.getMap()) {
			
			@Override
			public void coordinatesClicked(int x, int y) {
				
				
			}
		};
		add(mapPanel, constraints);
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		
			g.drawImage(TOWER_ICON, tileToScreenX(2),
					tileToScreenY(2), getTileWidth(), getTileHeight(),
					this);
		
		
		
	}
	
	
	int tileToScreenX(int x) {
		return mapPanel.tileToScreenX(x);
	}

	int tileToScreenY(int y) {
		return mapPanel.tileToScreenY(y);
	}
	
	int getTileWidth() {
		return mapPanel.getTileWidth();
	}

	int getTileHeight() {
		return mapPanel.getTileHeight();
	}
	
	/**
	 * Create the GUI and show it. For thread safety, this method should be
	 * invoked from the event-dispatching thread.
	 */
	private static void createAndShowGUI() {
		// Create and set up the window.
		JFrame frame = new JFrame("HelloWorldSwing");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Add the ubiquitous "Hello World" label.

		Map map = new Map(9, 9);

		GamePlay level = new GamePlay(map, 1000);
		GamePanel gamePanel = new GamePanel(level);

		frame.getContentPane().add(gamePanel);

		// Display the window.
		frame.pack();
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		// Schedule a job for the event-dispatching thread:
		// creating and showing this application's GUI.
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});
	}

}
