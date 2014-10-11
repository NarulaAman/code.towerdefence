package ca.concordia.soen6441.logic;

import ca.concordia.soen6441.logic.primitives.Coordinate;

/**
 * @author Golnoush
 *
 */

public class MapValidator {
	
	/**
	 * check  validation of the map 
	 * 
	 * @param map
	 *            map to be checked
	 *        message 
	 *            deal with inconsistency in the map   
	 *            
	 * @return false if the map is not valid
	 */

	public boolean isValid(Map map, StringBuilder messageIfNotValid) {

		boolean mapInconsistent = false;

		if (mapHasNoEnd(map)) {
			messageIfNotValid.append("Map has no end\n");
			mapInconsistent = true;
		}
		if (mapHasNoStart(map)) {
			messageIfNotValid.append("Map has no end\n");
			mapInconsistent = true;
		}
		if (mapStartPositionCount(map) > 1) {
			messageIfNotValid.append("Map has more than one start position\n");
			mapInconsistent = true;
		}
		if (coordinateIsNotInTheSides(map, map.getStartTile())) {
			messageIfNotValid.append("Map start position must be on the edges\n");
			mapInconsistent = true;
		}
		if (coordinateIsNotInTheSides(map, map.getEndTile())) {
			messageIfNotValid.append("Map exit position must be on the edges\n");
			mapInconsistent = true;

		}
		if (mapInconsistent) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * Returns true if the start of the map is not on one of its sides
	 * 
	 * @param map
	 *            map to be checked
	 * @return true if the start of the map is not on one of its sides
	 */
	public boolean coordinateIsNotInTheSides(Map map, Coordinate coordinate) {
		if (coordinate.getX() == 0 || coordinate.getY() == 0
				|| coordinate.getX() == map.getWidth() - 1
				|| coordinate.getY() == map.getHeight() - 1) {

			return false;
		} else {
			return true;
		}
	}

	
	/**
	 * Returns 0 if the map has only one start
	 * @param map  
	 *            map to be checked
	 * @return 0 if the map has only one start
	 */
	public int mapStartPositionCount(Map map) {
		if (map.getStartTile() == null) {
			return 0;
		}
		// else if (map.getStartTile() != null)
		// {
		return 1;
		// }
	}

	/**
	 * Returns false if map has no start
	 * @param map  
	 *            map to be checked
	 * @return false if map has no start
	 */
	public boolean mapHasNoStart(Map map) {

		return map.hasStartTile() == false;
	}

	/**
	 *  Returns false if map has no end
	 * @param map
	 *            map to be checked
	 * @return false if map has no end
	 */
	public boolean mapHasNoEnd(Map map) {
		return map.hasEndTile() == false;
	}

}
