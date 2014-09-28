package ca.concordia.soen6441.logic;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ca.concordia.soen6441.logic.Tile.TileType;
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
		this.grid = new Tile[width][height];

		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				grid[x][y] = new Tile(Tile.TileType.TOWER_FREE_SLOT);
			}
		}
	}

	public void set(Tile tile, Coordinate coordinate) {
		grid[coordinate.getX()][coordinate.getY()] = tile;

	}

	public Tile getTile(int x, int y) {
		return grid[x][y];
	}

	public boolean canPlace(Tower tower, int x, int y) {
		Tile tile = grid[x][y];
		return tile.getType() == Tile.TileType.TOWER_FREE_SLOT;
	}

	public boolean outOfBounds(Coordinate coordinate) {
		return coordinate.getX() < 0 || coordinate.getX() > width
				|| coordinate.getY() < 0 || coordinate.getY() > height;
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

	public void setTile(int x, int y, TileType tileType) {
		grid[x][y] = new Tile(tileType);

	}

}
