import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

import ca.concordia.soen6441.logic.Level;
import ca.concordia.soen6441.logic.Map;
import ca.concordia.soen6441.logic.Tile;
import ca.concordia.soen6441.logic.primitives.Coordinate;

public class LevelRenderer extends JPanel {

	private static final int TileSize = 10;
	final Level level;

	public LevelRenderer(Level level) {
		super();
		setVisible(true);
		this.level = level;
		setPreferredSize(new Dimension(640, 480));
	}

	@Override
	public void paint(Graphics g) {
		Map map = level.getMap();
		for (int x = 0; x < map.getWidth(); ++x) {
			for (int y = 0; y < map.getHeight(); ++y) {
				Tile tile = map.getTile(x, y);
				if (tile == null) {
					g.setColor(Color.BLACK);
					
				}
				else if (tile.getType() == Tile.TileType.ENEMY_PATH) {
					g.setColor(Color.RED);
				}
				g.drawRect(x*TileSize , y*TileSize, TileSize, TileSize);
			}
		}

	}
	
	
	  /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("HelloWorldSwing");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
        //Add the ubiquitous "Hello World" label.
        
    	Map map = new Map(9, 9);
		Tile path = new Tile(Tile.TileType.ENEMY_PATH);
		Tile freeSpace = new Tile(Tile.TileType.TOWER_FREE_SLOT);
		for (int x = 0; x < map.getWidth(); ++x) {
//			if (map.outOfBounds(new Coordinate(x, x)) == false)
//			{
				map.add(path, new Coordinate(x, x));
//			}
//			if (map.outOfBounds(new Coordinate(x+1, x)) == false)
//			{
//				map.add(path, new Coordinate(x+1, x));
//			}
		}
		
		
//		for (int x = 0; x < map.getWidth(); ++x) {
//			for (int y = 0; y < map.getHeight(); ++y) {
//				if (map.outOfBounds(new Coordinate(x, y)) == false)
//				{
//					if (map.hasTile(new Coordinate(x, y)))
//					{
//						map.add(freeSpace, new Coordinate(x, y));
//					}
//				}
//			}
//		}
		
		Level level = new Level(map);
        
        
        JPanel label = new LevelRenderer(level);
        frame.getContentPane().add(label);
 
        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }
 
    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}
