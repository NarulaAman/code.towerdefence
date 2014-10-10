package towerdefense.gui;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

import ca.concordia.soen6441.logic.Map;
import ca.concordia.soen6441.logic.Tile;
import ca.concordia.soen6441.logic.primitives.Coordinate;


public abstract class GridPanel extends JPanel implements Observer, MouseListener {

	/**
	 * 
	 */
	
	private static final Icon ENEMY_PATH_ICON = new ImageIcon("tilepath.jpg");
	private static final Icon SCENERY_ICON = new ImageIcon("grass.jpg");
	private static final Icon START_ICON = new ImageIcon("tilepath-start.jpg");
	private static final Icon EXIT_ICON = new ImageIcon("tilepath-exit.jpg");
	
	
	private static final long serialVersionUID = 1L;
	private GridTiles[][] gridTiles;
	
	//private boolean mouseEnterKill = true;
	
	Map map;
	
	public GridPanel(Map map)
	{
		this.map = map;
		map.addObserver(this);
		setPreferredSize(new Dimension(480, 640));
		setVisible(true);
	        
		GridLayout grid = new GridLayout(map.getWidth(), map.getHeight(),1,1);
		setLayout(grid);
		
		gridTiles = new GridTiles[map.getWidth()][map.getHeight()];
	        for(int x = 0; x < map.getWidth(); x++)
	        {
	        	for(int y = 0; y < map.getHeight(); y++)
	        	{
	        		gridTiles[x][y] = new GridTiles();
	        		gridTiles[x][y].setOpaque(true);
	        		gridTiles[x][y].setText(null);
	        		gridTiles[x][y].setTileCoordinate(x, y);
	        		gridTiles[x][y].addMouseListener(this);
	        		add(gridTiles[x][y]);
	        	}
	        }
	        
		
		update(null, null);
		
	}

	
	
	abstract public void coordinatesClicked(int x, int y);

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		GridTiles source = (GridTiles)e.getSource();

		int x = source.getTileXCoordinate();
		int y = source.getTileYCoordinate();
		
		coordinatesClicked(x, y);
//		System.out.println("Grid was clicked on [ X: "+x+" ][ Y: "+y+ " ] with event: " + e.toString());
//		
//		if(ButtonPanel.path){
//		map.setTile(x, y, Tile.TileType.ENEMY_PATH);
//
//		
//        source.setIcon(ENEMY_PATH_ICON);}
//		
//		if(ButtonPanel.scenery)
//		{
//			map.setTile(x, y, Tile.TileType.TOWER_FREE_SLOT);
//
//			
//	        source.setIcon(SCENERY_ICON);
//		}

        //mouseEnterKill = false;
        
   
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
//		GridTiles source = (GridTiles)e.getSource();
//	    //System.out.print("X : "+e.   );
//		System.out.print(source.getTileXCoordinate());
//		System.out.print(source.getTileYCoordinate());
//		//System.out.print(source.getTileCoordinate().getY());
//       // source.setBackground(Color.cyan);	
//        source.setIcon(new ImageIcon("imageicon1.jpg"));
        
		//System.out.println(source.getText());
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
//		GridTiles source = (GridTiles)e.getSource();
//	    //System.out.print("X : "+e.   );
//		System.out.print(source.getTileXCoordinate());
//		System.out.print(source.getTileYCoordinate());
//		//System.out.print(source.getTileCoordinate().getY());
//       // source.setBackground(Color.cyan);	
//        source.setIcon(new ImageIcon("imageicon1.jpg"));
        
		//System.out.println(source.getText());
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
//		if(mouseEnterKill){
//		GridTiles source = (GridTiles)e.getSource();
//	    //System.out.print("X : "+e.   );
//		System.out.print(source.getTileXCoordinate());
//		System.out.print(source.getTileYCoordinate());
//		//System.out.print(source.getTileCoordinate().getY());
//       // source.setBackground(Color.cyan);	
//        source.setIcon(new ImageIcon("imageicon1.jpg"));
//	}
        
		//System.out.println(source.getText());
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
		
	}

	@Override
	public void update(Observable o, Object arg) {
        for(int x = 0; x < map.getWidth(); x++)
        {
        	for(int y = 0; y < map.getHeight(); y++)
        	{
        		Tile tile = map.getTile(x, y);
        		if (tile.getType() == Tile.TileType.ENEMY_PATH)
        		{
        			gridTiles[x][y].setIcon(ENEMY_PATH_ICON);
        		}
        		else if (tile.getType() == Tile.TileType.SCENERY) 
        		{
        			gridTiles[x][y].setIcon(SCENERY_ICON);
        		}
        		else
        		{
        			gridTiles[x][y].setForeground(Color.BLACK);
        		}
        	}
        }
        
        if (map.hasStartTile()) {
        	Coordinate start = map.getStartTile();
        	gridTiles[start.getX()][start.getY()].setIcon(START_ICON);
        }
		
        if (map.hasEndTile()) {
        	Coordinate start = map.getEndTile();
        	gridTiles[start.getX()][start.getY()].setIcon(EXIT_ICON);
        }
	}

	
	
}
