package ca.concordia.soen6441.logic.tower;

import ca.concordia.soen6441.logic.Enemy;
import ca.concordia.soen6441.logic.TowerFactory;
import ca.concordia.soen6441.logic.primitives.GridPosition;

public class CannonTower extends Tower {

	public CannonTower(int level, GridPosition gridPosition, AimingStrategy shootingStrategy, TowerFactory towerFactory) {
		super(level, gridPosition, shootingStrategy, towerFactory);
	}

	@Override
	protected void specializedShot(Enemy enemy) {
		enemy.takeDamage(getDamage());
	}

	@Override
	public void visit(TowerVisitor visit) {
		visit.visit(this);
	}

}