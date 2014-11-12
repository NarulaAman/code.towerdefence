package ca.concordia.soen6441.logic.tower.shootingstrategy;

import ca.concordia.soen6441.logic.tower.Tower;

public abstract class AbstractShootingStrategy implements ShootingStrategy {

	private Tower tower;

	public AbstractShootingStrategy() {
		super();
	}

	@Override
	public void setTower(Tower tower) {
		this.tower = tower;
	}
	
	protected Tower getTower() {
		return tower;
	}

}