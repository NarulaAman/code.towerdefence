package ca.concordia.soen6441.logic.tower;

import java.util.Set;

import ca.concordia.soen6441.logger.GamePlayLogger;
import ca.concordia.soen6441.logger.Log;
import ca.concordia.soen6441.logic.Enemy;
import ca.concordia.soen6441.logic.GameMap;
import ca.concordia.soen6441.logic.primitives.GridPosition;
import ca.concordia.soen6441.logic.primitives.TemporalEffect;
import ca.concordia.soen6441.logic.tower.shootingstrategy.ShootingStrategy;


/**
 * This class has characteristics of Fire {@link Tower}.
 */
public class FireTower extends Tower /*implements Observer */{

	/** The Constant BURN_DURATION_SECS. */
	private final static float BURN_DURATION_SECS = 4;
	
	/** The Constant BURN_DAMAGE. */
	private final static int BURN_DAMAGE = 1;
	
	/** The Constant DAMAGE_EVERY_SECONDS. */
	private final static float DAMAGE_EVERY_SECONDS = 0.5f;

	/**
	 * Characteristics of burning affect on Enemy.
	 */
	class BurningEnemyEffect extends TemporalEffect {

		/** The enemy. */
		final Enemy enemy;
		
		/** The last burn time. */
		float lastBurnTime = 0;
		
		/**
		 * 	Initialize the data members.
		 *
		 * @param enemy The {@link Enemy} to be shot
		 */
		public BurningEnemyEffect(Enemy enemy) {
			super(BURN_DURATION_SECS);
			this.enemy = enemy;
		}

		/**
		 * Update the damage to the {@link Enemy}.
		 *
		 * @param seconds seconds passed
		 */
		protected void updateEffect(float seconds) {
			super.updateEffect(seconds);
			lastBurnTime = lastBurnTime + seconds;
			while (lastBurnTime > DAMAGE_EVERY_SECONDS) {
				burnEnemy(enemy, BURN_DAMAGE);
				lastBurnTime = lastBurnTime - DAMAGE_EVERY_SECONDS;
			}
		}
		

	};
	
	/** The burning effect. */
	private TemporalEffectApplier burningEffect = new TemporalEffectApplier() {
		@Override
		protected TemporalEffect buildEffectOn(Enemy enemy) {
			return buildBurningEffectOn(enemy);
		}
	};

	/**
	 * Create Fire {@link Tower} of certain level.
	 *
	 * @param id the id
	 * @param level The level of the game
	 * @param gridPosition The position of the {@link Tower} on {@link GameMap}
	 * @param shootingStrategy The object of {@link ShootingStrategy}
	 * @param towerFactory The object of {@link TowerFactory}
	 * @param logger the logger
	 */
	public FireTower(int id, int level, GridPosition gridPosition, ShootingStrategy shootingStrategy, TowerFactory towerFactory, GamePlayLogger logger) {
		super(id, level, gridPosition, shootingStrategy, towerFactory, logger);
	}
	
	/**
	 * Returns the object of {@link TemporalEffect}.
	 *
	 * @param enemy the enemy
	 * @return Returns the object of {@link TemporalEffect}
	 */
	protected TemporalEffect buildBurningEffectOn(Enemy enemy) {
		return new BurningEnemyEffect(enemy);
	}

/**
 * Provide specialize shot.
 *
 * @param enemy the enemy
 */
	@Override
	protected void specializedShot(Enemy enemy) {
		enemy.takeDamage(getDamage());
		burningEffect.applyEffectOn(enemy);
		
	}
	
	/**
	 * Update the seconds.
	 *
	 * @param seconds the seconds
	 */
	@Override
	public void update(float seconds) {
		super.update(seconds);
		burningEffect.update(seconds);
	}
	
	/**
	 * Damage to Enemy.
	 *
	 * @param enemy the enemy
	 * @param damage the damage
	 */
	@Log("%1$s burns %2$s for %3$d damage")
	protected void burnEnemy(Enemy enemy, int damage) {
		enemy.takeDamage(damage);
	}
	
	
	/**
	 * Visit this tower to visit the {@link TowerVisitor}.
	 *
	 * @param visitor visitor to be applied
	 */
	public void visit(TowerVisitor visitor) {
		visitor.visit(this);
	}
	
	/**
	 * Return Enemy.
	 *
	 * @return the enemies under effect
	 */
	public Set<Enemy> getEnemiesUnderEffect() {
		return burningEffect.getEnemiesUnderEffect();
	}
	
	/**
	 * Returns the Burn Duration seconds.
	 *
	 * @return the burn duration seconds
	 */
	public float getBurnDurationSecs() {
		return BURN_DURATION_SECS;
	}
	
	/**
	 * Returns the burn damage caused by the tower.
	 *
	 * @return the burn damage caused by the tower
	 */
	public int getBurnDamage() {
		return BURN_DAMAGE;
	}
	
	/**
	 * Returns the frequency the burn damage will be applied to.
	 *
	 * @return the frequency the burn damage will be applied to
	 */
	public float getBurnRateSecs() {
		return DAMAGE_EVERY_SECONDS;
	}
	
	/**
	 * Return String.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "FireTower on " + getGridPosition();
	}
}
