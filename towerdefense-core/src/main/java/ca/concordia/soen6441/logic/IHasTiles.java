package ca.concordia.soen6441.logic;

public interface IHasTiles {
	Tile tileOn(int x, int y);
	
	void setTile(int x, int y, Tile.TileType type);

}
