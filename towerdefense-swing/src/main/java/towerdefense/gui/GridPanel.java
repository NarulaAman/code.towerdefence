package towerdefense.gui;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

import ca.concordia.soen6441.logic.Map;
import ca.concordia.soen6441.logic.Tile;


public class GridPanel extends JPanel implements MouseListener {

	/**
	 * 
	 */
	
	private static final Icon ENEMY_PATH_ICON = new ImageIcon("grass.jpg");
	private static final Icon SCENERY_ICON = new ImageIcon("tilepath.jpg");
	
	private static final long serialVersionUID = 1L;
	private GridTiles[][] gridTiles;
	
	private boolean mouseEnterKill = true;
	
	Map map;
	
	public GridPanel(Map map)
	{
		this.map = map;
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
	        		Tile tile = map.getTile(x, y);
	        		if (tile.getType() == Tile.TileType.ENEMY_PATH)
	        		{
	        			gridTiles[x][y].setIcon(SCENERY_ICON);
	        		}
	        		else if (tile.getType() == Tile.TileType.TOWER_FREE_SLOT) 
	        		{
	        			gridTiles[x][y].setIcon(ENEMY_PATH_ICON);
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

		int x = source.getTileXCoordinate();
		int y = source.getTileYCoordinate();
		System.out.println("Grid was clicked on [ X: "+x+" ][ Y: "+y+ " ] with event: " + e.toString());
		
		
		map.setTile(x, y, Tile.TileType.ENEMY_PATH);

		
        source.setIcon(SCENERY_ICON);

        mouseEnterKill = false;
        
   
		
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

	

	
	
}
