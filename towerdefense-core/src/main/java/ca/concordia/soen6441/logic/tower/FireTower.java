package ca.concordia.soen6441.logic.tower;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import ca.concordia.soen6441.logic.Enemy;
import ca.concordia.soen6441.logic.TowerFactory;
import ca.concordia.soen6441.logic.primitives.GridPosition;

public class FireTower extends Tower implements Observer{
	
	List<Enemy> burningEnemies = new ArrayList<>();

	public FireTower(int level, GridPosition gridPosition, AimingStrategy shootingStrategy, TowerFactory towerFactory) {
		super(level, gridPosition, shootingStrategy, towerFactory);
	}

	@Override
	protected void specializedShot(Enemy enemy) {
		enemy.setHealth(enemy.getHealth() - getDamage());
		enemy.addObserver(this);
	}

	@Override
	public void update(Observable o, Object arg) {
		
		
	}

}
