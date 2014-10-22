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
import javax.swing.JFrame;
import javax.swing.JPanel;

import ca.concordia.soen6441.logic.GamePlay;
import ca.concordia.soen6441.logic.Map;
import ca.concordia.soen6441.logic.Tile;
import ca.concordia.soen6441.logic.primitives.IntCoordinate;

public class MapPanel extends JPanel implements Observer{

	public interface MapGridCoordinateClickedListener {
		void mapGridCoordinateClicked(int x, int y);
	}
	
	private static final Image ENEMY_PATH_ICON = new ImageIcon("tilepath.jpg")
			.getImage();
	private static final Image SCENERY_ICON = new ImageIcon("grass.jpg")
			.getImage();
	private static final Image START_ICON = new ImageIcon("tilepath-start.jpg")
			.getImage();
	private static final Image EXIT_ICON = new ImageIcon("tilepath-exit.jpg")
			.getImage();

	private Map map = null;
	
	private final List<MapGridCoordinateClickedListener> clickListenerList = new ArrayList<>();

	public MapPanel() {
		super();
		setPreferredSize(new Dimension(640, 480));

		addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (map == null) {
					return;
				}
				int x = e.getX();
				int y = e.getY();
//				System.out.println(screenToTileX(x) + "," + screenToTileY(y));// these co-ords are relative to
												// the component
//				coordinatesClicked(screenToTileX(x), screenToTileY(y));
				fireMapGridCoordinateClickedListener(screenToTileX(x), screenToTileY(y));
				
			};
		});
	}
	
	private void fireMapGridCoordinateClickedListener(int x, int y) {
		for (MapGridCoordinateClickedListener listener : clickListenerList) {
			listener.mapGridCoordinateClicked(x, y);
		}
	}
	
	public void addMapGridCoordinateClickedListener(MapGridCoordinateClickedListener listener) {
		clickListenerList.add(listener);
	}
	
	
	
	
	@Override
	public void paint(Graphics g) {
		if (getMap() == null) {
			g.setColor(getBackground());
			g.fillRect(0, 0, getWidth(), getHeight());
			return;
		}
		for (int x = 0; x < getMap().getWidth(); ++x) {
			for (int y = 0; y < getMap().getHeight(); ++y) {
				Tile tile = getMap().getTile(x, y);
				if (tile.getType() == Tile.TileType.SCENERY) {
					g.drawImage(SCENERY_ICON, tileToScreenX(x),
							tileToScreenY(y), getTileWidth(), getTileHeight(),
							this);
				} else if (tile.getType() == Tile.TileType.ENEMY_PATH) {
					g.drawImage(ENEMY_PATH_ICON, tileToScreenX(x),
							tileToScreenY(y), getTileWidth(), getTileHeight(),
							this);
				}
			}
		}
		
		if (getMap().hasStartTile()) {
			IntCoordinate start = getMap().getStartTile();
			g.drawImage(START_ICON, tileToScreenX(start.getX()),
					tileToScreenY(start.getY()), getTileWidth(), getTileHeight(),
					this);
		}
		
		if (getMap().hasEndTile()) {
			IntCoordinate end = getMap().getEndTile();
			g.drawImage(EXIT_ICON, tileToScreenX(end.getX()),
					tileToScreenY(end.getY()), getTileWidth(), getTileHeight(),
					this);
		}
	}

	Map getMap() {
		return map;
	}

	int tileToScreenX(int x) {
		return x * getTileWidth();
	}

	int tileToScreenY(int y) {
		return y * getTileHeight();
	}
	
	int screenToTileX(int x) {
		return x / getTileWidth();
	}

	int screenToTileY(int y) {
		return y / getTileHeight();
	}

	int getTileWidth() {
		return getWidth() / getMap().getWidth();
	}

	int getTileHeight() {
		return getHeight() / getMap().getHeight();
	}

	/**
	 * Create the GUI and show it. For thread safety, this method should be
	 * invoked from the event-dispatching thread.
	 */
	private static void createAndShowGUI() {
		// Create and set up the window.
		JFrame frame = new JFrame("HelloWorldSwing");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


		Map map = new Map(9, 9);

		GamePlay level = new GamePlay(map, 1000);

		MapPanel label = new MapPanel() {
			public void coordinatesClicked(int x, int y) {
				
			}
		};
		
		label.setMap(new Map(10, 10));
		frame.getContentPane().add(label);

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

	@Override
	public void update(Observable o, Object arg) {
		repaint();
	}
	
	public void setMap(Map map) {
		if (this.map != null) {
			this.map.deleteObserver(this);
		}
		this.map = map;
		map.addObserver(this);
		repaint();
	}
}
