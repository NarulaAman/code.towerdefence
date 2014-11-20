package ca.concordia.soen6441.logic.tower.shootingstrategy;

import ca.concordia.soen6441.logic.tower.Tower;
/**
 * 
 * This is abstract class for shooting strategies 
 * of the tower
 */

public abstract class AbstractShootingStrategy implements ShootingStrategy {

	private Tower tower;
	/**
	 *Call the constructor method of extended class.  
	 */
	public AbstractShootingStrategy() {
		super();
	}

	/**
	* Set the value to Tower
	* @param tower {@link Tower} 
	*/
	@Override
	public void setTower(Tower tower) {
		this.tower = tower;
	}

	/**
	 * Return the value of {@link Tower} 	
	 * @return {@link Tower} That will have strategy applied to
	 */
	protected Tower getTower() {
		return tower;
	}

}