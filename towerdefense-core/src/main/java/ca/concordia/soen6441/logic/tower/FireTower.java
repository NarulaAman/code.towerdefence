package ca.concordia.soen6441.logic.tower;

import java.util.Observer;

import ca.concordia.soen6441.logic.Enemy;
import ca.concordia.soen6441.logic.TowerFactory;
import ca.concordia.soen6441.logic.primitives.GridPosition;
import ca.concordia.soen6441.logic.primitives.TemporalEffect;
import ca.concordia.soen6441.logic.tower.shootingstrategy.ShootingStrategy;

public class FireTower extends AbstractTemporalEffectTower implements Observer{
	
	private final static float BURN_DURATION_SECS = 4;
	private final static int BURN_DAMAGE = 1;
	private final static float DAMAGE_EVERY_SECONDS = 0.5f;
	
	class BurningEnemyEffect extends TemporalEffect {
		
		final Enemy enemy;
		float lastBurnTime = 0;
		
		public BurningEnemyEffect(Enemy enemy) {
			super(BURN_DURATION_SECS);
			this.enemy = enemy;
		}

		@Override
		protected void updateEffect(float seconds) {
			super.updateEffect(seconds);
			lastBurnTime = lastBurnTime + seconds;
			while (lastBurnTime > DAMAGE_EVERY_SECONDS) {
				enemy.takeDamage(BURN_DAMAGE);
				lastBurnTime = lastBurnTime - DAMAGE_EVERY_SECONDS;
			}
		}
	};
	
	public FireTower(int level, GridPosition gridPosition, ShootingStrategy shootingStrategy, TowerFactory towerFactory) {
		super(level, gridPosition, shootingStrategy, towerFactory);
	}

	protected TemporalEffect buildEffectOn(Enemy enemy) {
		return new BurningEnemyEffect(enemy);
	}

	@Override
	public void visit(TowerVisitor visitor) {
		visitor.visit(this);
	}
	
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
