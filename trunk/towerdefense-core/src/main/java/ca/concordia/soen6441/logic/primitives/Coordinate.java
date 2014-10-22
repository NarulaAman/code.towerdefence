package ca.concordia.soen6441.logic.primitives;

import java.io.Serializable;

import javax.vecmath.Vector2d;

/**
 * This class represents and X and Y coordinate formed by integers
 */
public class Coordinate /*extends Vector2d*/ implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	final int x;
	final int y;

	public Coordinate(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (getClass() != obj.getClass())
			return false;
		Coordinate other = (Coordinate) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}

	
	
	
	
	
}
