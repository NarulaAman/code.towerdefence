package ca.concordia.soen6441.logic.tower;

import java.util.List;

import ca.concordia.soen6441.logic.Enemy;

/**
 * This is the interface of the shooting strategy of the tower
 *
 */
public interface AimingStrategy {
	
	/**
	 * Sets the tower to be used by the strategy
	 * @param tower {@link Tower} that will have the strategy applied to
	 */
	public void setTower(Tower tower);
	
	/**
	 * Shoot
	 * @param enemies
	 */
	public void shootIfInRange(List<Enemy> enemies); 

}
