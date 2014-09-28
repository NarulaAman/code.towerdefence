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

	public GamePlay(Map map) {
		super();
		this.map = map;
	}

	boolean buy(Tower tower) {
		if (tower.getBuyCost() <= currency) {
			currency -= tower.getBuyCost();
			towers.add(tower);
			return true;
		}
		else {
			return false;
		}
	}


	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}

	public Map getMap() {
		return map;
	}

}
