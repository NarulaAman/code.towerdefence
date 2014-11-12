package ca.concordia.soen6441.logic.tower;

import java.util.Iterator;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.ConcurrentHashMap;

import ca.concordia.soen6441.logic.Enemy;
import ca.concordia.soen6441.logic.TowerFactory;
import ca.concordia.soen6441.logic.primitives.GridPosition;

public class FireTower extends Tower implements Observer{
	
	private final static float BURN_TIME_SECS = 4;
	
	class BurnTime {
		float seconds = BURN_TIME_SECS;
	};
	
//	private final List<BurnTime> burningEnemies = new CopyOnWriteArrayList<>();
	
	private final Map<Enemy, BurnTime> burningEnemies = new ConcurrentHashMap<>();

	public FireTower(int level, GridPosition gridPosition, AimingStrategy shootingStrategy, TowerFactory towerFactory) {
		super(level, gridPosition, shootingStrategy, towerFactory);
	}

	@Override
	protected void specializedShot(Enemy enemy) {
		enemy.setHealth(enemy.getHealth() - getDamage());
		enemy.addObserver(this);
		
	}

	@Override
	public void update(Observable o, Object arg) {
		if (o instanceof Enemy) {
			Enemy enemy = (Enemy) o;
			if (!enemy.isAlive()) {
				enemy.deleteObserver(this);
				burningEnemies.remove(enemy);
			}
		}
	}
	
	
	
	public void update(float seconds) {
		super.update(seconds);
		for (Map.Entry<Enemy, BurnTime> burningEnemyEntry : burningEnemies.entrySet()) {
			Enemy burningEnemy = burningEnemyEntry.getKey();
			BurnTime burnTime = burningEnemyEntry.getValue();
			burnTime.seconds -= seconds;
			if (!(burnTime.seconds > 0)) {
				burningEnemies.remove(burningEnemy);
			}
		}
		
		
	}

}
