import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import ca.concordia.soen6441.logic.GamePlayTest;
import ca.concordia.soen6441.logic.MapDaoTest;
import ca.concordia.soen6441.logic.MapValidatorTest;

@RunWith(Suite.class)
@SuiteClasses({GamePlayTest.class, MapDaoTest.class, MapValidatorTest.class})
public class TowerDefenseCoreTestSuite {
	
}
