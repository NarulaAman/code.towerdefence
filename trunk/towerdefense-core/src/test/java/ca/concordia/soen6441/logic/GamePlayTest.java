package ca.concordia.soen6441.logic;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

import ca.concordia.soen6441.logic.primitives.GridPosition;
import ca.concordia.soen6441.logic.tower.Tower;



/**
 * This class will do unit testing of GamePlay
 *
 */
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes={TestConfig.class})
public class GamePlayTest {
	
	private static final int START_CURRENCY = 200;
	private GameMap gameMap;
	private Tower tower1;
	private Tower tower2;
	private Tower tower3;
	private GamePlay gamePlay;

	/**
	 * Setup pre-condition for all the test 
	 * @throws Exception on error
	 */
	@Before
	public void setUp() throws Exception {
		gameMap = mock(GameMap.class);
		tower1 = mock(Tower.class);
		tower2 = mock(Tower.class);
		tower3 = mock(Tower.class);
		gamePlay = new GamePlay(gameMap, 200);
	}
	
	/**
	 * Test the gamePlay start with total number of tower as 0  
	 */
	@Test
	public void testStartWithNoTowers() {
		assertEquals(0, gamePlay.totalTowers());
	}

	/**
	 * Test if it is possible to buy tower  
	 */
	@Test
	public void testBuySuccess() {
		when(tower1.getBuyCost()).thenReturn(START_CURRENCY / 2);		
		when(gameMap.getTile(any(GridPosition.class))).thenReturn(Tile.SCENERY);	
		assertTrue(gamePlay.tryToBuy(tower1));
		assertEquals(1, gamePlay.totalTowers());
	}
	
	/**
	 * Test that can spend all the currency buying towers
	 */
	@Test
	public void testBuySuccessEndedWithNoCurrency() {
		when(tower1.getBuyCost()).thenReturn(START_CURRENCY / 2);
		when(tower1.getGridPosition()).thenReturn(new GridPosition(1,  1));
		when(tower2.getBuyCost()).thenReturn(START_CURRENCY / 2);
		when(tower2.getGridPosition()).thenReturn(new GridPosition(2,  2));
		when(tower3.getBuyCost()).thenReturn(START_CURRENCY / 2);
		when(tower3.getGridPosition()).thenReturn(new GridPosition(3,  3));
		when(gameMap.getTile(any(GridPosition.class))).thenReturn(Tile.SCENERY);
		assertTrue(gamePlay.tryToBuy(tower1));
		assertEquals(1, gamePlay.totalTowers());
		assertTrue(gamePlay.tryToBuy(tower2));
		assertEquals(2, gamePlay.totalTowers());
		assertEquals(0, gamePlay.getCurrency());
	}
	
	/**
	 * Test that can spend all the currency buying towers
	 */
	@Test
	public void testThatCantBuyTowerOnEnemyPath() {
		when(tower1.getBuyCost()).thenReturn(START_CURRENCY / 2);
		when(tower1.getGridPosition()).thenReturn(new GridPosition(1,  1));
		when(gameMap.getTile(any(GridPosition.class))).thenReturn(Tile.ENEMY_PATH);
		assertFalse(gamePlay.tryToBuy(tower1));
	}
	
	/**
	 * Test if there is sufficient amount to buy tower
	 */
	@Test
	public void testBuyFailed() {
		when(tower1.getBuyCost()).thenReturn(START_CURRENCY + 50);		
		assertFalse(gamePlay.tryToBuy(tower1));
		assertEquals(0, gamePlay.totalTowers());
		assertEquals(START_CURRENCY, gamePlay.getCurrency());
	}
	
	
	/**
	 * Test if the currency amount decreases on buy of tower 
	 */
	@Test
	public void testBuyFailedOnThirdBuy() {
		when(tower1.getBuyCost()).thenReturn(START_CURRENCY / 2);
		when(tower1.getGridPosition()).thenReturn(new GridPosition(1,  1));
		when(tower2.getBuyCost()).thenReturn(START_CURRENCY / 2);
		when(tower2.getGridPosition()).thenReturn(new GridPosition(2,  2));
		when(tower3.getBuyCost()).thenReturn(START_CURRENCY / 2);
		when(tower3.getGridPosition()).thenReturn(new GridPosition(3,  3));
		when(gameMap.getTile(any(GridPosition.class))).thenReturn(Tile.SCENERY);
		
		assertTrue(gamePlay.tryToBuy(tower1));
		int expectedCurrencyFirstBuy = START_CURRENCY - tower1.getBuyCost();
		assertEquals(expectedCurrencyFirstBuy, gamePlay.getCurrency());
		assertTrue(gamePlay.tryToBuy(tower2));
		int expectedCurrencySecondBuy = expectedCurrencyFirstBuy - tower2.getBuyCost();
		assertEquals(expectedCurrencySecondBuy, gamePlay.getCurrency());
		assertFalse(gamePlay.tryToBuy(tower3));
		assertEquals(2, gamePlay.totalTowers());
	}
	
	/**
	 * Test prevent buying two towers in the same position
	 */
	@Test
	public void testPreventPlacingTwoTowersInTheSamePosition() {
		when(tower1.getGridPosition()).thenReturn(new GridPosition(1, 1));
		when(tower2.getGridPosition()).thenReturn(new GridPosition(1, 1));
		when(gameMap.getTile(new GridPosition(1, 1))).thenReturn(Tile.SCENERY);
		assertTrue(gamePlay.tryToBuy(tower1));
		assertFalse(gamePlay.tryToBuy(tower2));				
	}
	
   /**
    * Test if the tower level gets upgrade on sufficient funds 
    */
   @Test
    public void testUpgrade(){
	   when(tower1.getUpgradeCost()).thenReturn(START_CURRENCY /2); 
	   when(tower1.canUpgrade()).thenReturn(true);
	   assertTrue(gamePlay.tryToUpgrade(tower1));
	   verify(tower1).doUpgrade();
   }
   
   /**
	* Test if the tower upgrade fails due to insufficient funds
    */
   @Test
   public void testUpgradeFailed(){
	   when(tower1.getUpgradeCost()).thenReturn(START_CURRENCY + 1);
	   assertFalse(gamePlay.tryToUpgrade(tower1));
	   verify(tower1, never()).doUpgrade();
   }
   
   /**
	* Test if the tower upgrade didn't happen
	*/
   @Test
   public void testUpgradeDidntHappenCanUpgradeFalse(){
	   when(tower1.canUpgrade()).thenReturn(false);
	   assertFalse(gamePlay.tryToUpgrade(tower1));
	   verify(tower1, never()).doUpgrade();
   }
}
