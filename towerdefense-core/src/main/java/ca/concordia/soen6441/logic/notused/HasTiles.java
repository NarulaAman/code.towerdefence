package ca.concordia.soen6441.logic.notused;

import ca.concordia.soen6441.logic.Tile;

public interface HasTiles {
	Tile tileOn(int x, int y);
	
	void setTile(int x, int y, Tile.TileType type);

}
