package ca.concordia.soen6441.logic.tower;

import java.io.Serializable;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import ca.concordia.soen6441.logic.Enemy;
import ca.concordia.soen6441.logic.primitives.TemporalEffect;


/**
 * Abstract class for characteristics of A Temporal Effect Tower. 
 */
public abstract class TemporalEffectApplier implements Observer, Serializable {

	/** The enemies under effect. */
	private final Map<Enemy, TemporalEffect> enemiesUnderEffect = new ConcurrentHashMap<>();
	
	/**
	 * Initialize the TemporalEffectApplier.
	 */
	public TemporalEffectApplier() {
		//super(level, gridPosition, shootingStrategy, towerFactory);
	}
	
	/**
	 * Factory method to build an effect on a given enemy.
	 *
	 * @param enemy to have an TemporalEffect
	 * @return a Temporal effect for the enemy
	 */
	protected abstract TemporalEffect buildEffectOn(Enemy enemy);

	/**
	 * Towers shoot the enemies .
	 *
	 * @param enemy enemies that are in range
	 */
	public void applyEffectOn(Enemy enemy) {
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

	/**
	 * Delete the {@link Enemy} if its health is zero.
	 *
	 * @param o object of class {@link Enemy}
	 * @param arg is ignored
	 */
	public void update(Observable o, Object arg) {
		if (o instanceof Enemy) {
			Enemy enemy = (Enemy) o;
			if (!enemy.isAlive()) {
				enemy.deleteObserver(this);
				enemiesUnderEffect.remove(enemy);
			}
		}
	}
	
	/**
	 * Remove the temporal affect from the {@link Enemy}  when time is out.
	 *
	 * @param seconds seconds passed
	 */
	public void update(float seconds) {
		for (Map.Entry<Enemy, TemporalEffect> burningEnemyEntry : enemiesUnderEffect.entrySet()) {
			Enemy enemyUnderEffect = burningEnemyEntry.getKey();
			TemporalEffect effect = burningEnemyEntry.getValue();
			effect.update(seconds);
			if (!effect.isActive()) {
				enemiesUnderEffect.remove(enemyUnderEffect);
			}
		}
	}
	
	/**
	 * Return the set of {@link Enemy}.
	 *
	 * @return The set of {@link Enemy}
	 */ 
	public Set<Enemy> getEnemiesUnderEffect() {
		return enemiesUnderEffect.keySet();
	}

}