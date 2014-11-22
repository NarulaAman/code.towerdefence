package ca.concordia.soen6441.logic.tower;

import java.util.ArrayList;
import java.util.List;

import ca.concordia.soen6441.logic.Enemy;
import ca.concordia.soen6441.logic.GameMap;
import ca.concordia.soen6441.logic.TowerFactory;
import ca.concordia.soen6441.logic.primitives.GridPosition;
import ca.concordia.soen6441.logic.tower.shootingstrategy.ShootingStrategy;
/**
 * 
 * This class has characteristics of Cannon {@link Tower}
 *
 */
public class CannonTower extends Tower {
	
	private static final float SPLASH_RANGE = 1;
	private static final float SPLASH_DAMAGE_RATIO = 0.3f;

	private transient List<Enemy> enemies;
	/**
	 * Create Cannon {@link Tower} with certain level
	 * @param level The level of the game
	 * @param gridPosition The position of the {@link Tower} on {@link GameMap}
	 * @param shootingStrategy The object of {@link ShootingStrategy}
	 * @param towerFactory The object of {@link TowerFactory}
	 */
	public CannonTower(int level, GridPosition gridPosition, ShootingStrategy shootingStrategy, TowerFactory towerFactory) {
		super(level, gridPosition, shootingStrategy, towerFactory);
	}

	/**
	 * Tower shoot the enemy
	 * @param enemy {@link Enemy} to shoot
	 */
	protected void specializedShot(Enemy enemy) {
		List<Enemy> enemiesToSplash = new ArrayList<>();
		for (Enemy otherEnemy : enemies) {
			if (otherEnemy == enemy) {
				continue; // skip if it is the same
			}
			if (enemy.getCurrentPosition().distance(otherEnemy.getCurrentPosition()) < SPLASH_RANGE) {
				enemiesToSplash.add(otherEnemy);
			}
		}
		enemy.takeDamage(getDamage());
		for (Enemy splashedEnemy : enemiesToSplash) {
			splashedEnemy.takeDamage((int) (getDamage() * SPLASH_DAMAGE_RATIO));
			notifyObservers(new TowerShootEvent(enemy.getCurrentPosition(), splashedEnemy.getCurrentPosition()));
		}
	}

	/**
	 * Visit this tower to visit the {@link TowerVisitor}
	 * @param visitor visitor to be applied to this tower
	 */
	public void visit(TowerVisitor visitor) {
		visitor.visit(this);
	}

	/**
	* Shoot the {@link Enemy} after interval of time
	* @param shootEnemies enemies to be shot
	*/
	public void maybeShoot(List<Enemy> shootEnemies) {
		if (hasCooledDown()) {
			enemies = shootEnemies;
			getShootingStrategy().shootIfInRange(shootEnemies);
			enemies = null;
		}
	}
	
	/**
	 * Returns the range of the splash damage
	 * @return the range of the splash damage
	 */
	public float getSplashRange() {
		return SPLASH_RANGE;
	}
	
	/**
	 * Returns the damage ratio of the splash damage, i.e. how much is multiplied by the tower's base damage to calculate the damage
	 * @return the damage ratio of the splash damage, i.e. how much is multiplied by the tower's base damage to calculate the damage
	 */
	public float getSplashDamageRatio() {
		return SPLASH_DAMAGE_RATIO;
	}
	
	@Override
	public String toString() {
		return "CannonTower on " + getGridPosition();
	}
}
