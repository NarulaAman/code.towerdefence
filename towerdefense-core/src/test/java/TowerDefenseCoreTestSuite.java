import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import ca.concordia.soen6441.logic.GamePlayTest;
import ca.concordia.soen6441.logic.MapDaoTest;

@RunWith(Suite.class)
@SuiteClasses({GamePlayTest.class, MapDaoTest.class})
public class TowerDefenseCoreTestSuite {
	
}
