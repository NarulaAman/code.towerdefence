import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import ca.concordia.soen6441.logic.GamePlayTest;
import ca.concordia.soen6441.logic.GameMapDaoTest;
import ca.concordia.soen6441.logic.MapValidatorTest;

/**
 * This is the Test Suite of our TowerDefense core project
 *
 */
@RunWith(Suite.class)
@SuiteClasses({GamePlayTest.class, GameMapDaoTest.class, MapValidatorTest.class})
public class TowerDefenseCoreTestSuite {
	
}
