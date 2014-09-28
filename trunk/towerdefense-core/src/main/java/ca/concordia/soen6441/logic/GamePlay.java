package ca.concordia.soen6441.logic;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class GamePlay implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	final Map map;
	final List<Enemy> enemies = new ArrayList<Enemy>();
	final List<Tower> towers = new ArrayList<Tower>();

	int currency;

	public GamePlay(Map map) {
		super();
		this.map = map;
	}

	boolean buy(Tower tower) {
		if (tower.getBuyCost() <= currency) {
			currency -= tower.getBuyCost();
			towers.add(tower);
			return true;
		}
		else {
			return false;
		}
	}

//	public void update(long milisecondsFromLastUpdate) {
//		updateEnemies(milisecondsFromLastUpdate);
//		// updateTowers(milisecondsFromLastUpdate);
//	}

	// TODO: remove this!! To much logic, hard to test!!
	// TODO: tower could shoot more than one enemy, tower must receive list of
	// enemies
	// private void updateTowers(long milisecondsFromLastUpdate) {
	// for (Tower tower : towers) {
	// tower.update(milisecondsFromLastUpdate);
	// if (tower.canShoot() && hasLiveEnemies()) {
	// EnemyDistancePair enemyDistancePair = closestEnemyTo(tower);
	// if (enemyDistancePair.distance < tower.getRangeRadius()) {
	// Enemy enemy = enemyDistancePair.enemy;
	// tower.shoot(enemy);
	// if (enemy.isAlive() == false) {
	// remove(enemy);
	// }
	// }
	// }
	// }
	// }

	// private void remove(Enemy enemy) {
	// enemies.remove(enemy);
	//
	// }
	//
	//
	// private boolean hasLiveEnemies() {
	// return enemies.size() > 0;
	// }

	// private EnemyDistancePair closestEnemyTo(Tower tower) {
	// Enemy firstEnemy = enemies.get(0);
	// double distance = tower.distanceTo(firstEnemy.getCoordinate());
	// EnemyDistancePair enemyDistancePair = new EnemyDistancePair();
	// enemyDistancePair.enemy = firstEnemy;
	// enemyDistancePair.distance = distance;
	//
	// for (Enemy enemy : enemies) {
	// distance = tower.distanceTo(enemy.getCoordinate());
	// if (distance < enemyDistancePair.distance) {
	// enemyDistancePair.distance = distance;
	// enemyDistancePair.enemy = enemy;
	// }
	// }
	// return enemyDistancePair;
	// }

//	private void updateEnemies(long milisecondsFromLastUpdate) {
//		for (Enemy enemy : enemies) {
//			enemy.update(milisecondsFromLastUpdate);
//		}
//
//	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}

	public Map getMap() {
		return map;
	}

}
