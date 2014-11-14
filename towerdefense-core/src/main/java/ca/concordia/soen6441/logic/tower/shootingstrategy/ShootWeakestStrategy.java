package ca.concordia.soen6441.logic.tower.shootingstrategy;

import java.util.List;

import ca.concordia.soen6441.logic.Enemy;
import ca.concordia.soen6441.logic.GamePlay;
/**
 * 
 * This class has strategy to shoot the enemy weakest health
 *
 */
public class ShootWeakestStrategy extends AbstractShootingStrategy {
	
	/**
	 * Shoot the enemy with weakest health
	 * @param enemies The list of {@link Enemy} in the {@link GamePlay}
	 */
	public void shootIfInRange(List<Enemy> enemies) {
		Enemy enemyToShoot = null;
		for (Enemy enemy : enemies) {
			if (getTower().inRange(enemy.getCurrentPosition())) {
				if (enemyToShoot == null) {
					enemyToShoot = enemy;
				}
				else {
					if (enemy.getHealth() < enemyToShoot.getHealth()) {
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
