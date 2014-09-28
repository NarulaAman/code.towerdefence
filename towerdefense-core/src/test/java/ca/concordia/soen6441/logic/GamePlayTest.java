package ca.concordia.soen6441.logic;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;



public class GamePlayTest {
	
	private Map map;
	private Tower tower;

	@Before
	public void setUp() throws Exception {
		map = mock(Map.class);
		tower = mock(Tower.class);
	}

	@After
	public void tearDown() throws Exception {
		map = null;
		tower = null;
	}
	
	@Test
	public void testStartWithNoTowers() {
		
		GamePlay gamePlay = new GamePlay(map, 200);
		assertEquals(0, gamePlay.totalTowers());
	
	}

	@Test
	public void testBuySuccess() {
		when(tower.getBuyCost()).thenReturn(100);		
		
		GamePlay gamePlay = new GamePlay(map, 200);
		
		
		assertTrue(gamePlay.buy(tower));
		assertEquals(1, gamePlay.totalTowers());
	}
	
	@Test
	public void testBuySuccessEndedWithNoCurrency() {
		when(tower.getBuyCost()).thenReturn(100);		
		
		GamePlay gamePlay = new GamePlay(map, 200);
		
		assertTrue(gamePlay.buy(tower));
		assertEquals(1, gamePlay.totalTowers());
		assertTrue(gamePlay.buy(tower));
		assertEquals(2, gamePlay.getCurrency());
	}
	
	@Test
	public void testBuyFailed() {
		when(tower.getBuyCost()).thenReturn(100);		
		
		GamePlay gamePlay = new GamePlay(map, 50);
		
		assertFalse(gamePlay.buy(tower));
		assertEquals(0, gamePlay.totalTowers());
	}
	
	
	@Test
	public void testBuyFailedOnThirdBuy() {
		when(tower.getBuyCost()).thenReturn(100);	
		
		GamePlay gamePlay = new GamePlay(map, 200);
		
		assertTrue(gamePlay.buy(tower));
		assertTrue(gamePlay.buy(tower));	
		assertFalse(gamePlay.buy(tower));
		assertEquals(2, gamePlay.totalTowers());
		
	}
	

}
