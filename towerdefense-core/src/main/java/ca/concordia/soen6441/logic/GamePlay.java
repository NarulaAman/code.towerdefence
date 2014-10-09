package ca.concordia.soen6441.logic;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class GamePlay implements Serializable {

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
			currency -= tower.getBuyCost();
			towers.add(tower);
			return true;
		} else {
			return false;
		}
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
		if (tower.canUpgrade()){
	        if (currency-tower.getUpgradeCost()>=0){
	            currency=-tower.getUpgradeCost();
	            tower.doUpgrade();}
	
			return true;
		}else{
		
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

}
