package ca.concordia.soen6441.logic;

import java.io.Serializable;

/**
 * This class represents a {@link Tile} of the {@link GameMap}
 */
public enum Tile implements Serializable {

	/**
	 * Possible tile types
	 */
	ENEMY_PATH,
	SCENERY;
}
