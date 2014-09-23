package ca.concordia.soen6441.logic;

import ca.concordia.soen6441.logic.primitives.Coordinate;


public class EnemyMovingLogic {

	final Enemy enemy;
	final Map map;
	
	// TODO: remove this hack
	Coordinate current = new Coordinate(-1, -1);
	Coordinate destination;
	
	long milisecondsAlive = 0;
	
	public EnemyMovingLogic(Enemy enemy, Map map) {
		super();
		this.enemy = enemy;
		this.map = map;
		destination = map.pathCoordinates.get(0);
	}

	

	public void update(long milisecondsFromLastUpdate) {
		
		milisecondsAlive += milisecondsFromLastUpdate;
		int coordinateIndex = Integer.valueOf(""+ milisecondsAlive / enemy.getMilisecondsToWalkATile());
		destination = map.pathCoordinates.get(coordinateIndex);
		enemy.setCoordinate(destination);
		
	}
	


}
