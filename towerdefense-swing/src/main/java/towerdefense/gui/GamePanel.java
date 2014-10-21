package towerdefense.gui;

import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import towerdefense.gui.MapPanel.MapGridCoordinateClickedListener;
import ca.concordia.soen6441.logic.GamePlay;
import ca.concordia.soen6441.logic.Map;
import ca.concordia.soen6441.logic.Tower;
import ca.concordia.soen6441.logic.primitives.Coordinate;

public class GamePanel extends JPanel implements Observer {
	
	public interface TowerSelectedListener {
		void towerSelected(Tower tower);
	}
	
	private static final Image TOWER_ICON = new ImageIcon("tower.png")
	
	.getImage();
	private final MapPanel mapPanel;
	private final GamePlay gamePlay;
	private MapGridCoordinateClickedListener mapGridCoordinateClickedListener;
	private TowerSelectedListener towerSelectedListener;
	public GamePanel(GamePlay gamePlay) {
		super(new GridBagLayout());
		this.gamePlay = gamePlay;
		gamePlay.addObserver(this);
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.fill = GridBagConstraints.BOTH;
		constraints.weightx = constraints.weighty = 1.0;
		mapPanel = new MapPanel() {
			@Override
			public void coordinatesClicked(int x, int y) {
				if (getGamePlay().hasTower(x, y))
				{
					fireTowerSelected(getGamePlay().getTower(x, y));
				}
				else {
					fireMapGridCoordinateClickedListener(new Coordinate(x, y));
				}
					
			}
		};
		mapPanel.setMap(gamePlay.getMap());
		add(mapPanel, constraints);
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		
		for (Tower tower : getGamePlay().getTowers()) {
			g.drawImage(TOWER_ICON, tileToScreenX(tower.getCoordinate().getX()),
					tileToScreenY(tower.getCoordinate().getY()), getTileWidth(), getTileHeight(),
					this);
		}
		
		
		
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
	
	private void fireTowerSelected(Tower tower) {
		if (towerSelectedListener != null) {
			towerSelectedListener.towerSelected(tower);
		}
	}
	
	private void fireMapGridCoordinateClickedListener(Coordinate coord) {
		if (mapGridCoordinateClickedListener != null) {
			mapGridCoordinateClickedListener.mapGridCoordinateClicked(coord);
		}
	}

	public GamePlay getGamePlay() {
		return gamePlay;
	}
	
	public void setMapGridCoordinateClickedListener(MapGridCoordinateClickedListener listener) {
		this.mapGridCoordinateClickedListener = listener;
	}
	
	public void setTowerSelectedListener(TowerSelectedListener listener) {
		this.towerSelectedListener = listener;
	}

	@Override
	public void update(Observable o, Object arg) {
		repaint();
	}
	
	
}
