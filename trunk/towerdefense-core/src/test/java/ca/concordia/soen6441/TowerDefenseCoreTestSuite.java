package ca.concordia.soen6441;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import ca.concordia.soen6441.logic.GameMapDaoTest;
import ca.concordia.soen6441.logic.GamePlayTest;
import ca.concordia.soen6441.logic.MapValidatorTest;
import ca.concordia.soen6441.logic.TowerTest;
import ca.concordia.soen6441.logic.tower.CannonTowerTest;
import ca.concordia.soen6441.logic.tower.FireTowerTest;
import ca.concordia.soen6441.logic.tower.IceTowerTest;

/**
 * This is the Test Suite of our TowerDefense core project
 *
 */
@RunWith(Suite.class)
@SuiteClasses({GamePlayTest.class, GameMapDaoTest.class, MapValidatorTest.class, TowerTest.class, FireTowerTest.class, IceTowerTest.class, CannonTowerTest.class})
public class TowerDefenseCoreTestSuite {
	
}
