package ca.concordia.soen6441.logic;

import java.util.Observable;

import javax.vecmath.Point2d;

import ca.concordia.soen6441.logic.primitives.Coordinate;


public class Tower extends Observable {
	
	 
	// ths is shown in the tower inspection window

//	int damage;
//	
//	int buyCost;
//	
//	/**
//	 * Percentage of refund rate
//	 */
//	int refundRate;
//	
//	int range;
//	
//	int level;
	
	int level;
	
	final Coordinate coordinate;
	
	final TowerFactory towerFactory;
	

	// end of what is shown in the tower inspection window
	
	
	
	
	
	public int getX() {
		return coordinate.getX();
	}
	
	public int getY() {
		return coordinate.getX();
	}

	public int getRange() {
		return getTowerLevelCharacteristic().getRange();
	}

	public Tower(int level, Coordinate coordinate, TowerFactory towerFactory) {
		super();
		this.coordinate = coordinate;
		this.level = level;
		this.towerFactory = towerFactory;
	}

	public Coordinate getCoordinate() {
		return coordinate;
	}

	public void update(long milisecondsFromLastUpdate) {
//		shotMilisecondsAgo += milisecondsFromLastUpdate;
	}
	
	public boolean inRange(Coordinate otherCoordinate) {
		return distance(this.coordinate, otherCoordinate) < getRange();
	}
	
	public double distanceTo(Coordinate otherCoordinate) {
		return distance(this.coordinate, otherCoordinate);
	}
	
	public double distance(Coordinate p0, Coordinate p1) {
		return new Point2d(p0).distance(new Point2d(p1));
	}

	/**
	 * Returns the damage done by the tower
	 * 
	 * @return the damage done by the tower
	 */
	public int getDamage() {
		return getTowerLevelCharacteristic().getDamage();
	}

	public int getBuyCost() {
		return getTowerLevelCharacteristic().getBuyCost();
	}

	public int getRefundRate() {
		return getTowerLevelCharacteristic().getRefundRate();
	}
	
	public int getUpgradeCost() {
		return getTowerFactory().getLevelInformation(this.getClass(), level).getBuyCost();
	}
	
	/**
	 * Returns true if the tower can be upgraded, false if not
	 * 
	 * @return true if the tower can be upgraded, false if not
	 */
	public boolean canUpgrade() {
		return level < getTowerFactory().maxLevel(this.getClass());
	}
	
	public boolean doUpgrade() {
		level = level + 1;
		return true;
	}
	
	public int getLevel() {
		return level;
	}
	
	private TowerLevelCaracteristic getTowerLevelCharacteristic() {
		return getTowerFactory().getLevelInformation(this.getClass(), level);
	}
	
	private TowerFactory getTowerFactory() {
		return towerFactory;
	}
	
	
}
