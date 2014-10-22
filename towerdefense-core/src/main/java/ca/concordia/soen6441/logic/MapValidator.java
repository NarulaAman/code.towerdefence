package ca.concordia.soen6441.logic;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import ca.concordia.soen6441.logic.primitives.GridPosition;

/**
 *
 */

public class MapValidator {
	
	private static final int START_POSITION_COUNT = 1;
	private static final int END_POSITION_COUNT = 1;
	private static final int MIN_DISTANCE_FROM_START_TO_EXIT = 2;

	/**
	 * check  validation of the gameMap 
	 * 
	 * @param gameMap
	 *            gameMap to be checked
	 *        message 
	 *            deal with inconsistency in the gameMap   
	 *            
	 * @return false if the gameMap is not valid
	 */

	public boolean isValid(GameMap gameMap, StringBuilder messageIfNotValid) {

		boolean mapInconsistent = false;

		if (mapMustHaveEnd(gameMap)) {
			messageIfNotValid.append("GameMap must have one end\n");
			mapInconsistent = true;
		}
		if (mapMustHaveStart(gameMap)) {
			messageIfNotValid.append("GameMap must have one start\n");
			mapInconsistent = true;
		}
		if (mapStartPositionCount(gameMap) > START_POSITION_COUNT) {
			messageIfNotValid.append("GameMap shouldn't have more than one start position\n");
			mapInconsistent = true;
		}
		
		if (mapEndPositionCount(gameMap) > END_POSITION_COUNT) {
			messageIfNotValid.append("GameMap shouldn't have more than one end position\n");
			mapInconsistent = true;
		}
		if (! coordinateOnTheEdge(gameMap, gameMap.getStartGridPosition())) {
			messageIfNotValid.append("GameMap start position must be on the edges\n");
			mapInconsistent = true;
		}
		if (! coordinateOnTheEdge(gameMap, gameMap.getEndGridPosition())) {
			messageIfNotValid.append("GameMap exit position must be on the edges\n");
			mapInconsistent = true;
		}
		
		if (! mapStartMustReachEnd(gameMap)) {
			messageIfNotValid.append("Map start should reach the end\n");
			mapInconsistent = true;			
		}
			
		if (distanceBtwnCoordinates(gameMap.getStartGridPosition(), gameMap.getEndGridPosition()) > MIN_DISTANCE_FROM_START_TO_EXIT) {
			messageIfNotValid.append("GameMap must have a distance between start and exit\n");
			mapInconsistent = true;
		}

		if (mapInconsistent) {
			return false;
		} else {
			return true;
		}
	}
	
	public double distanceBtwnCoordinates(GridPosition positionA,GridPosition positionB) {
		return  Math.sqrt((positionA.getX()-positionB.getX())*(positionA.getX()-positionB.getX()) + (positionA.getY()-positionB.getY())*(positionA.getY()-positionB.getY()));
	}

	/**
	 * Returns true if the start of the gameMap is not on one of its sides
	 * 
	 * @param gameMap
	 *            gameMap to be checked
	 * @return true if the start of the gameMap is not on one of its sides
	 */
	public boolean coordinateOnTheEdge(GameMap gameMap, GridPosition coordinate) {
		if (coordinate == null) {
			return false;
		}
			
		if (coordinate.getX() == 0 || coordinate.getY() == 0
				|| coordinate.getX() == gameMap.getWidth() - 1
				|| coordinate.getY() == gameMap.getHeight() - 1) {

			return false;
		} else {
			return true;
		}
	}

	
	public int mapStartPositionCount(GameMap gameMap) {
		if (gameMap.getStartGridPosition() == null) {
			return 0;
		}
		return 1;
	}
	
	public int mapEndPositionCount(GameMap gameMap) {
		if (gameMap.getEndGridPosition() == null) {
			return 0;
		}
		return 1;
	}
	

	public boolean mapMustHaveStart(GameMap gameMap) {
		return gameMap.hasStartTile();
	}

	public boolean mapMustHaveEnd(GameMap gameMap) {
		return gameMap.hasEndTile();
	}
	
	public boolean mapStartMustReachEnd(GameMap gameMap) {
		if (gameMap.hasStartTile() && gameMap.hasEndTile()) {
			ArrayDeque<GridPosition> positionQueue = new ArrayDeque<GridPosition>();
			Set<GridPosition> visitedPositions = new HashSet<>();
			positionQueue.addFirst(gameMap.getStartGridPosition());
			while (!positionQueue.isEmpty()) {
				GridPosition currentPosition = positionQueue.getFirst();
				visitedPositions.add(currentPosition);
				if (currentPosition.distance(gameMap.getEndGridPosition()) < 1.1) {
					return true;
				}
				List<GridPosition> walkableFromHere = gameMap.getAdjacentWalkablePositions(currentPosition);
				for (GridPosition gridPosition : walkableFromHere) {					
					if (! visitedPositions.contains(gridPosition)) {
						positionQueue.addFirst(currentPosition);
					}
				}
				
			}
		}
		return false;
	}
	


}
