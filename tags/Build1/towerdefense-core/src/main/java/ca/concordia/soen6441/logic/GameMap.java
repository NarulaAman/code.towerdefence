package ca.concordia.soen6441.logic;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Observable;

import ca.concordia.soen6441.logic.primitives.GridPosition;

/**
 * This class represents the Tower Defense playable GameMap. 
 */
public class GameMap extends Observable implements Serializable, Cloneable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 7943086470924125701L;

	final Tile grid[][];

	/**
	 * Number of tiles in X-Coordinate
	 */
	final int width;
	/**
	 * Number of tiles in Y-Coordinate
	 */
	final int height;
	
	
	private String name = "Map";

	/**
	 * The position of Entry Tile
	 */
	private GridPosition startGridPosition;
	/**
	 * The position of Exit Tile
	 */
	private GridPosition endGridPosition;

	/**
	 * Display the {@link GameMap} with scenery at all the {@link Tile}
	 * @param width Number of tiles in X-Coordinate
	 * @param height Number of tiles in Y-Coordinate
	 */
	public GameMap(int width, int height) {
		super();
		this.width = width;
		this.height = height;
		this.grid = new Tile[width][height];

		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				grid[x][y] = Tile.SCENERY;
			}
		}
	}

	/**
	 * Get the {@link Tile} type at X and Y coordinate on {@link GameMap}
	 * @param gridPosition {@link GridPosition}
	 * @return Tile type at the {@link GridPosition} on Map
	 */
	public Tile getTile(GridPosition gridPosition) {
		return grid[gridPosition.getX()][gridPosition.getY()];
	}

	
	/**
	 * Validate the position of the {@link Tile} should within the {@link GameMap}
	 * @param coordinate The X and Y coordinate on {@link GameMap}
	 * @return True or False if the position of the tile is within the {@link GameMap} or not
	 */
	public boolean outOfBounds(GridPosition coordinate) {
		return coordinate.getX() < 0 || coordinate.getX() > width
				|| coordinate.getY() < 0 || coordinate.getY() > height;
	}

	/**
	 * Remove the tile type assigned to {@link Tile} on the {@link GameMap}
	 * @param coordinate The X and Y coordinate on @link GameMap
	 */
	public void remove(GridPosition coordinate) {
		grid[coordinate.getX()][coordinate.getY()] = null;
	}

	/**
	 * Returns the coordinates of Entry {@link Tile}
	 * @return The X and Y coordinate of Entry {@link Tile} on {@link GameMap}
	 */
	public GridPosition getStartGridPosition() {
		return startGridPosition;
	}
	/**
	 * Set the position of Entry Tile on {@link GameMap}
	 * @param startTile The X and Y coordinate of Entry {@link Tile} on {@link GameMap}
	 */
	public void setStartGridPosition(GridPosition startTile) {
		this.startGridPosition = startTile;
		setChanged();
		notifyObservers();
	}

	/**
	 * Returns the coordinates of Exit {@link Tile}
	 * @return The X and Y coordinate of Exit {@link Tile} on {@link GameMap}
	 */
	public GridPosition getEndGridPosition() {
		return endGridPosition;
	}
	
	/**
	 * Set the position of Exit {@link Tile} on {@link GameMap}
	 * @param endTile The X and Y coordinate of Exit {@link Tile} on {@link GameMap}
	 */
	public void setEndGridPosition(GridPosition endTile) {
		this.endGridPosition = endTile;
		setChanged();
		notifyObservers();
	}
	

	/**
	 * Returns the width of this {@link GameMap}
	 * @return the width of this {@link GameMap}
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * Returns the height of this {@link GameMap}
	 * @return the height of this {@link GameMap}
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * Set the {@link Tile} on the {@link GameMap} to user selected tile type 
	 * @param gridPosition The X and Y coordinate on {@link GameMap}
	 * @param tile The type of {@link Tile}
	 */
	public void setTile(GridPosition gridPosition, Tile tile) {
		
		if (grid[gridPosition.getX()][gridPosition.getY()] != tile) {
			grid[gridPosition.getX()][gridPosition.getY()] = tile;
			setChanged();
			notifyObservers();
		}
	}
	
	/**
	 * Validate the presence of Entry {@link Tile} on {@link GameMap}
	 * @return True or False if the Entry {@link Tile} is present or not
	 */
	public boolean hasStartTile() {
		return startGridPosition != null;
	}
	
	/**
	 * Validate the presence of Exit {@link Tile} on {@link GameMap}
	 * @return True or False if the Exit {@link Tile} is present or not
	 */
	public boolean hasEndTile() {
		return endGridPosition != null;
	}
	
	
	/**
	 * Returns a copy of this {@link GameMap}
	 * @return a copy of this {@link GameMap}
	 */
	public GameMap clone() {
		GameMap clone = new GameMap(getWidth(), getWidth());
		clone.setStartGridPosition(getStartGridPosition());
		clone.setEndGridPosition(getEndGridPosition());
		clone.setName(getName());
		for (int x = 0; x < getWidth(); ++x) {
			clone.grid[x] = Arrays.copyOf(grid[x], grid[x].length);
		}
		return clone;
	}
	
	/**
	 * Get the list of {@link Tile} positions where Enemy can walk
	 * @param gridPosition The X and Y coordinate on {@link GameMap}
	 * @return The list of {@link Tile} positions where Enemy can walk on {@link GameMap}
	 */
	public List<GridPosition> getAdjacentWalkablePositions(GridPosition gridPosition) {
		int xLeft = gridPosition.getX() - 1;
		int xRight = gridPosition.getX() + 1;
		int yBottom = gridPosition.getY() - 1;
		int yTop = gridPosition.getY() + 1;
		
		List<GridPosition> result = new ArrayList<>();
		if (xLeft >= 0 ) {
			GridPosition adjacent = new GridPosition(xLeft, gridPosition.getY());
			if (isEnemyWalkable(adjacent)) {
				result.add(adjacent);
			}
		}
		if (xRight < getWidth()) {
			GridPosition adjacent = new GridPosition(xRight, gridPosition.getY());
			if (isEnemyWalkable(adjacent)) {
				result.add(adjacent);
			}
		}
		if (yBottom >= 0) {
			GridPosition adjacent = new GridPosition(gridPosition.getX(), yBottom);
			if (isEnemyWalkable(adjacent)) {
				result.add(adjacent);
			}
		}
		if (yTop < getHeight()) {
			GridPosition adjacent = new GridPosition(gridPosition.getX(), yTop);
			if (isEnemyWalkable(adjacent)) {
				result.add(adjacent);
			}
		}
		return result;
	}
	
	
	/**
	 * Validate if the {@link Tile} type is Enemy Path 
	 * @param gridPosition The X and Y coordinate on {@link GameMap}
	 * @return True if the  {@link Tile} type at given position is Enemy Path
	 */
	public boolean isEnemyWalkable(GridPosition gridPosition) {
		return grid[gridPosition.getX()][gridPosition.getY()] == Tile.ENEMY_PATH;
	}

	/**
	 * Returns a unique hashcode of this {@link GameMap}
	 * @return hashcode calculated for this {@link GameMap}
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(grid);
		result = prime * result + height;
		result = prime * result + width;
		return result;
	}

	/**
	 * Returns true if this object is the same as the other, false if not
	 * @return true if this object is the same as the other, false if not
	 */
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

	/**
	 * Returns the name of the Map
	 * @return  the name of the Map
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name of the map
	 * @param name name to be given for the map
	 */
	public void setName(String name) {
		this.name = name;
	}
}
