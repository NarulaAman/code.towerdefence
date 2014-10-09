package ca.concordia.soen6441.logic;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;



public class GamePlayTest {
	
	
	
	
	
	
	
	
	
	private static final int START_CURRENCY = 200;
	private Map map;
	private Tower tower;
	private GamePlay gamePlay;

	@Before
	public void setUp() throws Exception {
		map = mock(Map.class);
		tower = mock(Tower.class);
		gamePlay = new GamePlay(map, 200);
	}

	@After
	public void tearDown() throws Exception {
		map = null;
		tower = null;
		gamePlay = null;
	}
	
	@Test
	public void testStartWithNoTowers() {
		assertEquals(0, gamePlay.totalTowers());
	}

	@Test
	public void testBuySuccess() {
		when(tower.getBuyCost()).thenReturn(START_CURRENCY / 2);		
		assertTrue(gamePlay.buy(tower));
		assertEquals(1, gamePlay.totalTowers());
	}
	
	@Test
	public void testBuySuccessEndedWithNoCurrency() {
		when(tower.getBuyCost()).thenReturn(START_CURRENCY / 2);
		
		assertTrue(gamePlay.buy(tower));
		assertEquals(1, gamePlay.totalTowers());
		assertTrue(gamePlay.buy(tower));
		assertEquals(2, gamePlay.totalTowers());
		assertEquals(0, gamePlay.getCurrency());
	}
	
	@Test
	public void testBuyFailed() {
		when(tower.getBuyCost()).thenReturn(START_CURRENCY + 50);		
		assertFalse(gamePlay.buy(tower));
		assertEquals(0, gamePlay.totalTowers());
	}
	
	
	@Test
	public void testBuyFailedOnThirdBuy() {
		when(tower.getBuyCost()).thenReturn(START_CURRENCY / 2);	
		assertTrue(gamePlay.buy(tower));
		assertTrue(gamePlay.buy(tower));	
		assertFalse(gamePlay.buy(tower));
		assertEquals(2, gamePlay.totalTowers());
		
	}
	
	
	
   @Test
   
    public void testUpgrade(){
	   when(tower.getUpgradeCost()).thenReturn(START_CURRENCY /2); 
	   when(tower.canUpgrade()).thenReturn(true);
	   assertTrue(gamePlay.upgrade(tower));
	   verify(tower).doUpgrade();
   }
   
   @Test
   public void testUpgradeFailed(){
	   when(tower.getUpgradeCost()).thenReturn(START_CURRENCY +50);
	   assertFalse(gamePlay.upgrade(tower));
	   verify(tower, never()).doUpgrade();
   }
   
   
   @Test
   public void testUpgradeDintHappenCanUpgradeFalse(){
	   when(tower.canUpgrade()).thenReturn(false);
	   assertFalse(gamePlay.upgrade(tower));
	   verify(tower, never()).doUpgrade();
   }
}
