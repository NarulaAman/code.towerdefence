package ca.concordia.soen6441.logic;

import ca.concordia.soen6441.logic.primitives.Coordinate;

public class MapValidator {

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
		if (mapStartIsNotInTheSides(map)) {
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
	public boolean mapStartIsNotInTheSides(Map map) {
		Coordinate startTile = map.getStartTile();

		if (startTile.getX() == 0 || startTile.getY() == 0
				|| startTile.getX() == map.getWidth() - 1
				|| startTile.getY() == map.getHeight() - 1) {

			return false;
		} else {
			return true;
		}
	}

	
	/**
	 * @param map
	 * @return
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

	public boolean mapHasNoStart(Map map) {

		return map.hasStartTile() == false;
	}

	public boolean mapHasNoEnd(Map map) {
		return map.hasEndTile() == false;
	}

}
