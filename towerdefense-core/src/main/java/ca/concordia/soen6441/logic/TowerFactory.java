package ca.concordia.soen6441.logic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ca.concordia.soen6441.logic.primitives.GridPosition;

/**
 * Responsible for creating the towers
 *
 */
public class TowerFactory {
	
	HashMap<Class<? extends Tower>, List<TowerLevelCharacteristic>> towerTypeInformation = new HashMap<>();
	
	/**
	 * Create towers
	 */
	public TowerFactory() {
		List<TowerLevelCharacteristic> towerLevelCharacteristic  = new ArrayList<>();
		towerLevelCharacteristic.add(new TowerLevelCharacteristic(10, 100, 75, 2));
		towerLevelCharacteristic.add(new TowerLevelCharacteristic(15, 70, 75, 3));
		towerTypeInformation.put(Tower.class, towerLevelCharacteristic);
	}
	
	/**
	 * Returns the maximum level of tower 
	 * @param type The type of the tower
	 * @return The maximum level of the tower
	 */
	public int maxLevel(Class<? extends Tower> type) {
		return towerTypeInformation.get(type).size();
	}
	
	/**
	 * Return the object of {@link TowerLevelCharacteristic} based on tower type and level
	 * @param type The type of the tower
	 * @param level The level of the tower
	 * @return The {@link TowerLevelCharacteristic}
	 */
	public TowerLevelCharacteristic getLevelInformation(Class<? extends Tower> type, int level) {
		return towerTypeInformation.get(type).get(level-1);
	}
	
	/**
	 * Return the tower present on the given coordinate
	 * @param type The type of the tower
	 * @param coordinate The position of the tower on {@link GameMap}
	 * @return The object of {@link Tower}
	 */
	public Tower towerOnCoordinate(Class<? extends Tower> type, GridPosition coordinate) {
		return new Tower(1, coordinate, this); // new Tower(gridPosition, new TowerInformation(0, 0, 0, 0));
	}
	
}
