package ca.concordia.soen6441.logic.tower;

import java.util.List;

import ca.concordia.soen6441.logic.Enemy;
import ca.concordia.soen6441.logic.Tower;

public class ShootWeakestStrategy implements ShootingStrategy {

	private Tower tower;
	
	public ShootWeakestStrategy(Tower tower) {
		super();
		this.tower = tower;
	}

	@Override
	public void shoot(List<Enemy> enemies) {
		Enemy enemyToShoot = null;
		for (Enemy enemy : enemies) {
			if (tower.inRange(enemy.getGridPosition())) {
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
		
		if (enemyToShoot != null) {
			// shoot it
		}
	}

}
