package ca.concordia.soen6441.logic.tower;

import java.util.List;

import ca.concordia.soen6441.logic.Enemy;

public interface ShootingStrategy {
	
	public void shoot(List<Enemy> enemies); 

}
