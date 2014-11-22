package ca.concordia.soen6441.guice;

import ca.concordia.soen6441.io.GameMapJavaSerializationDao;
import ca.concordia.soen6441.logger.Log;
import ca.concordia.soen6441.logger.LogInterceptor;
import ca.concordia.soen6441.logger.LogMessage;
import ca.concordia.soen6441.logic.GameMapDao;
import ca.concordia.soen6441.logic.TowerFactory;
import ca.concordia.soen6441.logic.primitives.GridPosition;
import ca.concordia.soen6441.logic.tower.FireTower;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.assistedinject.FactoryModuleBuilder;
import com.google.inject.matcher.Matchers;

public class TowerDefenseModule extends AbstractModule {
  @Override 
  protected void configure() {
	  LogInterceptor logger = new LogInterceptor();
	  requestInjection(logger);
	    bindInterceptor(Matchers.any(), Matchers.annotatedWith(Log.class), 
	       logger);
	    
	   bind(GameMapDao.class).to(GameMapJavaSerializationDao.class);
	   install(new FactoryModuleBuilder().build(GamePlayFactory.class));
  }
  
//  @Provides
//  LogMessage provide() {
//	  return new LogMessage();
//  }
  
  
  public static void main(String[] args) {
	Injector injector = Guice.createInjector(new TowerDefenseModule());
	LogMessage m = injector.getInstance(LogMessage.class);
//	System.out.println(m.g);
	m.method().tryToBuy(new TowerFactory().towerOnCoordinate(FireTower.class, new GridPosition(1, 1)));
//	System.out.println(m.method().toString());
	
}
}