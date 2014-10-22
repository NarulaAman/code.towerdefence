package ca.concordia.soen6441.logic;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import ca.concordia.soen6441.logic.primitives.GridPosition;

public class GamePlay extends Observable implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	final GameMap gameMap;
	final List<Tower> towers = new ArrayList<Tower>();

	int currency;
	
	int lives;

	public GamePlay(GameMap gameMap, int currency) {
		super();
		this.gameMap = gameMap;
		this.currency = currency;
		this.lives = 3;

	}

	public boolean buy(Tower tower) {
		if (canPlace(tower) && tower.getBuyCost() <= currency) {
			currency = currency - tower.getBuyCost();
			towers.add(tower);
			notifyWithChange();
			return true;
		} else {
			return false;
		}
	}
	
	public boolean canPlace(Tower tower) {
		return getMap().canPlace(tower);
	}

	public boolean hasTower(GridPosition gridPosition) {
		for (Tower tower : towers) {
			if (tower.getGridPosition().equals(gridPosition)) {
				return true;
			}
		}
		return false;
	}

	public Tower getTower(GridPosition gridPosition) {
		for (Tower tower : towers) {
			if (tower.getGridPosition().equals(gridPosition)) {
				return tower;
			}
		}
		return null;
	}

	/**
	 * Tries to upgrade the tower. Returns true if the upgrade happened, false
	 * if not
	 * 
	 * @param tower
	 *            to be upgraded
	 * @return true if the upgrade happened, false if not
	 */
	public boolean upgrade(Tower tower) {
		// implement upgrade logic here
		if (tower.canUpgrade() && currency >= tower.getUpgradeCost()) {
			currency = currency - tower.getUpgradeCost();
			tower.doUpgrade();
			notifyWithChange();
			return true;
		} else {

			return false;
		}
	}

	private void notifyWithChange() {
		setChanged();
		notifyObservers();
	}

	public void sell(Tower tower) {
		currency = currency + tower.getRefundRate();
		towers.remove(tower);
		notifyWithChange();
	}
	

	public int totalTowers() {
		return towers.size();
	}
	
	public int getCurrency() {
		return currency;
	}

	public GameMap getMap() {
		return gameMap;
	}
	
	

	public List<Tower> getTowers() {
		return new ArrayList<>(towers);
	}

	public int getLives() {
		return lives;
	}
	
}
