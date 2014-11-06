package ca.concordia.soen6441.logic;

import ca.concordia.soen6441.logic.primitives.GridPosition;

public class Enemy {
	private GridPosition gridPosition;
	private int health;
	
	public Enemy(){
		gridPosition =null;
		health=-1;
	}
	
	
	public GridPosition getGridPosition() {
		return gridPosition;
	}
	public void setGridPosition(GridPosition gridPosition) {
		this.gridPosition = gridPosition;
	}
	public int getHealth() {
		return health;
	}
	public void setHealth(int health) {
		this.health = health;
	}
	
	
	

}
