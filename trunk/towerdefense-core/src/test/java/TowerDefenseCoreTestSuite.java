import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import ca.concordia.soen6441.logic.GamePlayTest;
import ca.concordia.soen6441.logic.MapPersisterTest;

@RunWith(Suite.class)
@SuiteClasses({GamePlayTest.class, MapPersisterTest.class})
public class TowerDefenseCoreTestSuite {
	
}
