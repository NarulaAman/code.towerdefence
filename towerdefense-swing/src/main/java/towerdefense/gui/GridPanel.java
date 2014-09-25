package towerdefense.gui;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import ca.concordia.soen6441.logic.Map;
import ca.concordia.soen6441.logic.Tile;
import ca.concordia.soen6441.logic.Tile.TileType;


public class GridPanel extends JPanel implements MouseListener {

	/**
	 * 
	 */
	
	private static Color ENEMY_PATH_COLOR = Color.RED;
	private static Color SCENERY_COLOR = Color.green;
	private static final long serialVersionUID = 1L;
	private GridTiles[][] gridTiles;
	private boolean mouseEnterKill = true;
	
	Map map;
	
	public GridPanel(Map map)
	{
		this.map = map;
		setPreferredSize(new Dimension(480, 640));
		setVisible(true);
//		setLayout(new GridLayout(8,8));
//	    gridTiles = new GridTiles[8][8];
	        
		GridLayout grid = new GridLayout(map.getWidth(), map.getHeight());
		setLayout(grid);
		
		gridTiles = new GridTiles[map.getWidth()][map.getHeight()];
	        for(int x=0;x< gridTiles.length; x++ )
	        {
	        	for(int y=0;y<gridTiles.length;y++)
	        	{
	        		gridTiles[x][y] = new GridTiles();
	        		gridTiles[x][y].setOpaque(true);
	        		gridTiles[x][y].setText(null);
	        		gridTiles[x][y].setTileCoordinate(x, y);
	        		Tile tile = map.getTile(x, y);
	        		if (tile.getType() == Tile.TileType.ENEMY_PATH)
	        		{
	        			gridTiles[x][y].setForeground(ENEMY_PATH_COLOR);
	        		}
	        		else if (tile.getType() == Tile.TileType.TOWER_FREE_SLOT) 
	        		{
	        			gridTiles[x][y].setBackground(SCENERY_COLOR);
	        		}
	        		else
	        		{
	        			gridTiles[x][y].setForeground(Color.BLACK);
	        		}
	        		
	        		//gridTiles[x][y].addActionListener(this);
	        		
	        		
	        		
	        		
	        		gridTiles[x][y].addMouseListener(this);
	        		add(gridTiles[x][y]);
	        	}
	        }
	        
		
		
		
	}


	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		GridTiles source = (GridTiles)e.getSource();
	    //System.out.print("X : "+e.   );
		int x = source.getTileXCoordinate();
		System.out.print(x);
		int y = source.getTileYCoordinate();
		System.out.print(y);
		
		map.setTile(x, y, Tile.TileType.ENEMY_PATH);
		//System.out.print(source.getTileCoordinate().getY());
       source.setBackground(ENEMY_PATH_COLOR);	
        //source.setIcon(new ImageIcon("imageicon1.jpg"));
        mouseEnterKill = false;
        
        
		//System.out.println(source.getText());
		
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
		if(mouseEnterKill){
		GridTiles source = (GridTiles)e.getSource();
	    //System.out.print("X : "+e.   );
		System.out.print(source.getTileXCoordinate());
		System.out.print(source.getTileYCoordinate());
		//System.out.print(source.getTileCoordinate().getY());
       // source.setBackground(Color.cyan);	
        source.setIcon(new ImageIcon("imageicon1.jpg"));
	}
        
		//System.out.println(source.getText());
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
		
	}

	

	
	
}
