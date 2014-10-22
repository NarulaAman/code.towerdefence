package ca.concordia.soen6441.logic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ca.concordia.soen6441.logic.primitives.GridPosition;

public class TowerFactory {
	
	HashMap<Class<? extends Tower>, List<TowerLevelCaracteristic>> towerTypeInformation = new HashMap<>();
	
	public TowerFactory() {
		List<TowerLevelCaracteristic> towerLevelCharacteristic  = new ArrayList<>();
		towerLevelCharacteristic.add(new TowerLevelCaracteristic(10, 100, 75, 2));
		towerLevelCharacteristic.add(new TowerLevelCaracteristic(15, 70, 75, 3));
		towerTypeInformation.put(Tower.class, towerLevelCharacteristic);
	}
	
	public int maxLevel(Class<? extends Tower> type) {
		return towerTypeInformation.get(type).size();
	}
	
	public TowerLevelCaracteristic getLevelInformation(Class<? extends Tower> type, int level) {
		return towerTypeInformation.get(type).get(level-1);
	}
	
	public Tower towerOnCoordinate(Class<? extends Tower> type, GridPosition coordinate) {
		return new Tower(1, coordinate, this); // new Tower(gridPosition, new TowerInformation(0, 0, 0, 0));
	}
	
}
