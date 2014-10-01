package ca.concordia.soen6441.logic;

import ca.concordia.soen6441.logic.primitives.Coordinate;
import ca.concordia.soen6441.logic.primitives.Damage;


public class Tower {
	
	 
	// ths is shown in the tower inspection window

	int damage;
	
	int buyCost;
	
	/**
	 * Percentage of refund rate
	 */
	int refundRate;
	
	int range;

	// end of what is shown in the tower inspection window
	
	final Coordinate coordinate;
	
	public int getX() {
		return coordinate.getX();
	}
	
	public int getY() {
		return coordinate.getX();
	}

	public int getRange() {
		return range;
	}

	public Tower(Coordinate coordinate, int damage) {
		super();
		this.coordinate = coordinate;
		this.damage = damage;
	}

	public Coordinate getCoordinate() {
		return coordinate;
	}

	public void update(long milisecondsFromLastUpdate) {
//		shotMilisecondsAgo += milisecondsFromLastUpdate;
	}
	
//	public boolean inRange(Coordinate otherCoordinate) {
////		return distance(this.coordinate, otherCoordinate) < rangeRadius;
//	}
	
	public double distanceTo(Coordinate otherCoordinate) {
		return distance(this.coordinate, otherCoordinate);
	}
	
	public double distance(Coordinate p0, Coordinate p1) {
		return 0; //new Point2d(this.coordinate).distance(new Point2d(coordinate));
	}

	/**
	 * Returns the damage done by the tower
	 * 
	 * @return the damage done by the tower
	 */
	public int getDamage() {
		return damage;
	}

	public int getBuyCost() {
		return buyCost;
	}

	public int getRefundRate() {
		return refundRate;
	}
	
	public int getUpgradeCost() {
		return 100;
	}
	
	/**
	 * Returns true if the tower can be upgraded, false if not
	 * 
	 * @return true if the tower can be upgraded, false if not
	 */
	public boolean canUpgrade() {
		return true;
	}
	
	public boolean doUpgrade() {
		return true;
	}
	
	public int getLevel() {
		return 0;
	}
	
	
}
