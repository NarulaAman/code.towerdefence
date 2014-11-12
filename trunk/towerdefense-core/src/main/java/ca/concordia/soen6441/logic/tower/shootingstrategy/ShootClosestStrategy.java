package ca.concordia.soen6441.logic.tower.shootingstrategy;

import java.util.List;

import ca.concordia.soen6441.logic.Enemy;

public class ShootClosestStrategy extends AbstractShootingStrategy implements ShootingStrategy {

	@Override
	public void shootIfInRange(List<Enemy> enemies) {
		Enemy enemyToShoot = null;
		for (Enemy enemy : enemies) {
			if (getTower().inRange(enemy.getCurrentPosition())) {
				if (enemyToShoot == null) {
					enemyToShoot = enemy;
				}
				else {
					float currentEnemyDistance = getTower().distanceTo(enemy.getCurrentPosition());
					float lockedEnemyDistance = getTower().distanceTo(enemyToShoot.getCurrentPosition());
					if (currentEnemyDistance < lockedEnemyDistance) {
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
