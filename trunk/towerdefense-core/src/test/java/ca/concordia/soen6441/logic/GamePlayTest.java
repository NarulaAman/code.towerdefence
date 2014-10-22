package ca.concordia.soen6441.logic;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;



public class GamePlayTest {
	
	private static final int START_CURRENCY = 200;
	private Map map;
	private Tower tower1;
	private Tower tower2;
	private Tower tower3;
	private GamePlay gamePlay;

	@Before
	public void setUp() throws Exception {
		map = mock(Map.class);
		tower1 = mock(Tower.class);
		tower2 = mock(Tower.class);
		tower3 = mock(Tower.class);
		gamePlay = new GamePlay(map, 200);
	}
	
	@Test
	public void testStartWithNoTowers() {
		assertEquals(0, gamePlay.totalTowers());
	}

	@Test
	public void testBuySuccess() {
		when(tower1.getBuyCost()).thenReturn(START_CURRENCY / 2);		
		assertTrue(gamePlay.buy(tower1));
		assertEquals(1, gamePlay.totalTowers());
	}
	
	@Test
	public void testBuySuccessEndedWithNoCurrency() {
		when(tower1.getBuyCost()).thenReturn(START_CURRENCY / 2);
		assertTrue(gamePlay.buy(tower1));
		assertEquals(1, gamePlay.totalTowers());
		assertTrue(gamePlay.buy(tower2));
		assertEquals(2, gamePlay.totalTowers());
		assertEquals(0, gamePlay.getCurrency());
	}
	
	@Test
	public void testBuyFailed() {
		when(tower1.getBuyCost()).thenReturn(START_CURRENCY + 50);		
		assertFalse(gamePlay.buy(tower1));
		assertEquals(0, gamePlay.totalTowers());
	}
	
	
	@Test
	public void testBuyFailedOnThirdBuy() {
		when(tower1.getBuyCost()).thenReturn(START_CURRENCY / 2);	
		assertTrue(gamePlay.buy(tower1));
		assertTrue(gamePlay.buy(tower2));	
		assertFalse(gamePlay.buy(tower3));
		assertEquals(2, gamePlay.totalTowers());
	}
	
   @Test
    public void testUpgrade(){
	   when(tower1.getUpgradeCost()).thenReturn(START_CURRENCY /2); 
	   when(tower1.canUpgrade()).thenReturn(true);
	   assertTrue(gamePlay.upgrade(tower1));
	   verify(tower1).doUpgrade();
   }
   
   @Test
   public void testUpgradeFailed(){
	   when(tower1.getUpgradeCost()).thenReturn(START_CURRENCY + 1);
	   assertFalse(gamePlay.upgrade(tower1));
	   verify(tower1, never()).doUpgrade();
   }
   
   @Test
   public void testUpgradeDintHappenCanUpgradeFalse(){
	   when(tower1.canUpgrade()).thenReturn(false);
	   assertFalse(gamePlay.upgrade(tower1));
	   verify(tower1, never()).doUpgrade();
   }
}
