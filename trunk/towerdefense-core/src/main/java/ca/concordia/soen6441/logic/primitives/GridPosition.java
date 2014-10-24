package ca.concordia.soen6441.logic.primitives;

import java.io.Serializable;

import javax.vecmath.Point2d;

/**
 * This class represents and X and Y gridPosition formed by integers
 */
public class GridPosition implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	final int x;
	final int y;

	/**
	 * Creates a GridPosition with a given X and Y coordinates
	 * @param x X coordinate of the {@link GridPosition}
	 * @param y Y coordinate of the {@link GridPosition}
	 */
	public GridPosition(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Returns the X coordinate of this {@link GridPosition}
	 * @return the X coordinate of this {@link GridPosition}
	 */
	public int getX() {
		return x;
	}

	/**
	 * Returns the Y coordinate of this {@link GridPosition}
	 * @return the Y coordinate of this {@link GridPosition}
	 */
	public int getY() {
		return y;
	}
	
	/**
	 * Returns the distance between this {@link GridPosition} and a given second one passed by argument
	 * @param other other {@link GridPosition} to be measured the distance to 
	 * @return the distance between this {@link GridPosition} and a given second one passed by argument
	 */
	public double distance(GridPosition other) {
		return new Point2d(getX(), getY()).distance(new Point2d(other.getX(), other.getY()));
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}

	/* (non-Javadoc)
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
		GridPosition other = (GridPosition) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}



	
	
	
	
	
}
