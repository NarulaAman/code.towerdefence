package ca.concordia.soen6441.logic.tower.shootingstrategy;

import java.util.List;

import ca.concordia.soen6441.logic.Enemy;
import ca.concordia.soen6441.logic.GamePlay;

/**
 * 
 * This class has strategy to shoot the enemy closest to Exit 
 *
 */
public class ShootClosestToEndPointStrategy extends AbstractShootingStrategy implements ShootingStrategy {

	/**
	 * Shoot the enemy closest to Exit point
	 * @param enemies The list of {@link Enemy} in the {@link GamePlay}
	 */
	@Override
	public void shootIfInRange(List<Enemy> enemies) {
		Enemy enemyToShoot = null;
		for (Enemy enemy : enemies) {
			if (getTower().inRange(enemy.getCurrentPosition())) {
				if (enemyToShoot == null) {
					enemyToShoot = enemy;
				}
				else {
					if (enemy.getProgress() > enemyToShoot.getProgress()) {
						enemyToShoot = enemy;
					}
				}
			}
		}
		// if an enemy was chosen to shoot
		if (enemyToShoot != null) {
			getTower().shoot(enemyToShoot);
		}
	}




}
