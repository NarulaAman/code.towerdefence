package ca.concordia.soen6441.logic.tower;

import java.util.Set;

import ca.concordia.soen6441.logger.LogManager;
import ca.concordia.soen6441.logic.Enemy;
import ca.concordia.soen6441.logic.TowerFactory;
import ca.concordia.soen6441.logic.primitives.GridPosition;
import ca.concordia.soen6441.logic.primitives.TemporalEffect;
import ca.concordia.soen6441.logic.tower.shootingstrategy.ShootingStrategy;


/**
 * Tower that slows the enemies down when they are shot.
 */
public class IceTower extends Tower {

	/** The Constant SLOW_DURATION_SECS. */
	public final static float SLOW_DURATION_SECS = 10; 
	
	/** The Constant SLOWNESS_RATE. */
	public final static float SLOWNESS_RATE = 0.2f;
	
	/**
	 * Characteristics of slowing affect on {@link Enemy}.
	 */
	class SlowingEffect extends TemporalEffect {
		
		/** The enemy. */
		private final Enemy enemy;
		
		/** The original speed. */
		private float originalSpeed;
		
		/**
		 * Initialize the data members.
		 *
		 * @param enemy to be slown down
		 */
		public SlowingEffect(Enemy enemy) {
			super(SLOW_DURATION_SECS);
			this.enemy = enemy;
		}
		
		/**
		 * Start the slowing affect on {@link Enemy}.
		 */
		protected void startEffect() {
			super.startEffect();
			originalSpeed = enemy.getSpeed();
			float slowSpeed = originalSpeed * SLOWNESS_RATE;
			enemy.setSpeed(slowSpeed);
		}
		
		/**
		 * Stop the slowing affect on {@link Enemy}.
		 */
		protected void stopEffect() {
			super.stopEffect();
			enemy.setSpeed(originalSpeed);
		}
	}
	
	/** The slowing effect. */
	private TemporalEffectApplier slowingEffect = new TemporalEffectApplier() {
		@Override
		protected TemporalEffect buildEffectOn(Enemy enemy) {
			return buildSlowingEffectOn(enemy);
		}
	};
	
	/**
	 * Create an Ice tower of a certain level.
	 *
	 * @param id the id
	 * @param level level of the tower to be created
	 * @param gridPosition grid position of the tower
	 * @param shootingStrategy shooting strategy to apply to the tower
	 * @param towerFactory tower factory
	 * @param logger the logger
	 */
	public IceTower(int id, int level, GridPosition gridPosition, ShootingStrategy shootingStrategy, TowerFactory towerFactory, LogManager logger) {
		super(id, level, gridPosition, shootingStrategy, towerFactory, logger);
	}

	/**
	 * Returns the object of {@link SlowingEffect}.
	 *
	 * @param enemy the enemy
	 * @return the object of {@link SlowingEffect}
	 */
	protected TemporalEffect buildSlowingEffectOn(Enemy enemy) {
		return new SlowingEffect(enemy);
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
	 * Returns the duration of slowing down the enemy.
	 *
	 * @return the duration of slowing down the enemy
	 */
	public float getSlownessDurationSecs() {
		return SLOW_DURATION_SECS * getLevel();
	}

	/**
	 * Returns the Rate of the slowness applied to the speed of the enemy.
	 *
	 * @return the Rate of the slowness applied to the speed of the enemy
	 */
	public float getSlownessRate() {
		return SLOWNESS_RATE;
	}

	/**
	 * Specialized Shot
	 */
	@Override
	protected void specializedShot(Enemy enemy) {
		enemy.takeDamage(getDamage());
		slowingEffect.applyEffectOn(enemy);
	}
	
	/**
	 * Update Seconds
	 */
	@Override
	public void update(float seconds) {
		super.update(seconds);
		slowingEffect.update(seconds);
	}

	/**
	 * Gets the enemies under effect.
	 *
	 * @return the enemies under effect
	 */
	public Set<Enemy> getEnemiesUnderEffect() {
		return slowingEffect.getEnemiesUnderEffect();
	}
	
	/**
	 * Return String
	 */
	@Override
	public String toString() {
		return "IceTower on " + getGridPosition();
	}
	
}
