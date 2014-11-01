package towerdefense.gui;

import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import towerdefense.gui.MapPanel.MapGridCoordinateClickedListener;
import ca.concordia.soen6441.logic.GamePlay;
import ca.concordia.soen6441.logic.Tower;
import ca.concordia.soen6441.logic.primitives.GridPosition;

/**
 * This class is responsible for painting the {@link GamePlay} action, like enemies moving and towers 
 *
 */
public class GamePlayPanel extends JPanel implements Observer, MapGridCoordinateClickedListener {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6461365434058158286L;

	/**
	 * This is a listener for selected {@link Tower} in the {@link GamePlayPanel}
	 *
	 */
	public interface TowerSelectedListener {
		/**
		 * Indicates that a tower has been selected in the {@link GamePlayPanel}
		 * @param tower tower selected in the {@link GamePlayPanel}
		 */
		void towerSelected(Tower tower);
	}

	private static final Image TOWER_ICON = new ImageIcon(Object.class.getResource("/icons/tower.png")).getImage();
	private final MapPanel mapPanel = new MapPanel();
	private final GamePlay gamePlay;
	private MapGridCoordinateClickedListener mapGridCoordinateClickedListener;
	private TowerSelectedListener towerSelectedListener;
	
	/**
	 * Constructs a {@link GamePlayPanel} to show an instance of a {@link GamePlay}
	 * @param gamePlay to be displayed
	 */
	public GamePlayPanel(GamePlay gamePlay) {
		super(new GridBagLayout());
		this.gamePlay = gamePlay;
		gamePlay.addObserver(this);
		mapPanel.addMapGridCoordinateClickedListener(this);
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.fill = GridBagConstraints.BOTH;
		constraints.weightx = constraints.weighty = 1.0;
		mapPanel.setMap(gamePlay.getMap());
		add(mapPanel, constraints);
	}
	
	/**
	 * Constructs a {@link GamePlayPanel} to show an instance of a {@link GamePlay}
	 * @param g {@link Graphics} instance to paint to
	 */
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		for (Tower tower : getGamePlay().getTowers()) {
			g.drawImage(TOWER_ICON, tileToScreenX(tower.getGridPosition().getX()),
					tileToScreenY(tower.getGridPosition().getY()), getTileWidth(), getTileHeight(),
					this);
		}
	}
	
	/**
	 * Method triggered when the position is clicked
	 * @param gridPosition is the position has been clicked
	 */
	@Override
	public void mapGridCoordinateClicked(GridPosition gridPosition) {
		if (getGamePlay().hasTower(gridPosition))
		{
			fireTowerSelected(getGamePlay().getTower(gridPosition));
		}
		else {
			fireMapGridCoordinateClickedListener(gridPosition);
		}
	}
	
	
	/**
	 * Transforms from grid coordinates to screen coordinates
	 * @param x the grid coordinate
	 * @return the screen coordinate
	 */
	int tileToScreenX(int x) {
		return mapPanel.tileToScreenX(x);
	}

	/**
	 * Transforms from grid coordinates to screen coordinates
	 * @param y the grid coordinate
	 * @return the screen coordinate
	 */
	int tileToScreenY(int y) {
		return mapPanel.tileToScreenY(y);
	}
	
	
	/**
	 * Returns the width of the tiles in the {@link MapPanel}
	 * @return the width of the tiles in the {@link MapPanel}
	 */
	int getTileWidth() {
		return mapPanel.getTileWidth();
	}

	/**
	 * Returns the height of the tiles in the {@link MapPanel}
	 * @return the height of the tiles in the {@link MapPanel}
	 */
	int getTileHeight() {
		return mapPanel.getTileHeight();
	}
	
	/**
	 * Invoke the {@link TowerSelectedListener} with a selected {@link Tower}
	 * @param tower {@link Tower} selected with a click
	 */
	private void fireTowerSelected(Tower tower) {
		if (towerSelectedListener != null) {
			towerSelectedListener.towerSelected(tower);
		}
	}
	
	/**
	 * Invoke the {@link MapGridCoordinateClickedListener} with {@link GridPosition} clicked on the {@link MapPanel}. 
	 * This method will only be called when not clicked on a {@link Tower}
	 * @param gridPosition {@link GridPosition} clicked in the {@link MapPanel}
	 */
	private void fireMapGridCoordinateClickedListener(GridPosition gridPosition) {
		if (mapGridCoordinateClickedListener != null) {
			mapGridCoordinateClickedListener.mapGridCoordinateClicked(gridPosition);
		}
	}

	/**
	 * Returns the current {@link GamePlay}
	 * @return the current {@link GamePlay}
	 */
	private GamePlay getGamePlay() {
		return gamePlay;
	}
	
	
	/**
	 * Sets the {@link MapGridCoordinateClickedListener}, this listener will be invoked when the {@link GamePlayPanel} is clicked and the object clicked is not a {@link Tower}
	 * @param listener {@link MapGridCoordinateClickedListener} to be set
	 */
	public void setMapGridCoordinateClickedListener(MapGridCoordinateClickedListener listener) {
		this.mapGridCoordinateClickedListener = listener;
	}
	
	
	/**
	 * Sets the {@link TowerSelectedListener}, this listener will be invoked when a {@link Tower} is clicked on the {@link GamePlayPanel}
	 * @param listener
	 */
	public void setTowerSelectedListener(TowerSelectedListener listener) {
		this.towerSelectedListener = listener;
	}

	/**
	 * Update the scream
	 * @param o is ignored
	 * @param arg is ignored
	 */
	@Override
	public void update(Observable o, Object arg) {
		repaint();
	}

	/**
	 * Disposes of this panel, does clean
	 */
	public void dispose() {
		mapPanel.dispose();
		if (gamePlay != null) {
			gamePlay.deleteObserver(this);
		}
	}
	
//	/**
//	 * Create the GUI and show it. For thread safety, this method should be
//	 * invoked from the event-dispatching thread.
//	 */
//	private static void createAndShowGUI() {
//		// Create and set up the window.
//		JFrame frame = new JFrame("HelloWorldSwing");
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//		// Add the ubiquitous "Hello World" label.
//
//		GameMap gameMap = new GameMap(9, 9);
//
//		GamePlay level = new GamePlay(gameMap, 1000);
//		GamePlayPanel gamePanel = new GamePlayPanel(level);
//
//		frame.getContentPane().add(gamePanel);
//
//		// Display the window.
//		frame.pack();
//		frame.setVisible(true);
//	}
//
//	public static void main(String[] args) {
//		// Schedule a job for the event-dispatching thread:
//		// creating and showing this application's GUI.
//		javax.swing.SwingUtilities.invokeLater(new Runnable() {
//			public void run() {
//				createAndShowGUI();
//			}
//		});
//	}
}
