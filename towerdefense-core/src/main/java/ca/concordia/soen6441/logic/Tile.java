package ca.concordia.soen6441.logic;

import java.io.Serializable;

/**
 * This class represents a {@link Tile} of the {@link GameMap}
 */
public class Tile implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7952947752607673719L;
	
	public static final Tile ENEMY_PATH = new Tile(TileType.ENEMY_PATH);
	public static final Tile SCENERY = new Tile(TileType.SCENERY);

	/**
	 * Types of {@link Tile} that a {@link GameMap} can have
	 *
	 */
	private enum TileType {
		ENEMY_PATH, SCENERY
	}

	final TileType type;

	/**
	 * Builds a tile for a given type
	 * 
	 * @param type
	 *            type of the tile to be created
	 */
	protected Tile(TileType type) {
		super();
		this.type = type;
	}

	/**
	 * Returns the type of the tile
	 * 
	 * @return the type of the tile
	 */
	public TileType getType() {
		return type;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tile other = (Tile) obj;
		if (type != other.type)
			return false;
		return true;
	}

}
