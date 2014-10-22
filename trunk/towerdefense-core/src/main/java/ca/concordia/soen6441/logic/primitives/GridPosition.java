package ca.concordia.soen6441.logic.primitives;

import java.io.Serializable;

import javax.vecmath.Point2d;
import javax.vecmath.Vector2d;

/**
 * This class represents and X and Y gridPosition formed by integers
 */
/**
 * @author discipline
 *
 */
public class GridPosition implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	final int x;
	final int y;

	public GridPosition(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	public double distance(GridPosition other) {
		return new Point2d(getX(), getY()).distance(new Point2d(other.getX(), other.getY()));
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
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
		GridPosition other = (GridPosition) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}



	
	
	
	
	
}
