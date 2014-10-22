package ca.concordia.soen6441.logic;

import java.util.Observable;

import javax.vecmath.Point2d;

import ca.concordia.soen6441.logic.primitives.GridPosition;


public class Tower extends Observable {
	
	
	int level;
	
	final GridPosition gridPosition;
	
	final TowerFactory towerFactory;

	public Tower(int level, GridPosition gridPosition, TowerFactory towerFactory) {
		super();
		this.gridPosition = gridPosition;
		this.level = level;
		this.towerFactory = towerFactory;
	}

	public GridPosition getGridPosition() {
		return gridPosition;
	}


	
	public int getRange() {
		return getTowerLevelCharacteristic().getRange();
	}
	
	public boolean inRange(GridPosition otherCoordinate) {
		return distance(this.gridPosition, otherCoordinate) < getRange();
	}
	
	public double distanceTo(GridPosition otherCoordinate) {
		return distance(this.gridPosition, otherCoordinate);
	}
	
	public double distance(GridPosition p0, GridPosition p1) {
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
	
	public void update(long milisecondsFromLastUpdate) {
//		shotMilisecondsAgo += milisecondsFromLastUpdate;
	}
	
}
