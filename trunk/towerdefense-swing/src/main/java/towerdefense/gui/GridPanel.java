package towerdefense.gui;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;


public class GridPanel extends JPanel implements MouseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private GridTiles[][] gridTiles;
	private boolean mouseEnterKill = true;
	
	public GridPanel()
	{
		setPreferredSize(new Dimension(480, 640));
		setVisible(true);
		setLayout(new GridLayout(8,8));
	    gridTiles = new GridTiles[8][8];
	        
	        for(int x=0;x< gridTiles.length; x++ )
	        {
	        	for(int y=0;y<gridTiles.length;y++)
	        	{
	        		gridTiles[x][y] = new GridTiles();
	        		gridTiles[x][y].setText(null);
	        		gridTiles[x][y].setTileCoordinate(x, y);
	        		gridTiles[x][y].setForeground(new Color(3, 59, 90).brighter());
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
		System.out.print(source.getTileXCoordinate());
		System.out.print(source.getTileYCoordinate());
		//System.out.print(source.getTileCoordinate().getY());
       source.setBackground(Color.cyan);	
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
