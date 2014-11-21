package ca.concordia.soen6441.logic.tower;

import java.util.Set;

import ca.concordia.soen6441.logic.Enemy;
import ca.concordia.soen6441.logic.TowerFactory;
import ca.concordia.soen6441.logic.primitives.GridPosition;
import ca.concordia.soen6441.logic.primitives.TemporalEffect;
import ca.concordia.soen6441.logic.tower.shootingstrategy.ShootingStrategy;

/**
 * Tower that slows the enemies down when they are shot
 *
 */
public class IceTower extends Tower {

	public final static float SLOW_DURATION_SECS = 10; 
	public final static float SLOWNESS_RATE = 0.2f;
	/**
	 * 
	 * Characteristics of slowing affect on {@link Enemy}
	 *
	 */
	class SlowingEffect extends TemporalEffect {
		
		private final Enemy enemy;
		private float originalSpeed;
		
		/**
		 * Initialize the data members
		 * @param enemy to be slown down
		 */
		public SlowingEffect(Enemy enemy) {
			super(SLOW_DURATION_SECS);
			this.enemy = enemy;
		}
		
		/**
		 * Start the slowing affect on {@link Enemy}
		 */
		protected void startEffect() {
			super.startEffect();
			originalSpeed = enemy.getSpeed();
			float slowSpeed = originalSpeed * SLOWNESS_RATE;
			enemy.setSpeed(slowSpeed);
		}
		
		/**
		 * Stop the slowing affect on {@link Enemy}
		 */
		protected void stopEffect() {
			super.stopEffect();
			enemy.setSpeed(originalSpeed);
		}
	}
	
	private TemporalEffectApplier slowingEffect = new TemporalEffectApplier() {
		@Override
		protected TemporalEffect buildEffectOn(Enemy enemy) {
			return buildSlowingEffectOn(enemy);
		}
	};
	
	/**
	 * Create an Ice tower of a certain level
	 * @param level level of the tower to be created
	 * @param gridPosition grid position of the tower
	 * @param shootingStrategy shooting strategy to apply to the tower
	 * @param towerFactory tower factory
	 */
	public IceTower(int level, GridPosition gridPosition, ShootingStrategy shootingStrategy, TowerFactory towerFactory) {
		super(level, gridPosition, shootingStrategy, towerFactory);
	}

	/**
	 * Returns the object of {@link SlowingEffect}
	 * @return the object of {@link SlowingEffect}
	 */
	protected TemporalEffect buildSlowingEffectOn(Enemy enemy) {
		return new SlowingEffect(enemy);
	}

	/**
	 * Visit this tower to visit the {@link TowerVisitor}
	 * @param visitor visitor to be applied
	 */
	public void visit(TowerVisitor visitor) {
		visitor.visit(this);
	}
	
	/**
	 * Returns the duration of slowing down the enemy
	 * @return the duration of slowing down the enemy
	 */
	public float getSlownessDurationSecs() {
		return SLOW_DURATION_SECS * getLevel();
	}

	/**
	 * Returns the Rate of the slowness applied to the speed of the enemy
	 * @return the Rate of the slowness applied to the speed of the enemy
	 */
	public float getSlownessRate() {
		return SLOWNESS_RATE;
	}

	@Override
	protected void specializedShot(Enemy enemy) {
		enemy.takeDamage(getDamage());
		slowingEffect.applyEffectOn(enemy);
	}
	
	@Override
	public void update(float seconds) {
		super.update(seconds);
		slowingEffect.update(seconds);
	}

	public Set<Enemy> getEnemiesUnderEffect() {
		return slowingEffect.getEnemiesUnderEffect();
	}
	
}