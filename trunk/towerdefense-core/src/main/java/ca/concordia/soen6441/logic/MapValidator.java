package ca.concordia.soen6441.logic;

import ca.concordia.soen6441.logic.primitives.GridPosition;

/**
 *
 */

public class MapValidator {
	
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

		if (mapHasNoEnd(gameMap)) {
			messageIfNotValid.append("GameMap has no end\n");
			mapInconsistent = true;
		}
		if (mapHasNoStart(gameMap)) {
			messageIfNotValid.append("GameMap has no end\n");
			mapInconsistent = true;
		}
		if (mapStartPositionCount(gameMap) > 1) {
			messageIfNotValid.append("GameMap has more than one start position\n");
			mapInconsistent = true;
		}
		if (coordinateIsNotInTheSides(gameMap, gameMap.getStartGridPosition())) {
			messageIfNotValid.append("GameMap start position must be on the edges\n");
			mapInconsistent = true;
		}
		if (coordinateIsNotInTheSides(gameMap, gameMap.getEndGridPosition())) {
			messageIfNotValid.append("GameMap exit position must be on the edges\n");
			mapInconsistent = true;

		}
			
		if (distanceBtwnCoordinates(gameMap.getStartGridPosition(), gameMap.getEndGridPosition())) {
			messageIfNotValid.append("GameMap must have distance between start and exit\n");
			mapInconsistent = true;

		}
		

		if (mapInconsistent) {
			return false;
		} else {
			return true;
		}
	}
	
	public boolean distanceBtwnCoordinates(GridPosition d1,GridPosition d2) {
		
		double coordinateDistance = Math.sqrt((d1.getX()-d2.getX())*(d1.getX()-d2.getX()) + (d1.getY()-d2.getY())*(d1.getY()-d2.getY()));
		
		if (coordinateDistance<2) {

			return false;
		} else {
			return true;
		}
	}

	/**
	 * Returns true if the start of the gameMap is not on one of its sides
	 * 
	 * @param gameMap
	 *            gameMap to be checked
	 * @return true if the start of the gameMap is not on one of its sides
	 */
	public boolean coordinateIsNotInTheSides(GameMap gameMap, GridPosition coordinate) {
		if (coordinate.getX() == 0 || coordinate.getY() == 0
				|| coordinate.getX() == gameMap.getWidth() - 1
				|| coordinate.getY() == gameMap.getHeight() - 1) {

			return false;
		} else {
			return true;
		}
	}

	
	/**
	 * Returns 0 if the gameMap has only one start
	 * @param gameMap  
	 *            gameMap to be checked
	 * @return 0 if the gameMap has only one start
	 */
	public int mapStartPositionCount(GameMap gameMap) {
		if (gameMap.getStartGridPosition() == null) {
			return 0;
		}
		// else if (gameMap.getStartTile() != null)
		// {
		return 1;
		// }
	}

	/**
	 * Returns false if gameMap has no start
	 * @param gameMap  
	 *            gameMap to be checked
	 * @return false if gameMap has no start
	 */
	public boolean mapHasNoStart(GameMap gameMap) {

		return gameMap.hasStartTile() == false;
	}

	/**
	 *  Returns false if gameMap has no end
	 * @param gameMap
	 *            gameMap to be checked
	 * @return false if gameMap has no end
	 */
	public boolean mapHasNoEnd(GameMap gameMap) {
		return gameMap.hasEndTile() == false;
	}

}
