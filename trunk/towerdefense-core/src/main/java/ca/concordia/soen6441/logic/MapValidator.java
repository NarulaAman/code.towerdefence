package ca.concordia.soen6441.logic;

import ca.concordia.soen6441.logic.primitives.GridPosition;

/**
 * This class validate the gameMap before saving it
 *
 */
public class MapValidator {
	
	private static final int START_POSITION_COUNT = 1;
	private static final int END_POSITION_COUNT = 1;
	private static final double MIN_DISTANCE_FROM_START_TO_EXIT = 1.1;

	/**
	 * Check  validation of the gameMap along with the error message to be display 
	 * @param gameMap to be checked
	 * @param messageIfNotValid the error message to be displayed
	 * @return false if the gameMap is not valid
	 */
	public boolean isValid(GameMap gameMap, StringBuilder messageIfNotValid) {

		boolean mapInconsistent = false;

		if (! mapMustHaveEnd(gameMap)) {
			messageIfNotValid.append("Map must have one EXIT point\n");
			mapInconsistent = true;
		}
		if (! mapMustHaveStart(gameMap)) {
			messageIfNotValid.append("Map must have one START point\n");
			mapInconsistent = true;
		}
		if (mapStartPositionCount(gameMap) > START_POSITION_COUNT) {
			messageIfNotValid.append("Map shouldn't have more than one start position\n");
			mapInconsistent = true;
		}
		
		if (mapEndPositionCount(gameMap) > END_POSITION_COUNT) {
			messageIfNotValid.append("Map shouldn't have more than one exit position\n");
			mapInconsistent = true;
		}
		if (! coordinateOnTheEdge(gameMap, gameMap.getStartGridPosition())) {
			messageIfNotValid.append("Map start position must be on the edges\n");
			mapInconsistent = true;
		}
		if (! coordinateOnTheEdge(gameMap, gameMap.getEndGridPosition())) {
			messageIfNotValid.append("Map exit position must be on the edges\n");
			mapInconsistent = true;
		}
		
		if (! mapStartMustReachEnd(gameMap)) {
			messageIfNotValid.append("Map should have correct linked path between start and exit\n");
			mapInconsistent = true;			
		}
			
		if (distanceBtwnCoordinates(gameMap.getStartGridPosition(), gameMap.getEndGridPosition()) < MIN_DISTANCE_FROM_START_TO_EXIT) {
			messageIfNotValid.append("Map start and exit position should not be adjacent\n");
			mapInconsistent = true;
		}
		
		if (mapStartSamePositionAsEnd(gameMap)) {
			messageIfNotValid.append("You have the start and the exit on the same position, this is not allowed!\n");
			mapInconsistent = true;				
		}

		if (! mapHasAtLeastOneSceneryTile(gameMap)) {
			messageIfNotValid.append("You must have at least one Scenery Tile to place a tower!\n");
			mapInconsistent = true;				
		}
		
		if (! mapShouldHaveUniquePath(gameMap)) {
			messageIfNotValid.append("Your the enemy path tiles cannot have more than 2 adjacent paths!\n");
			mapInconsistent = true;						
		}
		
		if (mapInconsistent) {
			return false;
		} else {
			return true;
		}
	}
	
	/**
	 * Returns true if the {@link GameMap} has at least one Scenery tile, false if not
	 * @param gameMap {@link GameMap} to be checked
	 * @return true if the {@link GameMap} has at least one Scenery tile, false if not
	 */
	public boolean mapHasAtLeastOneSceneryTile(GameMap gameMap) {
		
		for (int w = 0; w < gameMap.getWidth(); ++w) {
			for (int h = 0; h < gameMap.getHeight(); ++h) {
				GridPosition gridPosition = new GridPosition(w, h);
				if (gameMap.getTile(gridPosition) == Tile.SCENERY 
						&& ! gridPosition.equals(gameMap.getEndGridPosition()) 
							&& ! gridPosition.equals(gameMap.getStartGridPosition())) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Tests if the start {@link GridPosition} of the {@link GameMap} is the same {@link GridPosition} as the end
	 * @param gameMap {@link GameMap} to be checked if the {@link GridPosition} of the start is the same as the end
	 * @return trus if the {@link GridPosition} of the start is the same as the end
	 */
	public boolean mapStartSamePositionAsEnd(GameMap gameMap) {
		if (gameMap.hasStartTile() && gameMap.hasEndTile()) {
			return gameMap.getStartGridPosition().equals(gameMap.getEndGridPosition());
		}
		return false;
	}

	/**
	 * Check the distance between start and exit position 
	 * @param positionA The start position coordinate
	 * @param positionB The exit position coordinate
	 * @return distance between positionA and positionB if positions are not null else return 0
	 */
	public double distanceBtwnCoordinates(GridPosition positionA,GridPosition positionB) { 
		if (positionA == null || positionB == null) {
			return 0;
		}
		return  positionA.distance(positionB);
	}

	/**
	 * Check true if the coordinates are on edges of the gameMap else false
	 * @param gameMap to be checked
	 * @param coordinate to be checked            
	 * @return true if the coordinates are on edges of the gameMap 
	 */
	public boolean coordinateOnTheEdge(GameMap gameMap, GridPosition coordinate) {
		if (coordinate == null) {
			return false;
		}
		return coordinate.getX() == 0 
				|| coordinate.getY() == 0
				|| coordinate.getX() == gameMap.getWidth() - 1
				|| coordinate.getY() == gameMap.getHeight() - 1;
	}

	
	/**
	 * Returns 0 if there is no start position on Map else 1
	 * @param gameMap to be checked
	 * @return 0 if there is no start position on Map 
	 */
	public int mapStartPositionCount(GameMap gameMap) {
		if (gameMap.getStartGridPosition() == null) {
			return 0;
		}
		return 1;
	}
	
	/**
	 * Return 0 if there is no end position on Map else 1
	 * @param gameMap to be checked
	 * @return 0 if there is no end position on Map
	 */
	public int mapEndPositionCount(GameMap gameMap) {
		if (gameMap.getEndGridPosition() == null) {
			return 0;
		}
		return 1;
	}
	

	/**
	 * Return true if start tile is present on gameMap else false
	 * @param gameMap to be checked
	 * @return true if start tile is present on gameMap
	 */
	public boolean mapMustHaveStart(GameMap gameMap) {
		return gameMap.hasStartTile();
	}

	/**
	 * Return true if end tile is present on gameMap else false
	 * @param gameMap to be checked
	 * @return true if end tile is present on gameMap
	 */
	public boolean mapMustHaveEnd(GameMap gameMap) {
		return gameMap.hasEndTile();
	}
	
	/**
	 * Check the correctness of path between start and exit point on Map
	 * @param gameMap to be checked
	 * @return true if the path between start point and exit point exist 
	 */
	public boolean mapStartMustReachEnd(GameMap gameMap) {
		if (gameMap.hasStartTile() && gameMap.hasEndTile()) {
			return gameMap.getStartToEndPath().size() > 0;
		}
		return false;
	}
	
	
	/**
	 * Check that the map has a unique path from the start and end, that is, no forks in the enemy path
	 * @param gameMap {@link GameMap} to be checked
	 * @return true if the path from the start to end is unique
	 */
	public boolean mapShouldHaveUniquePath(GameMap gameMap) {
		if (gameMap.hasStartTile() && gameMap.hasEndTile()) {
			for (GridPosition gridPosition : gameMap.getStartToEndPath()) {
				if (gameMap.getAdjacentWalkablePositions(gridPosition).size() > 2) {
					return false;
				}
			}
			return true;
		}
		return false;
	}
	



}
