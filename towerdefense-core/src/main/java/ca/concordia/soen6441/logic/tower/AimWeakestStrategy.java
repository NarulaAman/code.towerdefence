package ca.concordia.soen6441.logic.tower;

import java.util.List;

import ca.concordia.soen6441.logic.Enemy;

public class AimWeakestStrategy implements AimingStrategy {

	private Tower tower;
	
	@Override
	public void setTower(Tower tower) {
		this.tower = tower;
	}
	
	@Override
	public void shootIfInRange(List<Enemy> enemies) {
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
			tower.shoot(enemyToShoot);
		}
	}




}
