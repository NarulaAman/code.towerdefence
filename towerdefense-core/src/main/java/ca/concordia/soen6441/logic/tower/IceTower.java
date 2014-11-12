package ca.concordia.soen6441.logic.tower;

import ca.concordia.soen6441.logic.Enemy;
import ca.concordia.soen6441.logic.TowerFactory;
import ca.concordia.soen6441.logic.primitives.GridPosition;
import ca.concordia.soen6441.logic.primitives.TemporalEffect;
import ca.concordia.soen6441.logic.tower.shootingstrategy.ShootingStrategy;

/**
 * Tower that slows the enemies down when they are shot
 *
 */
public class IceTower extends AbstractTemporalEffectTower {

	public final static float SLOW_DURATION_SECS = 10; 
	public final static float SLOWNESS_RATE = 0.2f;
	
	class SlowingEffect extends TemporalEffect {
		
		private final Enemy enemy;
		private float originalSpeed;
		

		public SlowingEffect(Enemy enemy) {
			super(SLOW_DURATION_SECS);
			this.enemy = enemy;
		}
		
		@Override
		protected void startEffect() {
			super.startEffect();
			originalSpeed = enemy.getSpeed();
			float slowSpeed = originalSpeed * SLOWNESS_RATE;
			enemy.setSpeed(slowSpeed);
		}
		
		@Override
		protected void stopEffect() {
			super.stopEffect();
			enemy.setSpeed(originalSpeed);
		}
	}
	
	
	/**
	 * Create an Ice tower of a certain level
	 * @param level
	 * @param gridPosition
	 * @param shootingStrategy
	 * @param towerFactory
	 */
	public IceTower(int level, GridPosition gridPosition, ShootingStrategy shootingStrategy, TowerFactory towerFactory) {
		super(level, gridPosition, shootingStrategy, towerFactory);
	}

	@Override
	protected TemporalEffect buildEffectOn(Enemy enemy) {
		return new SlowingEffect(enemy);
	}

	@Override
	public void visit(TowerVisitor visitor) {
		visitor.visit(this);
		
	}

}
