package ca.concordia.soen6441.logic;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import ca.concordia.soen6441.logic.primitives.Coordinate;

public class GamePlay extends Observable implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	final Map map;
	final List<Tower> towers = new ArrayList<Tower>();

	int currency;

	public GamePlay(Map map, int currency) {
		super();
		this.map = map;
		this.currency = currency;

	}

	public boolean buy(Tower tower) {
		if (tower.getBuyCost() <= currency) {
			currency = currency - tower.getBuyCost();
			towers.add(tower);
			notifyWithChange();
			return true;
		} else {
			return false;
		}
	}

	public boolean hasTower(int x, int y) {
		for (Tower tower : towers) {
			if (tower.getCoordinate().equals(new Coordinate(x, y))) {
				return true;
			}
		}
		return false;
	}

	public Tower getTower(int x, int y) {
		for (Tower tower : towers) {
			if (tower.getCoordinate().equals(new Coordinate(x, y))) {
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

	public int getCurrency() {
		return currency;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}

	public int totalTowers() {
		return towers.size();
	}

	public Map getMap() {
		return map;
	}

	public List<Tower> getTowers() {
		return new ArrayList<>(towers);
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
	

}
