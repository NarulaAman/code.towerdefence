package ca.concordia.soen6441.logic.tower;

import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.ConcurrentHashMap;

import ca.concordia.soen6441.logic.Enemy;
import ca.concordia.soen6441.logic.TowerFactory;
import ca.concordia.soen6441.logic.primitives.GridPosition;
import ca.concordia.soen6441.logic.primitives.TemporalEffect;

public abstract class AbstractTemporalEffectTower extends Tower implements Observer {

	private final Map<Enemy, TemporalEffect> enemiesUnderEffect = new ConcurrentHashMap<>();

	public AbstractTemporalEffectTower(int level, GridPosition gridPosition, AimingStrategy shootingStrategy,
			TowerFactory towerFactory) {
		super(level, gridPosition, shootingStrategy, towerFactory);
	}
	
	protected abstract TemporalEffect buildEffectOn(Enemy enemy);

	@Override
	protected void specializedShot(Enemy enemy) {
		enemy.takeDamage(getDamage());
		enemy.addObserver(this);
		if (!enemiesUnderEffect.containsKey(enemy)) {
			TemporalEffect effect = buildEffectOn(enemy);
			effect.start();
			enemiesUnderEffect.put(enemy, effect);
		}
		else {
			TemporalEffect effect = enemiesUnderEffect.get(enemy);
			effect.resetEffect();
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		if (o instanceof Enemy) {
			Enemy enemy = (Enemy) o;
			if (!enemy.isAlive()) {
				enemy.deleteObserver(this);
				enemiesUnderEffect.remove(enemy);
			}
		}
	}

	public void update(float seconds) {
		super.update(seconds);
		for (Map.Entry<Enemy, TemporalEffect> burningEnemyEntry : enemiesUnderEffect.entrySet()) {
			Enemy enemyUnderEffect = burningEnemyEntry.getKey();
			TemporalEffect effect = burningEnemyEntry.getValue();
			effect.update(seconds);
			if (!effect.isActive()) {
				enemiesUnderEffect.remove(enemyUnderEffect);
			}
		}
	}

}