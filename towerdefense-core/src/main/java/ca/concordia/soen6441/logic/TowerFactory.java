package ca.concordia.soen6441.logic;

import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ca.concordia.soen6441.logic.primitives.GridPosition;
import ca.concordia.soen6441.logic.tower.CannonTower;
import ca.concordia.soen6441.logic.tower.FireTower;
import ca.concordia.soen6441.logic.tower.IceTower;
import ca.concordia.soen6441.logic.tower.Tower;
import ca.concordia.soen6441.logic.tower.shootingstrategy.ShootStrongestStrategy;
import ca.concordia.soen6441.logic.tower.shootingstrategy.ShootingStrategy;

/**
 * Responsible for creating the towers and holding their level characteristics
 *
 */
public class TowerFactory implements Serializable {

	private Map<Class<? extends Tower>, List<TowerLevelCharacteristic>> towerTypeInformation = new HashMap<>();

	private int towerId;
	/**
	 * Create a TowerFactory
	 */
	public TowerFactory() {
		List<TowerLevelCharacteristic> fireTowerLevelCharacteristic = new ArrayList<>();
		fireTowerLevelCharacteristic.add(new TowerLevelCharacteristic(10, 100, 75, 4, 0.9f));
		fireTowerLevelCharacteristic.add(new TowerLevelCharacteristic(15, 60, 120, 7, 0.7f));
		fireTowerLevelCharacteristic.add(new TowerLevelCharacteristic(20, 75, 190, 13, 0.5f));
		towerTypeInformation.put(FireTower.class, fireTowerLevelCharacteristic);
	
		List<TowerLevelCharacteristic> iceTowerLevelCharacteristic = new ArrayList<>();
		iceTowerLevelCharacteristic.add(new TowerLevelCharacteristic(1, 90, 75, 7, 1.f));
		iceTowerLevelCharacteristic.add(new TowerLevelCharacteristic(1, 50, 75, 13, 0.9f));
		iceTowerLevelCharacteristic.add(new TowerLevelCharacteristic(1, 65, 75, 16, 0.8f));
		towerTypeInformation.put(IceTower.class, iceTowerLevelCharacteristic);
	
		List<TowerLevelCharacteristic> cannonTowerLevelCharacteristic = new ArrayList<>();
		cannonTowerLevelCharacteristic.add(new TowerLevelCharacteristic(12, 110, 75, 5, 1.f));
		cannonTowerLevelCharacteristic.add(new TowerLevelCharacteristic(17, 65, 75, 8, 0.9f));
		cannonTowerLevelCharacteristic.add(new TowerLevelCharacteristic(23, 85, 75, 14, 0.8f));
		towerTypeInformation.put(CannonTower.class, cannonTowerLevelCharacteristic);
	}

	/**
	 * Returns the maximum level of tower
	 * 
	 * @param type
	 *            The type of the tower
	 * @return The maximum level of the tower
	 */
	public int maxLevel(Class<? extends Tower> type) {
		return towerTypeInformation.get(type).size();
	}

	/**
	 * Return the object of {@link TowerLevelCharacteristic} based on tower type
	 * and level
	 * 
	 * @param type
	 *            The type of the tower
	 * @param level
	 *            The level of the tower
	 * @return The {@link TowerLevelCharacteristic}
	 */
	public TowerLevelCharacteristic getLevelInformation(Class<? extends Tower> type, int level) {
		return towerTypeInformation.get(type).get(level - 1);
	}

	/**
	 * Return the tower present on the given coordinate
	 * 
	 * @param type
	 *            The type of the tower
	 * @param coordinate
	 *            The position of the tower on {@link GameMap}
	 * @return The object of {@link Tower}
	 */
	public Tower towerOnCoordinate(Class<? extends Tower> type, GridPosition coordinate) {
		try {
			Constructor<? extends Tower> constructor = type.getConstructor(int.class, int.class, GridPosition.class,
					ShootingStrategy.class, TowerFactory.class);
			return constructor.newInstance(getNextTowerId(), 1, coordinate, new ShootStrongestStrategy(), this);
		} catch (Exception exception) {
			throw new RuntimeException(exception);
		}
	}
	
	private int getNextTowerId() {
		return ++towerId;
	}

}
