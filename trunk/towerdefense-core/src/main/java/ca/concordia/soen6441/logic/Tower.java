package ca.concordia.soen6441.logic;

import ca.concordia.soen6441.logic.primitives.Coordinate;
import ca.concordia.soen6441.logic.primitives.Damage;


public class Tower {
	
	 
	final Coordinate coordinate;
	
	final Damage damage;
	
	final long shootEveryMiliseconds = 500;
	long shotMilisecondsAgo = Long.MAX_VALUE / 2;
	
	final double rangeRadius = 3f;

	public Tower(Coordinate coordinate, Damage damage) {
		super();
		this.coordinate = coordinate;
		this.damage = damage;
	}

	public Coordinate getCoordinate() {
		return coordinate;
	}

	public void update(long milisecondsFromLastUpdate) {
		shotMilisecondsAgo += milisecondsFromLastUpdate;
	}
	
	public boolean inRange(Coordinate otherCoordinate) {
		return distance(this.coordinate, otherCoordinate) < rangeRadius;
	}
	
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
	public Damage getDamage() {
		return damage;
	}

	public boolean canShoot() {
		return shotMilisecondsAgo > shootEveryMiliseconds;
	}

	public double getRangeRadius() {
		return rangeRadius;
	}

	public void shoot(Enemy enemy) {
		shotMilisecondsAgo = 0;
		enemy.hitWith(getDamage());
	}
	
	
	
	
	
	
}
