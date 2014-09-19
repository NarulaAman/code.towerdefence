package ca.concordia.soen6441.logic;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ca.concordia.soen6441.logic.primitives.Coordinate;

public class Map implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	final Tile grid[][];
	
	final int width;
	final int height;
	
	Coordinate startTile;
	Coordinate endTile;
	
	
	// REMOVE THIS HACK
	
	public List<Coordinate> pathCoordinates = new ArrayList<Coordinate>();
	

	public Map(int width, int height) {
		super();
		this.width = width;
		this.height = height;
		this.grid = new Tile[width][];
		
		for (int i = 0; i < grid.length; ++i) {
			grid[i] = new Tile[height];
		}
	}
	
	public void add(Tile tile, Coordinate coordinate) {
		if (tile.getType() == Tile.TileType.ENEMY_PATH)
		{
			pathCoordinates.add(coordinate);
		}
		grid[coordinate.getX()][coordinate.getY()] = tile;
		
	}
	
	public boolean hasTile(Coordinate coordinate) {
		
		return grid[coordinate.getX()][coordinate.getY()] != null;
	}
	
	
	public Tile getTile(int x, int y) {
		return grid[x][y];
	}
	
//	public boolean canPlace(Tower tower, Coordinate coordinate) {
//		Tile tile = grid[coordinate.getX()][coordinate.getY()];
//		return tile == null ? false :  
//	}
	
	public boolean outOfBounds(Coordinate coordinate) {
		return coordinate.getX() < 0 || coordinate.getX() > width ||
			coordinate.getY() < 0 || coordinate.getY() > height;
	}
	
	public void remove(Coordinate coordinate) {
		grid[coordinate.getX()][coordinate.getY()] = null;
	}
	
	public boolean isValid() {
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(grid);
		result = prime * result + height;
		result = prime * result + width;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Map other = (Map) obj;
		if (height != other.height)
			return false;
		if (width != other.width)
			return false;
		if (!Arrays.deepEquals(grid, other.grid))
			return false;
		return true;
	}

	public Coordinate getStartTile() {
		return startTile;
	}

	public void setStartTile(Coordinate startTile) {
		this.startTile = startTile;
	}

	public Coordinate getEndTile() {
		return endTile;
	}

	public void setEndTile(Coordinate endTile) {
		this.endTile = endTile;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	
	
}
