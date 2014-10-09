package ca.concordia.soen6441.logic;

import java.io.Serializable;

public class Tile implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public enum TileType {
		ENEMY_PATH,
		SCENERY
	}
	
	final TileType type;
	
	public Tile(TileType type) {
		super();
		this.type = type;
	}
	
	public TileType getType() {
		return type;
	}




	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		Tile other = (Tile) obj;
		if (type != other.type)
			return false;
		return true;
	}	

}
