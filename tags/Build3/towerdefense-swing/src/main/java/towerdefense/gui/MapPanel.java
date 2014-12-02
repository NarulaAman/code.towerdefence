package towerdefense.gui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.vecmath.Point2f;

import ca.concordia.soen6441.logic.GameMap;
import ca.concordia.soen6441.logic.Tile;
import ca.concordia.soen6441.logic.primitives.GridPosition;

/**
 * This class is responsible for painting the {@link GameMap}'s {@link Tile}s and Start and End position
 *
 */
public class MapPanel extends JPanel implements Observer{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6093261930596167143L;

	/**
	 * This is a Listener that will be notified when a position is clicked on the {@link MapPanel}
	 *
	 */
	public interface MapGridCoordinateClickedListener {
		/**
		 * This method gets invoked when a {@link GridPosition} is clicked on the {@link MapPanel}
		 * @param gridPosition {@link GridPosition} clicked on the {@link MapPanel}
		 */
		void mapGridCoordinateClicked(GridPosition gridPosition);
	}
	
	private static final Image ENEMY_PATH_ICON = new ImageIcon(Object.class.getResource("/icons/tilepath.jpg"))
			.getImage();
	private static final Image SCENERY_ICON = new ImageIcon(Object.class.getResource("/icons/grass.jpg"))
			.getImage();
	private static final Image START_ICON = new ImageIcon(Object.class.getResource("/icons/tilepath_start.jpg"))
			.getImage();
	private static final Image EXIT_ICON = new ImageIcon(Object.class.getResource("/icons/tilepath_exit.jpg"))
			.getImage();
	
	private final List<MapGridCoordinateClickedListener> clickListenerList = new ArrayList<>();

	private GameMap gameMap = null;
	


	/**
	 * Created a {@link MapPanel} with no {@link GameMap} associated with it. It will be painted grey.
	 */
	public MapPanel() {
		super();
		setPreferredSize(new Dimension(640, 640));

		addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (gameMap == null) {
					return;
				}
				int x = e.getX();
				int y = e.getY();
				fireMapGridCoordinateClickedListener(new GridPosition(screenToTileX(x), screenToTileY(y)));
				
			};
		});
	}
	
	/**
	 * Invoke the {@link MapGridCoordinateClickedListener} on a clicked {@link GridPosition}
	 * @param gridPosition {@link GridPosition} that was clicked on the {@link MapPanel}
	 */
	private void fireMapGridCoordinateClickedListener(GridPosition gridPosition) {
		for (MapGridCoordinateClickedListener listener : clickListenerList) {
			listener.mapGridCoordinateClicked(gridPosition);
		}
	}
	
	/**
	 * Adds a {@link MapGridCoordinateClickedListener} to this {@link MapPanel}
	 * @param listener {@link MapGridCoordinateClickedListener} to be added
	 */
	public void addMapGridCoordinateClickedListener(MapGridCoordinateClickedListener listener) {
		clickListenerList.add(listener);
	}
	
	/**
	 * Paints the component by a given graphic instance
	 * @param g graphic instance
	 */
	@Override
	public void paint(Graphics g) {
		if (getMap() == null) {
			g.setColor(getBackground());
			g.fillRect(0, 0, getWidth(), getHeight());
			return;
		}
		for (int x = 0; x < getMap().getWidth(); ++x) {
			for (int y = 0; y < getMap().getHeight(); ++y) {
				Tile tile = getMap().getTile(new GridPosition(x, y));
				if (tile == Tile.SCENERY) {
					g.drawImage(SCENERY_ICON, tileToScreenX(x),
							tileToScreenY(y), getTileWidth(), getTileHeight(),
							this);
				} else if (tile == Tile.ENEMY_PATH) {
					g.drawImage(ENEMY_PATH_ICON, tileToScreenX(x),
							tileToScreenY(y), getTileWidth(), getTileHeight(),
							this);
				}
			}
		}
		if (getMap().hasStartTile()) {
			GridPosition start = getMap().getStartGridPosition();
			g.drawImage(START_ICON, tileToScreenX(start.getX()),
					tileToScreenY(start.getY()), getTileWidth(), getTileHeight(),
					this);
		}
		if (getMap().hasEndTile()) {
			GridPosition end = getMap().getEndGridPosition();
			g.drawImage(EXIT_ICON, tileToScreenX(end.getX()),
					tileToScreenY(end.getY()), getTileWidth(), getTileHeight(),
					this);
		}
	}

	/**
	 * Returns the current {@link GameMap} being displayed on this {@link MapPanel}
	 * @return the current {@link GameMap} being displayed on this {@link MapPanel}
	 */
	private GameMap getMap() {
		return gameMap;
	}

	
	/**
	 * Converts from {@link GridPosition} coordinate to a screen coordinate
	 * @param x {@link GridPosition} X coordinate to be converted
	 * @return screen coordinate of the {@link GridPosition} X coordinate
	 */
	public int tileToScreenX(int x) {
		return x * getTileWidth();
	}

	/**
	 * Converts from {@link GridPosition} coordinate to a screen coordinate
	 * @param y {@link GridPosition} Y coordinate to be converted
	 * @return screen coordinate of the {@link GridPosition} Y coordinate
	 */
	int tileToScreenY(int y) {
		return y * getTileHeight();
	}
	
	/**
	 * Tile to screen.
	 *
	 * @param point the point
	 * @return the point2f
	 */
	public Point2f tileToScreen(Point2f point) {
		return new Point2f(point.x * getTileWidth(), point.y * getTileHeight());
	}

	/**
	 * Convert from screen X coordinate to a {@link GridPosition} X coordinate
	 * @param x to be converted
	 * @return the {@link GridPosition} X coordinate
	 */
	private int screenToTileX(int x) {
		return x / getTileWidth();
	}

	/**
	 * Convert from screen Y coordinate to a {@link GridPosition} Y coordinate
	 * @param y to be converted
	 * @return the {@link GridPosition} Y coordinate
	 */
	private int screenToTileY(int y) {
		return y / getTileHeight();
	}

	/**
	 * Returns the width of the tile
	 * @return the width of the tile
	 */
	int getTileWidth() {
		return getWidth() / getMap().getWidth();
	}

	/**
	 * Returns the height of the tile
	 * @return the height of the tile
	 */
	int getTileHeight() {
		return getHeight() / getMap().getHeight();
	}

	/**
	 * update the view
	 * @param o is ignored
	 * @param arg is ignored
	 */
	@Override
	public void update(Observable o, Object arg) {
		repaint();
	}
	
	/**
	 * Sets a map to be displayed by this {@link MapPanel}
	 * @param gameMap {@link GameMap} to be set
	 */
	public void setMap(GameMap gameMap) {
		if (this.gameMap != null) {
			this.gameMap.deleteObserver(this);
		}
		this.gameMap = gameMap;
		if (gameMap != null) {
			gameMap.addObserver(this);
		}
		repaint();
	}
	
	/**
	 * Method to be called as this panel is disposed
	 */
	public void dispose() {
		if (gameMap != null) {
			gameMap.deleteObserver(this);
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
//		MapPanel label = new MapPanel();
//		
//		label.setMap(new GameMap(10, 10));
//		frame.getContentPane().add(label);
//
//		// Display the window.
//		frame.pack();
//		frame.setVisible(true);
//	}
//
//	/**
//	 * Main method used for testing the GUI
//	 * @param args arguments are ignored by this method
//	 */
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
