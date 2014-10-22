package ca.concordia.soen6441.logic;

import java.util.Observable;

import javax.vecmath.Point2d;

import ca.concordia.soen6441.logic.primitives.IntCoordinate;


public class Tower extends Observable {
	
	
	int level;
	
	final IntCoordinate coordinate;
	
	final TowerFactory towerFactory;

	public int getX() {
		return coordinate.getX();
	}
	
	public int getY() {
		return coordinate.getX();
	}

	public int getRange() {
		return getTowerLevelCharacteristic().getRange();
	}

	public Tower(int level, IntCoordinate coordinate, TowerFactory towerFactory) {
		super();
		this.coordinate = coordinate;
		this.level = level;
		this.towerFactory = towerFactory;
	}

	public IntCoordinate getCoordinate() {
		return coordinate;
	}

	public void update(long milisecondsFromLastUpdate) {
//		shotMilisecondsAgo += milisecondsFromLastUpdate;
	}
	
	public boolean inRange(IntCoordinate otherCoordinate) {
		return distance(this.coordinate, otherCoordinate) < getRange();
	}
	
	public double distanceTo(IntCoordinate otherCoordinate) {
		return distance(this.coordinate, otherCoordinate);
	}
	
	public double distance(IntCoordinate p0, IntCoordinate p1) {
		return new Point2d(p0.getX(), p0.getY()).distance(new Point2d(p1.getX(), p1.getY()));
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
		return getTowerFactory().getLevelInformation(this.getClass(), level+1).getBuyCost();
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
		setChanged();
		notifyObservers();
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
