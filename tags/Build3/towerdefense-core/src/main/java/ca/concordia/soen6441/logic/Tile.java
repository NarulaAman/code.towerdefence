package ca.concordia.soen6441.logic;

import java.io.Serializable;

/**
 * This class represents a {@link Tile} of the {@link GameMap}
 */
public enum Tile implements Serializable {

	/**
	 * Enemy path tile
	 */
	ENEMY_PATH,
	/**
	 * Scenery tile, can have towers on it
	 */
	SCENERY;
}
