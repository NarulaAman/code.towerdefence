package ca.concordia.soen6441.logic;

import ca.concordia.soen6441.logic.primitives.Coordinate;
import ca.concordia.soen6441.logic.primitives.Damage;

public class Enemy {
	
	final Map map;
	final EnemyMovingLogic movingLogic;
	final long milisecondsToWalkATile;
	Coordinate coordinate;
	int hitPoints;
	
	public Enemy(Map map, Coordinate coordinate, int hitPoints) {
		super();
		this.map = map;
		movingLogic = new EnemyMovingLogic(this, map);
		this.coordinate = coordinate;
		this.hitPoints = hitPoints;
		milisecondsToWalkATile = 1000;
	}

	public void hitWith(Damage damage) {
		hitPoints = hitPoints - damage.getHitPoints();
	}
	
	public boolean isAlive() {
		return hitPoints > 0;
	}
	
	public boolean canMoveOn(Tile tile) {
		return tile.canHave(this);
	}
	
	
	
	public void update(long milisecondsFromLastUpdate) {
		movingLogic.update(milisecondsFromLastUpdate);
	}

	public Coordinate getCoordinate() {
		return coordinate;
	}
	

	void setCoordinate(Coordinate coordinate) {
		this.coordinate = coordinate;
	}

	public long getMilisecondsToWalkATile() {
		return milisecondsToWalkATile;
	}
	
	
	
	
	
	
}
