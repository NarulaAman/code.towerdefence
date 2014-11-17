package ca.concordia.soen6441.logic.tower;

import java.util.List;
import java.util.Observable;

import javax.vecmath.Point2d;
import javax.vecmath.Point2f;

import ca.concordia.soen6441.logic.Enemy;
import ca.concordia.soen6441.logic.GameMap;
import ca.concordia.soen6441.logic.TowerFactory;
import ca.concordia.soen6441.logic.TowerLevelCharacteristic;
import ca.concordia.soen6441.logic.primitives.GridPosition;
import ca.concordia.soen6441.logic.tower.shootingstrategy.ShootingStrategy;


/**
 * This class has various Characteristics and operations executed on Tower 
 *
 */
public abstract class Tower extends Observable {
	
	/**
	 * Event for a tower shooting an enemy, it will be used in the user interface
	 * to show which enemy is the tower shooting at
	 */
	public class TowerShootEvent {
		public final Point2f origin;
		public final Point2f destination;
		
		/**
		 * Builds a shooting event
		 * @param origin
		 * @param destination
		 */
		public TowerShootEvent(Point2f origin, Point2f destination) {
			super();
			this.origin = origin;
			this.destination = destination;
		}
	}
	
	private int level;
	
	private final GridPosition gridPosition;
	
	private final TowerFactory towerFactory;
	
	private ShootingStrategy shootStrategy;
	
	private float secondsSinceLastShot;

	/**
	 * Initialize the data members
	 * @param level The level of the game
	 * @param gridPosition The position of the {@link Tower} on {@link GameMap}
	 * @param towerFactory The object of {@link TowerFactory}
	 */
	public Tower(int level, GridPosition gridPosition, ShootingStrategy shootingStrategy, TowerFactory towerFactory) {
		super();
		this.gridPosition = gridPosition;
		this.level = level;
		this.towerFactory = towerFactory;
		setShootingStrategy(shootingStrategy);
		secondsSinceLastShot = getShootRateSecs();
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
	
//	/**
//	 * Validate if position of enemy is within the range of tower 
//	 * @param otherCoordinate The position of enemy on {@link GameMap}
//	 * @return True if the position of enemy is within the range of tower else false
//	 */
//	public boolean inRange(GridPosition otherCoordinate) {
//		return distance(gridPosition, otherCoordinate) < getRange();
//	}
	
	/**
	 * Calculate the distance between Tower and Enemy
	 * @param otherCoordinate The position of enemy on {@link GameMap}
	 * @return The distance between Tower and Enemy
	 */
	public double distanceTo(GridPosition otherCoordinate) {
		return distance(gridPosition, otherCoordinate);
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
	 * Returns the time in seconds for every shot of the tower
	 * @return the time in seconds for every shot of the tower
	 */
	public float getShootRateSecs() {
		return getTowerLevelCharacteristic().getShootRateSecs();
	}
	
	/**
	 * Returns true if the tower can be upgraded, false if not
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
		return getTowerFactory().getLevelInformation(getClass(), level);
	}
	
	/**
	 * Returns the {@link TowerFactory}
	 * @return The {@link TowerFactory}
	 */
	private TowerFactory getTowerFactory() {
		return towerFactory;
	}
	
	public boolean canLockTarget(Enemy T) {
		double countedDistanse = countDistanse(T);
		if ( countedDistanse < (getRange())) {
			return true;
		}
		return false;
	}
	public double countDistanse(Enemy T) {
		return distanceTo(gridPosition);
	}
	
	public void shootEnemy(Enemy enemy) {
		enemy.setHealth(enemy.getHealth() - getDamage());
	}
	
	/**
	 * Sets the shooting strategy
	 * @param shootingStrategy
	 */
	public void setShootingStrategy(ShootingStrategy shootingStrategy) {
		this.shootStrategy = shootingStrategy;
		shootingStrategy.setTower(this);
	}
	
	/**
	 * Maybe shoots the enemies, if they are in range, with a given strategy
	 * @param shootEnemies enemies that will maybe be shot
	 */
	public void maybeShoot(List<Enemy> shootEnemies) {
		if (hasCooledDown()) {
			shootStrategy.shootIfInRange(shootEnemies);
		}
	}
	
	/**
	 * Towers shoot the enemies 
	 * @param enemies enemies that are in range
	 */
	protected abstract void specializedShot(Enemy enemy);
	
	public void shoot(Enemy enemy) {
		secondsSinceLastShot = 0;
		specializedShot(enemy);
		notifyObservers(new TowerShootEvent(getGridPosition().toPoint2f(), enemy.getCurrentPosition()));
	}
	/**
	 * Return true if the {@link Tower} has to shoot 
	 * @return True if {@link Tower} has to shoot  
	 */
	public boolean hasCooledDown() {
		return secondsSinceLastShot >= getShootRateSecs();
	}

	/**
	 * Update the tower for the amount of seconds passed
	 * @param seconds
	 */
	public void update(float seconds) {
		secondsSinceLastShot = secondsSinceLastShot + seconds;
	}
/**
 * Return True if the {@link Enemy} is in range
 * @param otherPosition The position of the {@link Enemy}
 * @return True if the {@link Enemy} is in range
 */
	public boolean inRange(Point2f otherPosition) {
		float distance = new Point2f(gridPosition.getX(), gridPosition.getY()).distance(otherPosition);
		return distance <= getRange();
	}
	
	/**
	 * Applied the visitor to this {@link Tower}
	 * @param visitor to be applied
	 */
	public abstract void visit(TowerVisitor visitor);
/**
 * Return the distance  between the {@link Tower} and {@link Enemy}
 * @param currentPosition The position of the {@link Enemy}
 * @return The distance between the {@link Tower} and {@link Enemy}
 */
	public float distanceTo(Point2f currentPosition) {
		return new Point2f(gridPosition.getX(), gridPosition.getY()).distance(currentPosition);
	}
	/**
	 * Return the object of {@link ShootingStrategy}
	 * @return The object of {@link ShootingStrategy}
	 */
	public ShootingStrategy getShootingStrategy() {
		return shootStrategy;
	}
}
