package ca.concordia.soen6441.logic;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import ca.concordia.soen6441.logic.primitives.GridPosition;


/**
 * This class has logic for various operations executed on Game Play
 *
 */
public class GamePlay extends Observable implements Serializable {

	private static final long serialVersionUID = 1L;

	private final GameMap gameMap;
	
	private final List<Tower> towers = new ArrayList<Tower>();

	private int currency;
	
	private int lives;

	/**
	 * @param gameMap The map selected for Play
	 * @param currency The amount available
	 */
	public GamePlay(GameMap gameMap, int currency) {
		super();
		this.gameMap = gameMap;
		this.currency = currency;
		this.lives = 3;

	}

	/**
	 * Validate if the user can buy and place the {@link Tower} or not
	 * @param tower The object of class {@link Tower}
	 * @return True if the user can buy tower else false
	 */
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
	
	/**
	 * Validate the correct position of tower at {@link GameMap}
	 * @param tower The position of tower on {@link GameMap}
	 * @return True or False if the position of the Tower is correct or incorrect
	 */
	public boolean canPlace(Tower tower) {
		if (getMap().hasStartTile()) {
			if (tower.getGridPosition().equals(getMap().getStartGridPosition())) {
				return false;
			}
		}
		if (getMap().hasEndTile()) {
			if (tower.getGridPosition().equals(getMap().getEndGridPosition())) {
				return false;
			}
		}
		if(hasTower(tower.getGridPosition())){
			return false;
		}
		Tile tile = getMap().getTile(tower.getGridPosition());
		return tile == Tile.SCENERY;
	}

	
	/**
	 * Validate if the tower is present at give position
	 * @param gridPosition The X and Y coordinate of {@link Tile} on {@link GameMap}
	 * @return True if the Tower is present at give position
	 */
	public boolean hasTower(GridPosition gridPosition) {
		for (Tower tower : towers) {
			if (tower.getGridPosition().equals(gridPosition)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Return the object of {@link Tower} at give position
	 * @param gridPosition The X and Y coordinate of {@link Tile} on {@link GameMap}
	 * @return The object of {@link Tower}
	 */
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
	 * @param tower To be upgraded
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

	/**
	 * Notify the Observers that there was a change
	 */
	private void notifyWithChange() {
		setChanged();
		notifyObservers();
	}

	/**
	 * Remove the tower from {@link GameMap} and refund the currency amount
	 * @param tower To be refunded
	 */
	public void sell(Tower tower) {
		currency = currency + tower.getRefundRate();
		towers.remove(tower);
		notifyWithChange();
	}
	

	/**
	 * Returns the total number of towers on the {@link GameMap}
	 * @return The total number of towers on the {@link GameMap}
	 */
	public int totalTowers() {
		return towers.size();
	}
	
	/**
	 * Returns amount of currency
	 * @return currency
	 */
	public int getCurrency() {
		return currency;
	}

	/**
	 * Returns the current {@link GameMap}
	 * @return the current {@link GameMap}
	 */
	public GameMap getMap() {
		return gameMap;
	}

	/**
	 * Returns a copy of the list of Towers 
	 * @return a copy of the list of Towers
	 */
	public List<Tower> getTowers() {
		return new ArrayList<>(towers);
	}

	/**
	 * Returns the number of lives
	 * @return the number of lives
	 */
	public int getLives() {
		return lives;
	}
	
}