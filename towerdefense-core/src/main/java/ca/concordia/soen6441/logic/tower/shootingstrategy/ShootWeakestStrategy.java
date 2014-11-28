package ca.concordia.soen6441.logic.tower.shootingstrategy;

import java.util.List;

import ca.concordia.soen6441.logic.Enemy;

public class ShootWeakestStrategy extends AbstractShootingStrategy{

	@Override
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
