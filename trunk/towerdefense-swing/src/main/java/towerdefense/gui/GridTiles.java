package towerdefense.gui;


import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JButton;

public class GridTiles extends JButton{
	
	
	
	private static final long serialVersionUID = 1L;
	private Color pressedTileColor;
	private GridCoordinates tileCoordinate;
	
	
	public GridTiles()
	{
		this(null);
		this.setBackground(Color.red);
		tileCoordinate = new GridCoordinates();
	}

	public GridTiles(String tile_text)
	{
		super(tile_text);
		this.setBackground(Color.red);
		
	}
	
	public void setTileCoordinate(int x,int y)
	{
		tileCoordinate.setX(x);
		tileCoordinate.setY(y);
	}
	
	public int getTileXCoordinate()
	{
		return tileCoordinate.getX();
	}
	
	public int getTileYCoordinate()
	{
		return tileCoordinate.getY();
	}
//	 @Override
//     protected void paintComponent(Graphics g) {
//		 super.paintComponent(g);
//		 System.out.print("in gridtiles paint");
//         if (getModel().isPressed()) {
//        	 System.out.print(tileCoordinate.getX());
//             g.setColor(Color.cyan);
//         } 
//         else {
//             g.setColor(Color.red);
//         }
//         drawButton(g);
//         
//     }
	 
	 public void setPressedTileColor(Color color)
	 {
		 this.pressedTileColor = color;
	 }
	 
	 public Color getPressedTileColor() {
         return pressedTileColor;
     }
	 
	 public void drawButton(Graphics g)
	 {
		 
	 }
}
