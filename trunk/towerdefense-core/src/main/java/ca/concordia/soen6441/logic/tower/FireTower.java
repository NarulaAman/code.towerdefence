package ca.concordia.soen6441.logic.tower;

import java.util.Observer;
import java.util.Set;

import ca.concordia.soen6441.logic.Enemy;
import ca.concordia.soen6441.logic.GameMap;
import ca.concordia.soen6441.logic.TowerFactory;
import ca.concordia.soen6441.logic.primitives.GridPosition;
import ca.concordia.soen6441.logic.primitives.TemporalEffect;
import ca.concordia.soen6441.logic.tower.shootingstrategy.ShootingStrategy;
/**
 * 
 * This class has characteristics of Fire {@link Tower}
 *
 */
public class FireTower extends Tower /*implements Observer */{

	private final static float BURN_DURATION_SECS = 4;
	private final static int BURN_DAMAGE = 1;
	private final static float DAMAGE_EVERY_SECONDS = 0.5f;

	/**
	 * Characteristics of burning affect on Enemy 
	 */
	class BurningEnemyEffect extends TemporalEffect {

		final Enemy enemy;
		float lastBurnTime = 0;
		/**
		 * 	Initialize the data members
		 * @param enemy The {@link Enemy} to be shot
		 */
		public BurningEnemyEffect(Enemy enemy) {
			super(BURN_DURATION_SECS);
			this.enemy = enemy;
		}

		/**
		 * Update the damage to the {@link Enemy}
		 * @param seconds seconds passed
		 */
		protected void updateEffect(float seconds) {
			super.updateEffect(seconds);
			lastBurnTime = lastBurnTime + seconds;
			while (lastBurnTime > DAMAGE_EVERY_SECONDS) {
				enemy.takeDamage(BURN_DAMAGE);
				lastBurnTime = lastBurnTime - DAMAGE_EVERY_SECONDS;
			}
		}
	};
	
	private TemporalEffectApplier burningEffect = new TemporalEffectApplier() {
		@Override
		protected TemporalEffect buildEffectOn(Enemy enemy) {
			return buildBurningEffectOn(enemy);
		}
	};

	/**
	 * Create Fire {@link Tower} of certain level
	 * @param level The level of the game
	 * @param gridPosition The position of the {@link Tower} on {@link GameMap}
	 * @param shootingStrategy The object of {@link ShootingStrategy}
	 * @param towerFactory The object of {@link TowerFactory}
	 */
	public FireTower(int level, GridPosition gridPosition, ShootingStrategy shootingStrategy, TowerFactory towerFactory) {
		super(level, gridPosition, shootingStrategy, towerFactory);
	}
	/**
	 * Returns the object of {@link TemporalEffect}
	 * @return Returns the object of {@link TemporalEffect}
	 */
	protected TemporalEffect buildBurningEffectOn(Enemy enemy) {
		return new BurningEnemyEffect(enemy);
	}

	@Override
	protected void specializedShot(Enemy enemy) {
		enemy.takeDamage(getDamage());
		burningEffect.applyEffectOn(enemy);
		
	}
	
	@Override
	public void update(float seconds) {
		super.update(seconds);
		burningEffect.update(seconds);
	}
	
	
	/**
	 * Visit this tower to visit the {@link TowerVisitor}
	 * @param visitor visitor to be aplied
	 */
	public void visit(TowerVisitor visitor) {
		visitor.visit(this);
	}
	
	public Set<Enemy> getEnemiesUnderEffect() {
		return burningEffect.getEnemiesUnderEffect();
	}
	
	/**
	 * Returns the Burn Duration seconds
	 * @return the burn duration seconds
	 */
	public float getBurnDurationSecs() {
		return BURN_DURATION_SECS;
	}
	
	/**
	 * Returns the burn damage caused by the tower
	 * @return the burn damage caused by the tower
	 */
	public int getBurnDamage() {
		return BURN_DAMAGE;
	}
	
	/**
	 * Returns the frequency the burn damage will be applied to
	 * @return the frequency the burn damage will be applied to
	 */
	public float getBurnRateSecs() {
		return DAMAGE_EVERY_SECONDS;

	}

}
