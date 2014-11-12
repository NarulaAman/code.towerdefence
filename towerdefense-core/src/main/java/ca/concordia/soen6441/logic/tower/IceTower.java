package ca.concordia.soen6441.logic.tower;

import ca.concordia.soen6441.logic.Enemy;
import ca.concordia.soen6441.logic.TowerFactory;
import ca.concordia.soen6441.logic.primitives.GridPosition;
import ca.concordia.soen6441.logic.primitives.TemporalEffect;

/**
 * Tower that slows the enemies down when they are shot
 *
 */
public class IceTower extends AbstractTemporalEffectTower {

	private final static float SLOW_DURATION_SECS = 10; 
	private final static float SLOWNESS_RATE = 0.7f;
	
	class SlowingEffect extends TemporalEffect {
		
		private float originalSpeed;
		private Enemy enemy;

		public SlowingEffect(Enemy enemy) {
			super(SLOW_DURATION_SECS);
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
	public IceTower(int level, GridPosition gridPosition, AimingStrategy shootingStrategy, TowerFactory towerFactory) {
		super(level, gridPosition, shootingStrategy, towerFactory);
	}

	@Override
	protected void specializedShot(Enemy enemy) {
		enemy.takeDamage(getDamage());
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
