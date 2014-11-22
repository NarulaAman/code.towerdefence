package ca.concordia.soen6441.guice;

import ca.concordia.soen6441.logic.GameMap;
import ca.concordia.soen6441.logic.GamePlay;

public interface GamePlayFactory {
	public GamePlay create(GameMap gameMap, int currency);
}
