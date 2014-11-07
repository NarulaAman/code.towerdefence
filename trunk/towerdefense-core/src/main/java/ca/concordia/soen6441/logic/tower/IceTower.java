package ca.concordia.soen6441.logic.tower;

import ca.concordia.soen6441.logic.Enemy;
import ca.concordia.soen6441.logic.TowerFactory;
import ca.concordia.soen6441.logic.primitives.GridPosition;

/**
 * Tower that slows the enemies down when they are shot
 *
 */
public class IceTower extends Tower {

	/**
	 * Create an Ice tower of a certain level
	 * @param level
	 * @param gridPosition
	 * @param shootingStrategy
	 * @param towerFactory
	 */
	public IceTower(int level, GridPosition gridPosition, AimingStrategy shootingStrategy, TowerFactory towerFactory) {
		super(level, gridPosition, shootingStrategy, towerFactory);
	}

	@Override
	protected void specializedShot(Enemy enemy) {
		enemy.setHealth(enemy.getHealth() - getDamage());
	}

}
