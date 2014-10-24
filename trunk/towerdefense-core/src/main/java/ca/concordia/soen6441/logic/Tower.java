package ca.concordia.soen6441.logic;

import java.util.Observable;

import javax.vecmath.Point2d;

import ca.concordia.soen6441.logic.primitives.GridPosition;


/**
 * This class has various Characteristics and operations executed on Tower 
 *
 */
public class Tower extends Observable {
	
	
	int level;
	
	final GridPosition gridPosition;
	
	final TowerFactory towerFactory;

	/**
	 * Initialize the data members
	 * @param level The level of the game
	 * @param gridPosition The position of the {@link Tower} on {@link GameMap}
	 * @param towerFactory The object of {@link TowerFactory}
	 */
	public Tower(int level, GridPosition gridPosition, TowerFactory towerFactory) {
		super();
		this.gridPosition = gridPosition;
		this.level = level;
		this.towerFactory = towerFactory;
	}

	/**
	 * Return the position on {@link GameMap}
	 * @return The position on {@link GameMap}
	 */
	public GridPosition getGridPosition() {
		return gridPosition;
	}


	
	/**
	 * Return the range of {@link Tower}
	 * @return Range of {@link Tower}
	 */
	public int getRange() {
		return getTowerLevelCharacteristic().getRange();
	}
	
	/**
	 * Validate if position of enemy is within the range of tower 
	 * @param otherCoordinate The position of enemy on {@link GameMap}
	 * @return True if the position of enemy is within the range of tower else false
	 */
	public boolean inRange(GridPosition otherCoordinate) {
		return distance(this.gridPosition, otherCoordinate) < getRange();
	}
	
	/**
	 * Calculate the distance between Tower and Enemy
	 * @param otherCoordinate The position of enemy on {@link GameMap}
	 * @return The distance between Tower and Enemy
	 */
	public double distanceTo(GridPosition otherCoordinate) {
		return distance(this.gridPosition, otherCoordinate);
	}
	
	/**
	 * Calculate the distance between two positions
	 * @param p0 Position on {@link GameMap}
	 * @param p1 Position on {@link GameMap}
	 * @return The distance between the two positions
	 */
	public double distance(GridPosition p0, GridPosition p1) {
		return new Point2d(p0.getX(), p0.getY()).distance(new Point2d(p1.getX(), p1.getY()));
	}

	/**
	 * Returns the damage done by the tower
	 * 
	 * @return The damage done by the tower
	 */
	public int getDamage() {
		return getTowerLevelCharacteristic().getDamage();
	}

	/**
	 * Returns the cost of the tower
	 * @return The cost of the tower
	 */
	public int getBuyCost() {
		return getTowerLevelCharacteristic().getBuyCost();
	}

	/**
	 * Returns the Refund Rate for the Tower
	 * @return The Refund Rate for the Tower
	 */
	public int getRefundRate() {
		return getTowerLevelCharacteristic().getRefund();
	}
	
	/**
	 * Returns the upgraded cost of the Tower
	 * @return The upgraded cost of the Tower
	 */
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
	
	/**
	 * Upgrade the tower
	 * @return True if the tower is upgraded
	 */
	public boolean doUpgrade() {
		level = level + 1;
		setChanged();
		notifyObservers();
		return true;
	}
	
	/**
	 * Returns the level of the tower
	 * @return The level of the tower
	 */
	public int getLevel() {
		return level;
	}
	
	/**
	 * Returns the {@link TowerLevelCharacteristic}
	 * @return The {@link TowerLevelCharacteristic}
	 */
	private TowerLevelCharacteristic getTowerLevelCharacteristic() {
		return getTowerFactory().getLevelInformation(this.getClass(), level);
	}
	
	/**
	 * Returns the {@link TowerFactory}
	 * @return The {@link TowerFactory}
	 */
	private TowerFactory getTowerFactory() {
		return towerFactory;
	}
	
	/**
	 * @param milisecondsFromLastUpdate 
	 */
	public void update(long milisecondsFromLastUpdate) {
//		shotMilisecondsAgo += milisecondsFromLastUpdate;
	}
	
}
