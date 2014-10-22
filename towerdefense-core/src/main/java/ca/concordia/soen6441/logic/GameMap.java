package ca.concordia.soen6441.logic;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Observable;

import ca.concordia.soen6441.logic.Tile.TileType;
import ca.concordia.soen6441.logic.primitives.GridPosition;

/**
 * This class represents the Tower Defense playable GameMap. 
 */
public class GameMap extends Observable implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	final Tile grid[][];

	final int width;
	final int height;

	private GridPosition startGridPosition;
	private GridPosition endGridPosition;
	
	// REMOVE THIS HACK

	public List<GridPosition> pathCoordinates = new ArrayList<GridPosition>();

	public GameMap(int width, int height) {
		super();
		this.width = width;
		this.height = height;
		this.grid = new Tile[width][height];

		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				grid[x][y] = new Tile(Tile.TileType.SCENERY);
			}
		}
	}

	public void set(Tile tile, GridPosition coordinate) {
		grid[coordinate.getX()][coordinate.getY()] = tile;
// TODO: remove this method

	}

	public Tile getTile(int x, int y) {
		return grid[x][y];
	}

	public boolean canPlace(Tower tower) {
		if (hasStartTile()) {
			if (tower.getGridPosition().equals(getStartGridPosition())) {
				return false;
			}
		}
		if (hasEndTile()) {
			if (tower.getGridPosition().equals(getEndGridPosition())) {
				return false;
			}
		}
		int x = tower.getGridPosition().getX();
		int y = tower.getGridPosition().getY();
		Tile tile = grid[x][y];
		return tile.getType() == Tile.TileType.SCENERY;
	}

	public boolean outOfBounds(GridPosition coordinate) {
		return coordinate.getX() < 0 || coordinate.getX() > width
				|| coordinate.getY() < 0 || coordinate.getY() > height;
	}

	public void remove(GridPosition coordinate) {
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
		GameMap other = (GameMap) obj;
		if (height != other.height)
			return false;
		if (width != other.width)
			return false;
		if (!Arrays.deepEquals(grid, other.grid))
			return false;
		return true;
	}

	public GridPosition getStartGridPosition() {
		return startGridPosition;
	}

	public void setStartGridPosition(GridPosition startTile) {
		this.startGridPosition = startTile;
		setChanged();
		notifyObservers();
	}

	public GridPosition getEndGridPosition() {
		return endGridPosition;
	}
	
	public void setEndGridPosition(GridPosition endTile) {
		this.endGridPosition = endTile;
		setChanged();
		notifyObservers();
	}
	

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public void setTile(GridPosition gridPosition, TileType tileType) {
		
		if (grid[gridPosition.getX()][gridPosition.getY()].type != tileType) {
			grid[gridPosition.getX()][gridPosition.getY()] = new Tile(tileType);
			
			setChanged();
			notifyObservers();
		}
	}
	
	public boolean hasStartTile() {
		return startGridPosition != null;
	}
	
	public boolean hasEndTile() {
		return endGridPosition != null;
	}
}
